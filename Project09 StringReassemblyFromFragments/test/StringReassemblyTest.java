import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests for String combination(String str1, String str2, int overlap)
     */
    @Test //overlap 1 letter
    public void testCombination_AT_TA() {
        String s1 = "AT";
        String s2 = "TA";
        String combine = StringReassembly.combination(s1, s2, 1);
        String combineExpected = "ATA";
        assertTrue(combine.equals(combineExpected));
    }

    @Test //no overlap
    public void testCombination_A_BC() {
        String s1 = "A";
        String s2 = "BC";
        String combine = StringReassembly.combination(s1, s2, 0);
        String combineExpected = "ABC";
        assertTrue(combine.equals(combineExpected));
    }

    @Test //more than 1 overlap
    public void testCombination_ATATBC_TBCAST() {
        String s1 = "ATATBC";
        String s2 = "TBCAST";
        String combine = StringReassembly.combination(s1, s2, 3);
        String combineExpected = "ATATBCAST";
        assertTrue(combine.equals(combineExpected));
    }

    @Test //flip: no more overlap
    public void testCombination_TBCAST_ATATBC() {
        String s1 = "TBCAST";
        String s2 = "ATATBC";
        String combine = StringReassembly.combination(s1, s2, 0);
        String combineExpected = "TBCASTATATBC";
        assertTrue(combine.equals(combineExpected));
    }

    @Test //overlap 1
    public void testCombination_TBCAST_TBCASE() {
        String s1 = "TBCAST";
        String s2 = "TBCASE";
        String combine = StringReassembly.combination(s1, s2, 1);
        String combineExpected = "TBCASTBCASE";
        assertTrue(combine.equals(combineExpected));
    }

    @Test //overlap all but last
    public void testCombination_TBCAST_BCASTS() {
        String s1 = "TBCAST";
        String s2 = "BCASTS";
        String combine = StringReassembly.combination(s1, s2, 5);
        String combineExpected = "TBCASTS";
        assertEquals(combine, combineExpected);
    }

    /**
     * Construct and return a {@code Set<String>} containing the given
     * {@code String}s.
     *
     * @param args
     *            the {@code String}s to put in the set
     * @return {@code Set<String>} of the given {@code String}s
     * @ensures createFromArgs = [the Set<String> of the given Strings]
     */
    private static Set<String> createFromArgs(String... args) {
        Set<String> set = new Set2<String>();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    /*
     * Tests for void addToSetAvoidingSubstrings(Set<String> strSet,
     */
    @Test //is a substring of one of the element
    public void testAddToSetAvoidingSubstrings_S2SubEle() {
        Set<String> set1 = createFromArgs("hello");
        String s2 = "hell";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 1;
        assertTrue(!set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //not a substring of any element
    public void testAddToSetAvoidingSubstrings_NotSubstring() {
        Set<String> set1 = createFromArgs("hello");
        String s2 = "yolo";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 2;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //exactly one of the element
    public void testAddToSetAvoidingSubstrings_ExactlySame() {
        Set<String> set1 = createFromArgs("hello", "yes");
        String s2 = "hello";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 2;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //element is substring of s2
    public void testAddToSetAvoidingSubstrings_EleSubS2() {
        Set<String> set1 = createFromArgs("hello", "yes");
        String s2 = "hellosir";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 2;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //2element is substring of s2
    public void testAddToSetAvoidingSubstrings_2EleSubS2() {
        Set<String> set1 = createFromArgs("hello", "yes", "hellos");
        String s2 = "hellosir";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 2;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //try with caps
    public void testAddToSetAvoidingSubstrings_Caps() {
        Set<String> set1 = createFromArgs("hello", "yes", "hellos");
        String s2 = "hellOsir";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 4;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //substring in middle
    public void testAddToSetAvoidingSubstrings_MidSubstring() {
        Set<String> set1 = createFromArgs("hello", "yes");
        String s2 = "el";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 2;
        assertTrue(!set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    @Test //all substring of s2
    public void testAddToSetAvoidingSubstrings_AllSubstringOfS2() {
        Set<String> set1 = createFromArgs("eye", "uck", "Buck");
        String s2 = "Buckeyes";
        StringReassembly.addToSetAvoidingSubstrings(set1, s2);
        int len = set1.size();
        int lenExpected = 1;
        assertTrue(set1.contains(s2));
        assertEquals(len, lenExpected);
    }

    /*
     * Tests for Set<String> linesFromInput(SimpleReader input)
     */
    @Test //cheer.txt
    public void testLinesFromInput_Cheer() {
        SimpleReader input = new SimpleReader1L("cheer.txt");
        Set<String> set1 = StringReassembly.linesFromInput(input);
        int len = set1.size();
        int lenExpected = 5;
        assertEquals(len, lenExpected);
    }

    @Test //2lines of hello
    public void testLinesFromInput_2Hello() {
        SimpleReader input = new SimpleReader1L(
                "test/testLinesFromInput_2Hello.txt");
        Set<String> set1 = StringReassembly.linesFromInput(input);
        int len = set1.size();
        int lenExpected = 1;
        assertEquals(len, lenExpected);
        assertTrue(set1.contains("Hello"));
    }

    @Test //1 line of yes
    public void testLinesFromInput_Yes() {
        SimpleReader input = new SimpleReader1L(
                "test/testLinesFromInput_Yes.txt");
        Set<String> set1 = StringReassembly.linesFromInput(input);
        int len = set1.size();
        int lenExpected = 1;
        assertEquals(len, lenExpected);
        assertTrue(set1.contains("Yes"));
    }

    @Test //3 line of yes
    public void testLinesFromInput_3Yes() {
        SimpleReader input = new SimpleReader1L(
                "test/testLinesFromInput_3Yes.txt");
        Set<String> set1 = StringReassembly.linesFromInput(input);
        int len = set1.size();
        int lenExpected = 1;
        assertEquals(len, lenExpected);
        assertTrue(set1.contains("Yes"));
    }

    @Test //2 line of yes+1line of hello
    public void testLinesFromInput_2Yes_1Hello() {
        SimpleReader input = new SimpleReader1L(
                "test/testLinesFromInput_2Yes_1Hello.txt");
        Set<String> set1 = StringReassembly.linesFromInput(input);
        int len = set1.size();
        int lenExpected = 2;
        assertEquals(len, lenExpected);
        assertTrue(set1.contains("Hello"));
        assertTrue(set1.contains("Yes"));
    }

    /*
     * Tests for void printWithLineSeparators(String text, SimpleWriter out)
     */
    @Test //1 "~" in middle
    public void testPrintWithLineSeparators_1CurvyInMiddle() {
        SimpleWriter out = new SimpleWriter1L("test/Result_1CurvyInMiddle.txt");
        SimpleReader inResult = new SimpleReader1L(
                "test/Result_1CurvyInMiddle.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_1CurvyInMiddle.txt");
        String txt = "1~InMiddle";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 2;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //2 "~" in middle
    public void testPrintWithLineSeparators_2CurvyInMiddle() {
        SimpleWriter out = new SimpleWriter1L("test/Result_2CurvyInMiddle.txt");
        SimpleReader inResult = new SimpleReader1L(
                "test/Result_2CurvyInMiddle.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_2CurvyInMiddle.txt");
        String txt = "2~~InMiddle";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 3;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //1 "~" at front
    public void testPrintWithLineSeparators_1CurvyFront() {
        SimpleWriter out = new SimpleWriter1L("test/Result_1CurvyFront.txt");
        SimpleReader inResult = new SimpleReader1L(
                "test/Result_1CurvyFront.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_1CurvyFront.txt");
        String txt = "~1CurvyFront";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 2;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //2 "~" at front
    public void testPrintWithLineSeparators_2CurvyFront() {
        SimpleWriter out = new SimpleWriter1L("test/Result_2CurvyFront.txt");
        SimpleReader inResult = new SimpleReader1L(
                "test/Result_2CurvyFront.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_2CurvyFront.txt");
        String txt = "~~2CurvyFront";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 3;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //1 "~" at end
    public void testPrintWithLineSeparators_1CurvyEnd() {
        SimpleWriter out = new SimpleWriter1L("test/Result_1CurvyEnd.txt");
        SimpleReader inResult = new SimpleReader1L("test/Result_1CurvyEnd.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_1CurvyEnd.txt");
        String txt = "1CurvyEnd~";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 1;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //2 "~" at end
    public void testPrintWithLineSeparators_2CurvyEnd() {
        SimpleWriter out = new SimpleWriter1L("test/Result_2CurvyEnd.txt");
        SimpleReader inResult = new SimpleReader1L("test/Result_2CurvyEnd.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_2CurvyEnd.txt");
        String txt = "2CurvyEnd~~";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 2;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //2 seperated "~" in middle
    public void testPrintWithLineSeparators_2CurvyMidSeperated() {
        SimpleWriter out = new SimpleWriter1L(
                "test/Result_2CurvyMidSeperated.txt");
        SimpleReader inResult = new SimpleReader1L(
                "test/Result_2CurvyMidSeperated.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_2CurvyMidSeperated.txt");
        String txt = "2~Mid~Seperated";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 3;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }

    @Test //no ~
    public void testPrintWithLineSeparators_NoCurvy() {
        SimpleWriter out = new SimpleWriter1L("test/Result_NoCurvy.txt");
        SimpleReader inResult = new SimpleReader1L("test/Result_NoCurvy.txt");
        SimpleReader inExpected = new SimpleReader1L(
                "test/Expected_NoCurvy.txt");
        String txt = "No Curvy";
        StringReassembly.printWithLineSeparators(txt, out);
        /*
         * compares both files
         */
        boolean pass = true;
        int resultCount = 0;
        int resultCountExpected = 1;
        while (!inExpected.atEOS()) {
            String tmp = inResult.nextLine();
            String tmp2 = inExpected.nextLine();
            if (!tmp.equals(tmp2)) {
                pass = false;
            }
            resultCount++;
        }
        out.close();
        inResult.close();
        inExpected.close();

        assertTrue(pass);
        assertEquals(resultCount, resultCountExpected);
    }
}
