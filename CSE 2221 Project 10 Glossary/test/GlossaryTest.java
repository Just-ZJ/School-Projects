import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map2;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GlossaryTest {

    /**
     * Compares 2 maps and checks if they have the same key, as well as the same
     * values for each key.
     *
     * @param termDef
     *            the term -> definition map
     * @param termDefExpected
     *            the expected term -> definition map
     * @return true iff both maps have the same key, and also the same value for
     *         each key
     */
    public static boolean compareMaps(Map<String, String> termDef,
            Map<String, String> termDefExpected) {
        boolean checkSame = true;

        for (Map.Pair<String, String> x : termDef) {
            String term = x.key();
            String def = x.value();
            if (!termDefExpected.hasKey(term)
                    || !termDefExpected.hasValue(def)) {
                checkSame = false;
            }
        }
        return checkSame;
    }

    /**
     * Compares 2 files and checks if they are the same.
     *
     * @param in1
     *            the input stream of the first file
     * @param in2
     *            the input stream of the second file
     * @return true iff both files have the same number of lines and the same
     *         words in each line
     */
    public static boolean compareFiles(SimpleReader in1, SimpleReader in2) {
        boolean checkSame = true;
        while (!in1.atEOS()) {
            String tmp1 = in1.nextLine();
            String tmp2 = in2.nextLine();
            if (!tmp1.equals(tmp2)) {
                checkSame = false;
            }
        }
        if (!in2.atEOS()) {
            checkSame = false;
        }
        return checkSame;
    }

    /*
     * Test for processInputFile(String file, Map<String, String> termDef)
     */
    @Test //tests with the given terms
    public void testProcessInputFile_terms() {
        String file = "test/testProcessInputFile_terms";
        Map<String, String> termDef = new Map2<>();
        Glossary.processInputFile(file, termDef);

        Map<String, String> termDefExpected = new Map2<>();
        termDefExpected.add("meaning",
                "something that one wishes to convey, especially by language");
        termDefExpected.add("term", "a word whose definition is in a glossary");
        termDefExpected.add("word",
                "a string of characters in a language, which has at least one character");
        termDefExpected.add("definition",
                "a sequence of words that gives meaning to a term");
        termDefExpected.add("glossary",
                "a list of difficult or specialized terms, with their definitions,"
                        + "usually near the end of a book");
        termDefExpected.add("language",
                "a set of strings of characters, each of which has meaning");
        termDefExpected.add("book", "a printed or written literary work");

        int len = termDef.size();
        int lenExpected = 7;
        boolean check = compareMaps(termDef, termDefExpected);
        assertEquals(len, lenExpected);
        assertTrue(check);
    }

    @Test //tests with a term with a very long definition
    public void testProcessInputFile_1LongDef() {
        String file = "test/testProcessInputFile_1LongDef";
        Map<String, String> termDef = new Map2<>();
        Glossary.processInputFile(file, termDef);

        Map<String, String> termDefExpected = new Map2<>();
        termDefExpected.add("Lorem",
                "Lorem Ipsum is simply dummy text of the printing and typesetting "
                        + "industry. Lorem Ipsum has been the industry's standard dummy "
                        + "text ever since the 1500s, when an unknown printer took a "
                        + "galley of type and scrambled it to make a type specimen book. "
                        + "It has survived not only five centuries, but also the leap "
                        + "into electronic typesetting, remaining essentially unchanged. "
                        + "It was popularised in the 1960s with the release of Letraset "
                        + "sheets containing Lorem Ipsum passages, and more recently "
                        + "with desktop publishing software like Aldus PageMaker "
                        + "including versions of Lorem Ipsum.");

        int len = termDef.size();
        int lenExpected = 1;
        boolean check = compareMaps(termDef, termDefExpected);
        assertEquals(len, lenExpected);
        assertTrue(check);
    }

    /*
     * Tests for mapTermsAndLinks(Map<String, String> termDef, Map<String,
     * String> termLink)
     */
    @Test
    public void testmapTermsAndLinks_2Terms() {
        Map<String, String> termDef = new Map2<>();
        Map<String, String> termLink = new Map2<>();
        Map<String, String> termLinkExpected = new Map2<>();
        termDef.add("term", "bla bla");
        termDef.add("term2", "bla2 bla2");
        termLinkExpected.add("term", "term.html");
        termLinkExpected.add("term2", "term2.html");
        Glossary.mapTermsAndLinks(termDef, termLink);
        boolean check = compareMaps(termLink, termLinkExpected);
        assertTrue(check);
    }

    @Test
    public void testmapTermsAndLinks_Empty() {
        Map<String, String> termDef = new Map2<>();
        Map<String, String> termLink = new Map2<>();
        Map<String, String> termLinkExpected = new Map2<>();
        Glossary.mapTermsAndLinks(termDef, termLink);
        boolean check = compareMaps(termLink, termLinkExpected);
        assertTrue(check);
    }

    /*
     * Tests for outputHeader(SimpleWriter out, String title)
     */
    @Test
    public void testoutputHeader_Terms() {
        String outFile = "test/testoutputHeader_Terms";
        String fileExpected = "test/testoutputHeader_TermsExpected";
        SimpleWriter out = new SimpleWriter1L(outFile);
        Glossary.outputHeader(out, "Terms");
        SimpleReader in1 = new SimpleReader1L(outFile);
        SimpleReader in2 = new SimpleReader1L(fileExpected);
        boolean check = compareFiles(in1, in2);
        out.close();
        in1.close();
        in2.close();
        assertTrue(check);
    }

    @Test
    public void testoutputHeader_NoTitle() {
        String outFile = "test/testoutputHeader_NoTitle";
        String fileExpected = "test/testoutputHeader_NoTitleExpected";
        SimpleWriter out = new SimpleWriter1L(outFile);
        Glossary.outputHeader(out, "");
        SimpleReader in1 = new SimpleReader1L(outFile);
        SimpleReader in2 = new SimpleReader1L(fileExpected);
        boolean check = compareFiles(in1, in2);
        out.close();
        in1.close();
        in2.close();
        assertTrue(check);
    }

    /*
     * Tests for outputFooter(SimpleWriter out)
     */
    @Test
    public void testoutputFooter() {
        String outFile = "test/testoutputFooter";
        String fileExpected = "test/testoutputFooterExpected";
        SimpleWriter out = new SimpleWriter1L(outFile);
        Glossary.outputFooter(out);
        SimpleReader in1 = new SimpleReader1L(outFile);
        SimpleReader in2 = new SimpleReader1L(fileExpected);
        boolean check = compareFiles(in1, in2);
        out.close();
        in1.close();
        in2.close();
        assertTrue(check);
    }

    /*
     * Tests for processIndexHtml(SimpleWriter out, Map<String, String>
     * termLink, Queue<String> qTerms)
     */
    @Test
    public void testProcessIndexHtml_ABC() {
        String outFile = "test/testprocessIndexHtml_ABC";
        SimpleWriter out = new SimpleWriter1L(outFile);
        String fileExpected = "test/testprocessIndexHtml_ABCExpected";
        SimpleReader in1 = new SimpleReader1L(outFile);
        SimpleReader in2 = new SimpleReader1L(fileExpected);
        Map<String, String> termLink = new Map2<>();
        termLink.add("Beta", "Beta.html");
        termLink.add("Alpha", "Alpha.html");
        termLink.add("Calvin", "Calvin.html");
        Map<String, String> termLinkExpected = new Map2<>();
        termLinkExpected.add("Beta", "Beta.html");
        termLinkExpected.add("Alpha", "Alpha.html");
        termLinkExpected.add("Calvin", "Calvin.html");
        Queue<String> qTerms = new Queue2<>();
        qTerms.enqueue("Alpha");
        qTerms.enqueue("Beta");
        qTerms.enqueue("Calvin");
        Queue<String> qTermsExpected = new Queue2<>();
        qTermsExpected.enqueue("Alpha");
        qTermsExpected.enqueue("Beta");
        qTermsExpected.enqueue("Calvin");
        Glossary.processIndexHtml(out, termLink, qTerms);
        boolean checkMap = compareMaps(termLink, termLinkExpected);
        boolean checkFile = compareFiles(in1, in2);
        int len = qTerms.length();
        int lenExpected = qTermsExpected.length();
        out.close();
        in1.close();
        in2.close();
        assertTrue(checkMap);
        assertTrue(checkFile);
        assertEquals(len, lenExpected);
    }

    @Test
    public void testProcessIndexHtml_CBA() {
        String outFile = "test/testprocessIndexHtml_CBA";
        SimpleWriter out = new SimpleWriter1L(outFile);
        String fileExpected = "test/testprocessIndexHtml_CBAExpected";
        SimpleReader in1 = new SimpleReader1L(outFile);
        SimpleReader in2 = new SimpleReader1L(fileExpected);
        Map<String, String> termLink = new Map2<>();
        termLink.add("Beta", "Beta.html");
        termLink.add("Alpha", "Alpha.html");
        termLink.add("Calvin", "Calvin.html");
        Map<String, String> termLinkExpected = new Map2<>();
        termLinkExpected.add("Beta", "Beta.html");
        termLinkExpected.add("Alpha", "Alpha.html");
        termLinkExpected.add("Calvin", "Calvin.html");
        Queue<String> qTerms = new Queue2<>();
        qTerms.enqueue("Calvin");
        qTerms.enqueue("Beta");
        qTerms.enqueue("Alpha");
        Queue<String> qTermsExpected = new Queue2<>();
        qTermsExpected.enqueue("Calvin");
        qTermsExpected.enqueue("Beta");
        qTermsExpected.enqueue("Alpha");
        Glossary.processIndexHtml(out, termLink, qTerms);
        boolean checkMap = compareMaps(termLink, termLinkExpected);
        boolean checkFile = compareFiles(in1, in2);
        int len = qTerms.length();
        int lenExpected = qTermsExpected.length();
        out.close();
        in1.close();
        in2.close();
        assertTrue(checkMap);
        assertTrue(checkFile);
        assertEquals(len, lenExpected);
    }

    /*
     * Tests for processTermsHtml(Map<String, String> termDef, Map<String,
     * String> termLink, String outFolder)
     */
    @Test
    public void testProcessTermsHtml_OverLap() {
        String outFolder = "test/";
        SimpleWriter out = new SimpleWriter1L();
        Map<String, String> termDef = new Map2<>();
        termDef.add("glossary", "a list of difficult or specialized terms");
        termDef.add("terms", "a glossary");
        Map<String, String> termDefExpected = new Map2<>();
        termDefExpected.add("glossary",
                "a list of difficult or specialized terms");
        termDefExpected.add("terms", "a glossary");
        Map<String, String> termLink = new Map2<>();
        termLink.add("glossary", "glossary.html");
        termLink.add("terms", "terms.html");
        Map<String, String> termLinkExpected = new Map2<>();
        termLinkExpected.add("glossary", "glossary.html");
        termLinkExpected.add("terms", "terms.html");
        Glossary.processTermsHtml(termDef, termLink, outFolder);
        SimpleReader in1 = new SimpleReader1L("test/glossary.html");
        SimpleReader in2 = new SimpleReader1L(
                "test/testProcessTermsHtmlExpected_Glossary");
        SimpleReader in3 = new SimpleReader1L("test/terms.html");
        SimpleReader in4 = new SimpleReader1L(
                "test/testProcessTermsHtmlExpected_Terms");
        boolean checkMap1 = compareMaps(termDef, termDefExpected);
        boolean checkMap2 = compareMaps(termLink, termLinkExpected);
        boolean checkFile1 = compareFiles(in1, in2);
        boolean checkFile2 = compareFiles(in3, in4);
        out.close();
        in1.close();
        in2.close();
        assertTrue(checkMap1);
        assertTrue(checkMap2);
        assertTrue(checkFile1);
        assertTrue(checkFile2);
    }

    @Test
    public void testProcessTermsHtml_NoOverlap() {
        String outFolder = "test/";
        SimpleWriter out = new SimpleWriter1L();
        Map<String, String> termDef = new Map2<>();
        termDef.add("hello", "a greeting");
        termDef.add("bye", "a greeting");
        Map<String, String> termDefExpected = new Map2<>();
        termDefExpected.add("hello", "a greeting");
        termDefExpected.add("bye", "a greeting");
        Map<String, String> termLink = new Map2<>();
        termLink.add("hello", "hello.html");
        termLink.add("bye", "bye.html");
        Map<String, String> termLinkExpected = new Map2<>();
        termLinkExpected.add("hello", "hello.html");
        termLinkExpected.add("bye", "bye.html");
        Glossary.processTermsHtml(termDef, termLink, outFolder);
        SimpleReader in1 = new SimpleReader1L("test/hello.html");
        SimpleReader in2 = new SimpleReader1L(
                "test/testProcessTermsHtmlExpected_Hello");
        SimpleReader in3 = new SimpleReader1L("test/bye.html");
        SimpleReader in4 = new SimpleReader1L(
                "test/testProcessTermsHtmlExpected_Bye");
        boolean checkMap1 = compareMaps(termDef, termDefExpected);
        boolean checkMap2 = compareMaps(termLink, termLinkExpected);
        boolean checkFile1 = compareFiles(in1, in2);
        boolean checkFile2 = compareFiles(in3, in4);
        out.close();
        in1.close();
        in2.close();
        assertTrue(checkMap1);
        assertTrue(checkMap2);
        assertTrue(checkFile1);
        assertTrue(checkFile2);
    }

    /*
     * Tests for generateElements(String str, Set<Character> strSet)
     */
    @Test //repeated space
    public void testGenerateElements_Space() {
        String str = " ";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add(' ');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 1;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //repeated space
    public void testGenerateElements_RepeatSpace() {
        String str = "  ";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add(' ');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 1;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //characters
    public void testGenerateElements_characters() {
        String str = "characters";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add('c');
        setExpected.add('h');
        setExpected.add('a');
        setExpected.add('r');
        setExpected.add('t');
        setExpected.add('e');
        setExpected.add('s');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 7;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //Characters
    public void testGenerateElements_Characters() {
        String str = "Characters";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add('c');
        setExpected.add('h');
        setExpected.add('a');
        setExpected.add('r');
        setExpected.add('t');
        setExpected.add('e');
        setExpected.add('s');
        setExpected.add('C');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 8;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //empty string
    public void testGenerateElements_Empty() {
        String str = "";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 0;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //seperators used
    public void testGenerateElements_Seperators() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> set = new Set2<Character>();
        Glossary.generateElements(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add('.');
        setExpected.add(',');
        setExpected.add('\t');
        setExpected.add('!');
        setExpected.add(' ');
        setExpected.add('@');
        setExpected.add('#');
        setExpected.add('$');
        setExpected.add('%');
        setExpected.add('^');
        setExpected.add('&');
        setExpected.add('*');
        setExpected.add('(');
        setExpected.add(')');
        setExpected.add('_');
        setExpected.add('+');
        setExpected.add('-');
        setExpected.add('{');
        setExpected.add('}');
        setExpected.add('/');
        setExpected.add(':');
        setExpected.add(';');
        setExpected.add('<');
        setExpected.add('>');
        setExpected.add('=');
        setExpected.add('~');
        setExpected.add('`');
        setExpected.add('[');
        setExpected.add(']');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 29;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    /*
     * Tests for nextWordOrSeparator(String text, int position, Set<Character>
     * separators)
     */
    @Test
    public void testNextWordOrSeparator_AllSeperators() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        int position = 0;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_AllWords0() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "qwertyuiopasdfghjklzxcvbnm";
        int position = 0;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "qwertyuiopasdfghjklzxcvbnm";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_AllWords10() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "qwertyuiopasdfghjklzxcvbnm";
        int position = 10;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "asdfghjklzxcvbnm";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_GoBucks0() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "Go Bucks!!!";
        int position = 0;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "Go";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_GoBucks2() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "Go Bucks!!!";
        int position = 2;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = " ";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_GoBucks3() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "Go Bucks!!!";
        int position = 3;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "Bucks";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testNextWordOrSeparator_GoBucks8() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        Glossary.generateElements(str, separators);
        String text = "Go Bucks!!!";
        int position = 8;
        String result = Glossary.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "!!!";
        assertEquals(result, resultExpected);
    }

    /*
     * Tests for addAttriTagToDef(String def, Map<String, String> termLink)
     */
    @Test
    public void testAddAttriTagToDef_term() {
        String def = "this is a term";
        Map<String, String> termLink = new Map2<>();
        termLink.add("term", "term.html");
        String result = Glossary.addAttriTagToDef(def, termLink);
        String resultExpected = "this is a <a href=\"term.html\">term</a>";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testAddAttriTagToDef_termTwice() {
        String def = "this is a term, this is also a term";
        Map<String, String> termLink = new Map2<>();
        termLink.add("term", "term.html");
        String result = Glossary.addAttriTagToDef(def, termLink);
        String resultExpected = "this is a <a href=\"term.html\">term</a>, "
                + "this is also a <a href=\"term.html\">term</a>";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testAddAttriTagToDef_2DiffTerm() {
        String def = "this is a term, this is also a term";
        Map<String, String> termLink = new Map2<>();
        termLink.add("term", "term.html");
        termLink.add("this", "this.html");
        String result = Glossary.addAttriTagToDef(def, termLink);
        String resultExpected = "<a href=\"this.html\">this</a> is a "
                + "<a href=\"term.html\">term</a>, <a href=\"this.html\">this</a> "
                + "is also a <a href=\"term.html\">term</a>";
        assertEquals(result, resultExpected);
    }

    @Test
    public void testAddAttriTagToDef_NoTerm() {
        String def = "this is a term, this is also a term";
        Map<String, String> termLink = new Map2<>();
        termLink.add("that", "that.html");
        termLink.add("yo", "yo.html");
        String result = Glossary.addAttriTagToDef(def, termLink);
        String resultExpected = "this is a term, this is also a term";
        assertEquals(result, resultExpected);
    }
}
