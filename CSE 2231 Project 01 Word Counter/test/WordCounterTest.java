import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class WordCounterTest {

    /**
     * A function that checks whether both maps have the same key and key
     * values.
     *
     * @param map1
     *            a word -> count map
     * @param map2
     *            a word -> count map
     * @return boolean
     * @ensures If both maps have the same key and key values, it will return
     *          true. Else, it will return false
     */
    public static boolean compareMap(Map<String, Integer> map1,
            Map<String, Integer> map2) {
        boolean same = true;
        for (Map.Pair<String, Integer> keyPair : map1) {
            String keyName = keyPair.key();
            if (!map2.hasKey(keyName)
                    && map2.value(keyName) != keyPair.value()) {
                same = false;
            }
        }
        return same;
    }

    /**
     * Tests for outputHtmlHeader(SimpleWriter out, String title).
     */
    @Test
    public void testOutputHtmlHeader() {
        SimpleWriter out = new SimpleWriter1L("test/testOutputHtmlHeader");
        String title = "TITLE";
        WordCounter.outputHtmlHeader(out, title);
        SimpleReader in = new SimpleReader1L("test/testOutputHtmlHeader");
        SimpleReader inExpected = new SimpleReader1L(
                "test/testOutputHtmlHeaderExpected");
        boolean sameInput = true;
        while (!inExpected.atEOS()) {
            String line = in.nextLine();
            String lineExpected = inExpected.nextLine();
            if (!lineExpected.equals(line)) {
                sameInput = false;
            }
        }
        in.close();
        inExpected.close();
        out.close();
        assertTrue(sameInput);
    }

    /**
     * Tests for outputHtmlBody(SimpleWriter out, Queue<String> orderedKeys,
     * Map<String, Integer> wordCount).
     */
    @Test
    public void testOutputHtmlBody_3Word() {
        SimpleWriter out = new SimpleWriter1L("test/testOutputHtmlBody_3Word");
        Queue<String> orderedKeys = new Queue1L<>();
        orderedKeys.enqueue("A");
        orderedKeys.enqueue("B");
        orderedKeys.enqueue("C");
        Map<String, Integer> wordCount = new Map1L<>();
        wordCount.add("A", 5);
        wordCount.add("B", 2);
        wordCount.add("C", 8);
        WordCounter.outputHtmlBody(out, orderedKeys, wordCount);
        SimpleReader in = new SimpleReader1L("test/testOutputHtmlBody_3Word");
        SimpleReader inExpected = new SimpleReader1L(
                "test/testOutputHtmlBodyExpected_3Word");
        boolean sameInput = true;
        while (!inExpected.atEOS()) {
            String line = in.nextLine();
            String lineExpected = inExpected.nextLine();
            if (!lineExpected.equals(line)) {
                sameInput = false;
            }
        }
        in.close();
        inExpected.close();
        out.close();
        assertTrue(sameInput);
    }

    @Test
    public void testOutputHtmlBody_NoWord() {
        SimpleWriter out = new SimpleWriter1L("test/testOutputHtmlBody_NoWord");
        Queue<String> orderedKeys = new Queue1L<>();
        Map<String, Integer> wordCount = new Map1L<>();
        WordCounter.outputHtmlBody(out, orderedKeys, wordCount);
        SimpleReader in = new SimpleReader1L("test/testOutputHtmlBody_NoWord");
        SimpleReader inExpected = new SimpleReader1L(
                "test/testOutputHtmlBodyExpected_NoWord");
        boolean sameInput = true;
        while (!inExpected.atEOS()) {
            String line = in.nextLine();
            String lineExpected = inExpected.nextLine();
            if (!lineExpected.equals(line)) {
                sameInput = false;
            }
        }
        in.close();
        inExpected.close();
        out.close();
        assertTrue(sameInput);
    }

    /**
     * Tests for outputHtmlFooter(SimpleWriter out).
     */
    @Test
    public void testOutputHtmlFooter() {
        SimpleWriter out = new SimpleWriter1L("test/testOutputHtmlFooter");
        WordCounter.outputHtmlFooter(out);
        SimpleReader in = new SimpleReader1L("test/testOutputHtmlFooter");
        SimpleReader inExpected = new SimpleReader1L(
                "test/testOutputHtmlFooterExpected");
        boolean sameInput = true;
        while (!inExpected.atEOS()) {
            String line = in.nextLine();
            String lineExpected = inExpected.nextLine();
            if (!lineExpected.equals(line)) {
                sameInput = false;
            }
        }
        in.close();
        inExpected.close();
        out.close();
        assertTrue(sameInput);
    }

    /**
     * Tests for generateSeparator(String separatorStr, Set<Character>
     * separator).
     */
    @Test //repeated space
    public void testGenerateSeparator_Space() {
        String str = " ";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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

    @Test
    public void testGenerateSeparator_Tab() {
        String str = "\t";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add('\t');
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

    @Test
    public void testGenerateSeparator_SpaceTab() {
        String str = " \t";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
        Set<Character> setExpected = new Set2<Character>();
        setExpected.add('\t');
        setExpected.add(' ');
        boolean contains = true;
        for (Character x : setExpected) {
            if (!set.contains(x)) {
                contains = false;
            }
        }
        int len = set.size();
        int lenExpected = 2;
        assertEquals(len, lenExpected);
        assertTrue(contains);
    }

    @Test //2 space
    public void testGenerateSeparator_2Space() {
        String str = "  ";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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
    public void testGenerateSeparator_characters() {
        String str = "characters";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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
    public void testGenerateSeparator_Characters() {
        String str = "Characters";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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
    public void testGenerateSeparator_Empty() {
        String str = "";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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

    @Test //separators used
    public void testGenerateSeparator_Seperators() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> set = new Set2<Character>();
        WordCounter.generateSeparator(str, set);
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

    /**
     * Tests for nextWordOrSeparator(String text, int position, Set<Character>
     * separators).
     */
    @Test //input has no words
    public void testNextWordOrSeparator_AllSeperators() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        int position = 0;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        assertEquals(result, resultExpected);
    }

    @Test //input has no separators.  start at index 0
    public void testNextWordOrSeparator_AllWords0() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "qwertyuiopasdfghjklzxcvbnm";
        int position = 0;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "qwertyuiopasdfghjklzxcvbnm";
        assertEquals(result, resultExpected);
    }

    @Test //input has no separators. start at index 10
    public void testNextWordOrSeparator_AllWords10() {
        String str = "., \t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "qwertyuiopasdfghjklzxcvbnm";
        int position = 10;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "asdfghjklzxcvbnm";
        assertEquals(result, resultExpected);
    }

    @Test //input has words & separators. start at index 0
    public void testNextWordOrSeparator_GoBucks0() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "Go Bucks!!!";
        int position = 0;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "Go";
        assertEquals(result, resultExpected);
    }

    @Test //input has words & separators. start at index 2
    public void testNextWordOrSeparator_GoBucks2() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "Go Bucks!!!";
        int position = 2;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = " ";
        assertEquals(result, resultExpected);
    }

    @Test //input has words & separators. start at index 3
    public void testNextWordOrSeparator_GoBucks3() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "Go Bucks!!!";
        int position = 3;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "Bucks";
        assertEquals(result, resultExpected);
    }

    @Test //input has words & separators. start at index 8
    public void testNextWordOrSeparator_GoBucks8() {
        String str = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(str, separators);
        String text = "Go Bucks!!!";
        int position = 8;
        String result = WordCounter.nextWordOrSeparator(text, position,
                separators);
        String resultExpected = "!!!";
        assertEquals(result, resultExpected);
    }

    /**
     * Tests for processLine(String line, Set<Character> separators, Map<String,
     * Integer> wordCount).
     */
    @Test //input has no words
    public void testProcessLine_NoWord() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "= : =";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has 1 word
    public void testProcessLine_1Word() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "hello";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("hello", 1);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has 2 word
    public void testProcessLine_2Word() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "= hello = world";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("hello", 1);
        wordCountExpected.add("world", 1);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has 1 word repeated twice
    public void testProcessLine_1WordRepeatedTwice() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "= hello == hello =";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("hello", 2);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has 1 word repeated 10times
    public void testProcessLine_1WordRepeated10() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = " hello hello hello hello hello hello hello hello hello hello";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("hello", 10);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has 2 word repeated twice
    public void testProcessLine_2WordRepeatedTwice() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "= hello world..world  =  hello";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("hello", 2);
        wordCountExpected.add("world", 2);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }

    @Test //input has multiple word with upper and lowercase This
    public void testProcessLine_MultipleWord() {
        String separatorStr = ".,\t! @#$%^&*()_+-{}/:;<>=~`[]";
        Set<Character> separators = new Set2<Character>();
        WordCounter.generateSeparator(separatorStr, separators);
        String line = "This is just a random sentence that i have to write like this";
        Map<String, Integer> wordCount = new Map1L<>();
        Map<String, Integer> wordCountExpected = new Map1L<>();
        wordCountExpected.add("This", 1);
        wordCountExpected.add("is", 1);
        wordCountExpected.add("just", 1);
        wordCountExpected.add("a", 1);
        wordCountExpected.add("random", 1);
        wordCountExpected.add("sentence", 1);
        wordCountExpected.add("that", 1);
        wordCountExpected.add("i", 1);
        wordCountExpected.add("have", 1);
        wordCountExpected.add("to", 1);
        wordCountExpected.add("write", 1);
        wordCountExpected.add("like", 1);
        wordCountExpected.add("this", 1);
        int size = wordCount.size();
        int sizeExpected = 0;
        boolean sameMap = compareMap(wordCount, wordCountExpected);
        WordCounter.processLine(line, separators, wordCount);

        assertTrue(sameMap);
        assertEquals(size, sizeExpected);
    }
}
