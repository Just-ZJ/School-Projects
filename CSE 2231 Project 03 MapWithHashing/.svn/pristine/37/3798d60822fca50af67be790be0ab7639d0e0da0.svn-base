import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Zheng Ji Tan(tan.955) & Beiwen Wu(wu.4742)
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /*
     ***************** Test cases for no argument constructors *****************
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and Call method under test
         */
        Map<String, String> n = this.constructorTest();
        Map<String, String> nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     ****************** Kernel methods - Test cases for add *****************
     */
    @Test //boundary case
    public final void testAddtoEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest();
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        /*
         * Call method under test
         */
        n.add("first", "5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //boundary case
    public final void testAddtoNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15");
        /*
         * Call method under test
         */
        n.add("second", "15");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //routine case
    public final void testAdd4KeytoNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        /*
         * Call method under test
         */
        n.add("second", "15");
        n.add("third", "85");
        n.add("fourth", "585");
        n.add("fifth", "155");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //routine case
    public final void testAdd5KeytoEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest();
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        /*
         * Call method under test
         */
        n.add("first", "5");
        n.add("second", "15");
        n.add("third", "85");
        n.add("fourth", "585");
        n.add("fifth", "155");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     ****************** Kernel methods - Test cases for remove *****************
     */
    @Test //boundary case - becomes empty map
    public final void testRemove1KeyFrom1() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef();
        String keyExpected = "first";
        String valueExpected = "5";
        /*
         * Call method under test
         */
        Pair<String, String> result = n.remove("first");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(keyExpected, result.key());
        assertEquals(valueExpected, result.value());
    }

    @Test //boundary case - becomes empty map
    public final void testRemove1KeyFrom2() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "2");
        Map<String, String> nExpected = this.createFromArgsRef("second", "2");
        String keyExpected = "first";
        String valueExpected = "5";
        /*
         * Call method under test
         */
        Pair<String, String> result = n.remove("first");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(keyExpected, result.key());
        assertEquals(valueExpected, result.value());
    }

    @Test // routine case
    public final void testRemove3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("A", "B", "C", "D", "E",
                "F", "K", "L");
        Map<String, String> mExpected = this.createFromArgsRef("K", "L");
        /*
         * Call method under test
         */
        Pair<String, String> result1 = m.remove("A");
        Pair<String, String> result2 = m.remove("C");
        Pair<String, String> result3 = m.remove("E");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("A", result1.key());
        assertEquals("C", result2.key());
        assertEquals("E", result3.key());
        assertEquals("B", result1.value());
        assertEquals("D", result2.value());
        assertEquals("F", result3.value());
        assertEquals(mExpected, m);
    }

    /*
     ****************** Kernel methods - Test cases for removeAny *****************
     */
    @Test //boundary case - becomes empty map
    public final void testRemoveAny1From1() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        /*
         * Call method under test
         */
        Pair<String, String> result = n.removeAny();
        /*
         * Set up variables
         */
        String key = result.key();
        String value = result.value();
        /*
         * Assert that nExpected contains the same pair
         */
        assertTrue(nExpected.hasKey(key));
        assertEquals(nExpected.value(key), value);
        /*
         * Remove the pair from nExpected
         */
        nExpected.remove(key);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //routine case
    public final void testRemoveAny1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("A", "a", "B", "b");
        Map<String, String> mExpected = this.createFromArgsRef("A", "a", "B",
                "b");
        /*
         * Call method under test
         */
        Pair<String, String> re = m.removeAny();
        /*
         * Set up variables
         */
        String key = re.key();
        String value = re.value();
        /*
         * Assert that mExpected contains the same pair
         */
        assertTrue(mExpected.hasKey(key));
        assertEquals(mExpected.value(key), value);
        /*
         * Remove the pair from mExpected
         */
        mExpected.remove(key);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test //challenging case
    public final void testRemoveAnyThree() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c", "D", "d", "E", "e", "F", "f");
        Map<String, String> mExpected = this.createFromArgsRef("A", "a", "B",
                "b", "C", "c", "D", "d", "E", "e", "F", "f");
        /*
         * Call method under test
         */
        Pair<String, String> re1 = m.removeAny();
        Pair<String, String> re2 = m.removeAny();
        Pair<String, String> re3 = m.removeAny();
        /*
         * Set up variables
         */
        String key1 = re1.key();
        String value1 = re1.value();
        String key2 = re2.key();
        String value2 = re2.value();
        String key3 = re3.key();
        String value3 = re3.value();
        /*
         * Assert that mExpected contains all 3 pairs
         */
        assertTrue(mExpected.hasKey(key1));
        assertEquals(mExpected.value(key1), value1);
        assertTrue(mExpected.hasKey(key2));
        assertEquals(mExpected.value(key2), value2);
        assertTrue(mExpected.hasKey(key3));
        assertEquals(mExpected.value(key3), value3);
        /*
         * Remove all 3 pair from mExpected
         */
        mExpected.remove(key1);
        mExpected.remove(key2);
        mExpected.remove(key3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test //challenging case - remove all
    public final void testRemoveAny6From6() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("A", "a", "B", "b", "C",
                "c", "D", "d", "E", "e", "F", "f");
        Map<String, String> mExpected = this.createFromArgsRef("A", "a", "B",
                "b", "C", "c", "D", "d", "E", "e", "F", "f");
        /*
         * Call method under test
         */
        Pair<String, String> re1 = m.removeAny();
        Pair<String, String> re2 = m.removeAny();
        Pair<String, String> re3 = m.removeAny();
        Pair<String, String> re4 = m.removeAny();
        Pair<String, String> re5 = m.removeAny();
        Pair<String, String> re6 = m.removeAny();
        /*
         * Set up variables
         */
        String key1 = re1.key();
        String value1 = re1.value();
        String key2 = re2.key();
        String value2 = re2.value();
        String key3 = re3.key();
        String value3 = re3.value();
        String key4 = re4.key();
        String value4 = re4.value();
        String key5 = re5.key();
        String value5 = re5.value();
        String key6 = re6.key();
        String value6 = re6.value();
        /*
         * Assert that mExpected contains all 3 pairs
         */
        assertTrue(mExpected.hasKey(key1));
        assertEquals(mExpected.value(key1), value1);
        assertTrue(mExpected.hasKey(key2));
        assertEquals(mExpected.value(key2), value2);
        assertTrue(mExpected.hasKey(key3));
        assertEquals(mExpected.value(key3), value3);
        assertTrue(mExpected.hasKey(key4));
        assertEquals(mExpected.value(key4), value4);
        assertTrue(mExpected.hasKey(key5));
        assertEquals(mExpected.value(key5), value5);
        assertTrue(mExpected.hasKey(key6));
        assertEquals(mExpected.value(key6), value6);
        /*
         * Remove all 3 pair from mExpected
         */
        mExpected.remove(key1);
        mExpected.remove(key2);
        mExpected.remove(key3);
        mExpected.remove(key4);
        mExpected.remove(key5);
        mExpected.remove(key6);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     ****************** Kernel methods - Test cases for value *****************
     */
    @Test //boundary case
    public final void testValueOf1InLength1() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        String resultExpected = "5";
        /*
         * Call method under test
         */
        String result = n.value("first");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //challenging case
    public final void testValueOfAll5InLength5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        String result1Expected = "5";
        String result2Expected = "15";
        String result3Expected = "85";
        String result4Expected = "585";
        String result5Expected = "155";
        /*
         * Call method under test
         */
        String result1 = n.value("first");
        String result2 = n.value("second");
        String result3 = n.value("third");
        String result4 = n.value("fourth");
        String result5 = n.value("fifth");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(result1Expected, result1);
        assertEquals(result2Expected, result2);
        assertEquals(result3Expected, result3);
        assertEquals(result4Expected, result4);
        assertEquals(result5Expected, result5);
    }

    @Test //routine case
    public final void testValueOf1InLength5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        String resultExpected = "15";
        /*
         * Call method under test
         */
        String result = n.value("second");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //routine case
    public final void testValueOf2InLength5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        String result1Expected = "5";
        String result2Expected = "15";
        String result5Expected = "155";
        /*
         * Call method under test
         */
        String result1 = n.value("first");
        String result2 = n.value("second");
        String result5 = n.value("fifth");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(result1Expected, result1);
        assertEquals(result2Expected, result2);
        assertEquals(result5Expected, result5);
    }

    /*
     ****************** Kernel methods - Test cases for hasKey *****************
     */
    @Test //boundary case - true
    public final void testHasKeyTrue() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        boolean resultExpected = true;
        /*
         * Call method under test
         */
        boolean result = n.hasKey("first");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //boundary case - false
    public final void testHasKeyFalseEmptyMap() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest();
        Map<String, String> nExpected = this.createFromArgsRef();
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.hasKey("first");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //boundary case - false
    public final void testHasKeyFalseNonEmptyMap() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.hasKey("second");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //routine case - true
    public final void testHasKeyTrueinLength5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        boolean resultExpected = true;
        /*
         * Call method under test
         */
        boolean result = n.hasKey("third");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //routine case - false
    public final void testHasKeyFalseinLength5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.hasKey("none");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    /*
     ****************** Kernel methods - Test cases for size *****************
     */
    @Test //boundary case
    public final void testSize0() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest();
        Map<String, String> nExpected = this.createFromArgsRef();
        int resultExpected = 0;
        /*
         * Call method under test
         */
        int result = n.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //routine case
    public final void testSize1() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5");
        int resultExpected = 1;
        /*
         * Call method under test
         */
        int result = n.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //routine case
    public final void testSize5() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("first", "5", "second",
                "15", "third", "85", "fourth", "585", "fifth", "155");
        Map<String, String> nExpected = this.createFromArgsRef("first", "5",
                "second", "15", "third", "85", "fourth", "585", "fifth", "155");
        int resultExpected = 5;
        /*
         * Call method under test
         */
        int result = n.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //challenging case
    public final void testSize119() {
        /*
         * Set up variables
         */
        Map<String, String> n = this.createFromArgsTest("1", "1", "2", "2", "3",
                "3", "4", "4", "5", "5", "6", "6", "7", "7", "8", "8", "9", "9",
                "10", "10", "11", "11", "12", "12", "13", "13", "14", "14",
                "15", "15", "16", "16", "17", "17", "18", "18", "19", "19",
                "20", "20", "21", "21", "22", "22", "23", "23", "24", "24",
                "25", "25", "26", "26", "27", "27", "28", "28", "29", "29",
                "30", "30", "31", "31", "32", "32", "33", "33", "34", "34",
                "35", "35", "36", "36", "37", "37", "38", "38", "39", "39",
                "40", "40", "41", "41", "42", "42", "43", "43", "44", "44",
                "45", "45", "46", "46", "47", "47", "48", "48", "49", "49",
                "50", "50", "51", "51", "52", "52", "53", "53", "54", "54",
                "55", "55", "56", "56", "57", "57", "58", "58", "59", "59",
                "60", "60", "61", "61", "62", "62", "63", "63", "64", "64",
                "65", "65", "66", "66", "67", "67", "68", "68", "69", "69",
                "70", "70", "71", "71", "72", "72", "73", "73", "74", "74",
                "75", "75", "76", "76", "77", "77", "78", "78", "79", "79",
                "80", "80", "81", "81", "82", "82", "83", "83", "84", "84",
                "85", "85", "86", "86", "87", "87", "88", "88", "89", "89",
                "90", "90", "91", "91", "92", "92", "93", "93", "94", "94",
                "95", "95", "96", "96", "97", "97", "98", "98", "99", "99",
                "100", "100", "101", "101", "102", "102", "103", "103", "104",
                "104", "105", "105", "106", "106", "107", "107", "108", "108",
                "109", "109", "110", "110", "111", "111", "112", "112", "113",
                "113", "114", "114", "115", "115", "116", "116", "117", "117",
                "118", "118", "119", "119");
        Map<String, String> nExpected = this.createFromArgsRef("1", "1", "2",
                "2", "3", "3", "4", "4", "5", "5", "6", "6", "7", "7", "8", "8",
                "9", "9", "10", "10", "11", "11", "12", "12", "13", "13", "14",
                "14", "15", "15", "16", "16", "17", "17", "18", "18", "19",
                "19", "20", "20", "21", "21", "22", "22", "23", "23", "24",
                "24", "25", "25", "26", "26", "27", "27", "28", "28", "29",
                "29", "30", "30", "31", "31", "32", "32", "33", "33", "34",
                "34", "35", "35", "36", "36", "37", "37", "38", "38", "39",
                "39", "40", "40", "41", "41", "42", "42", "43", "43", "44",
                "44", "45", "45", "46", "46", "47", "47", "48", "48", "49",
                "49", "50", "50", "51", "51", "52", "52", "53", "53", "54",
                "54", "55", "55", "56", "56", "57", "57", "58", "58", "59",
                "59", "60", "60", "61", "61", "62", "62", "63", "63", "64",
                "64", "65", "65", "66", "66", "67", "67", "68", "68", "69",
                "69", "70", "70", "71", "71", "72", "72", "73", "73", "74",
                "74", "75", "75", "76", "76", "77", "77", "78", "78", "79",
                "79", "80", "80", "81", "81", "82", "82", "83", "83", "84",
                "84", "85", "85", "86", "86", "87", "87", "88", "88", "89",
                "89", "90", "90", "91", "91", "92", "92", "93", "93", "94",
                "94", "95", "95", "96", "96", "97", "97", "98", "98", "99",
                "99", "100", "100", "101", "101", "102", "102", "103", "103",
                "104", "104", "105", "105", "106", "106", "107", "107", "108",
                "108", "109", "109", "110", "110", "111", "111", "112", "112",
                "113", "113", "114", "114", "115", "115", "116", "116", "117",
                "117", "118", "118", "119", "119");
        int resultExpected = 119;
        /*
         * Call method under test
         */
        int result = n.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }
}
