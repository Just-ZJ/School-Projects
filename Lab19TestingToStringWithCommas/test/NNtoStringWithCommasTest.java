import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {

        return NNtoStringWithCommas1.toStringWithCommas(n);
//        return NaturalNumberStaticOps.toStringWithCommas(n);
    }

    @Test
    public void testComma0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "0";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) >= 0); //n should be >=0
        assertTrue(n.compareTo(tmp) == 0); //value of n should not be changed
        assertTrue(commas.equals(result)); //output should be correct
    }

    @Test
    public void testComma2147483647() {
        /*
         * Set up variables and call method under test
         */
        final int a = 2147483647;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "2,147,483,647";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma10() {
        /*
         * Set up variables and call method under test
         */
        final int a = 10;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "10";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma100() {
        /*
         * Set up variables and call method under test
         */
        final int a = 100;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "100";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma1000() {
        /*
         * Set up variables and call method under test
         */
        final int a = 1000;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "1,000";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma10000() {
        /*
         * Set up variables and call method under test
         */
        final int a = 10000;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "10,000";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma100000() {
        /*
         * Set up variables and call method under test
         */
        final int a = 100000;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "100,000";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma1000000() {
        /*
         * Set up variables and call method under test
         */
        final int a = 1000000;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "1,000,000";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }

    @Test
    public void testComma999999() {
        /*
         * Set up variables and call method under test
         */
        final int a = 999999;
        NaturalNumber n = new NaturalNumber2(a);
        NaturalNumber tmp = new NaturalNumber2(n);
        String commas = redirectToMethodUnderTest(n);
        String result = "999,999";
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(n.compareTo(new NaturalNumber2(0)) > 0);
        assertTrue(n.compareTo(tmp) == 0);
        assertTrue(commas.equals(result));
    }
}
