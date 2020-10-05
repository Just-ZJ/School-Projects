import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Zheng Ji Tan(tan.955) & Beiwen Wu(wu.4742)
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for no argument constructors
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for int constructors
     */
    @Test //single digit: 0 boundary case
    public final void testIntConstructorWith0() {
        /*
         * Set up variables and call method under test
         */
        int i = 0;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //single digit: 9  routine case
    public final void testIntConstructorWith9() {
        /*
         * Set up variables and call method under test
         */
        int i = 9;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //double digit: 00 challenging case
    public final void testIntConstructorWith00() {
        /*
         * Set up variables and call method under test
         */
        int i = 00;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //double digit: 59
    public final void testIntConstructorWith59() {
        /*
         * Set up variables and call method under test
         */
        int i = 59;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //3 digit: 579
    public final void testIntConstructorWith579() {
        /*
         * Set up variables and call method under test
         */
        int i = 579;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //4 digit: 7359 routine case
    public final void testIntConstructorWith7359() {
        /*
         * Set up variables and call method under test
         */
        int i = 7359;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //4 digit with 2 zeros infront: 00100
    public final void testIntConstructorWith00100() {
        /*
         * Set up variables and call method under test
         */
        int i = 00100;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //max integer value  challenging case
    public final void testIntConstructorWithMaxInt() {
        /*
         * Set up variables and call method under test
         */
        int i = Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for string constructors
     */
    @Test //1 digit: 0
    public final void testStringConstructorWith0() {
        /*
         * Set up variables and call method under test
         */
        String s = "0";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //2 digit: 679
    public final void testStringConstructorWith79() {
        /*
         * Set up variables and call method under test
         */
        String s = "79";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //triple digit: 679
    public final void testStringConstructorWith679() {
        /*
         * Set up variables and call method under test
         */
        String s = "679";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //max integer value: 2147483647
    public final void testStringConstructorWithMaxInt() {
        /*
         * Set up variables and call method under test
         */
        String s = "2147483647";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //over max integer value: 2147483647456
    public final void testStringConstructorWithOverMaxInt() {
        /*
         * Set up variables and call method under test
         */
        String s = "2147483647456";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for natural number constructors
     */
    @Test //single digit:0
    public final void testNNConstructorWith0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber i = new NaturalNumber2(0);
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //triple digit:159
    public final void testNNConstructorWith159() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber i = new NaturalNumber2(159);
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //max integer: 2147483647
    public final void testNNConstructorWithMaxInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber i = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //over max int: 2147483647657
    public final void testNNConstructorWithOverMaxInt() {
        /*
         * Set up variables and call method under test
         */
        String i = "2147483647657";
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for Kernel methods: multiplyBy10
     */
    @Test //0*10+0 boundary case
    public final void testMultiplyBy10Input0Add0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int num = 0;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //0*10+7 routine case
    public final void testMultiplyBy10Input0Add7() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(7);
        int num = 7;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test // 10*10+0
    public final void testMultiplyBy10Input10Add0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(100);
        int num = 0;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test //10*10+7 routine case
    public final void testMultiplyBy10Input10Add7() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(107);
        int num = 7;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test // 2147483647*10+0 challenging case
    public final void testMultiplyBy10InputMaxIntAdd0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("21474836470");
        int num = 0;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test // 2147483647*10+9
    public final void testMultiplyBy10InputMaxIntAdd9() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("21474836479");
        int num = 9;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test // 21474836478468*10+9
    public final void testMultiplyBy10InputOverMaxIntAdd9() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("21474836478468");
        NaturalNumber nExpected = this.constructorRef("214748364784689");
        int num = 9;
        /*
         * Call method under test
         */
        n.multiplyBy10(num);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for Kernel methods: divideBy10
     */
    @Test //single digit: 0 boundary case
    public final void testDivideBy10Input0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        int remExpected = 0;
        /*
         * Call method under test
         */
        int rem = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    @Test //single digit: 6
    public final void testDivideBy10Input6() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(6);
        NaturalNumber nExpected = this.constructorRef(0);
        int remExpected = 6;
        /*
         * Call method under test
         */
        int rem = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    @Test //double digit: 10
    public final void testDivideBy10Input10() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(10);
        NaturalNumber nExpected = this.constructorRef(1);
        int remExpected = 0;
        /*
         * Call method under test
         */
        int rem = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    @Test //max integer value challenging case
    public final void testDivideBy10InputMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("214748364");
        int remExpected = 7;
        /*
         * Call method under test
         */
        int rem = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    @Test //2147483647548 * 2
    public final void testDivideBy10InputOverMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647548");
        NaturalNumber nExpected = this.constructorRef("214748364754");
        int remExpected = 8;
        /*
         * Call method under test
         */
        int rem = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(remExpected, rem);
    }

    /*
     * Test cases for Kernel methods: isZero
     */
    @Test //single digit: 0 - true
    public final void testIsZeroWith0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        boolean resultExpected = true;
        /*
         * Call method under test
         */
        boolean result = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //single digit: 1 - false
    public final void testIsZeroWith1() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //max int value - false
    public final void testIsZeroWithMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("2147483647");
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test //over max int value - false
    public final void testIsZeroWithNNOverMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647487");
        NaturalNumber nExpected = this.constructorRef("2147483647487");
        boolean resultExpected = false;
        /*
         * Call method under test
         */
        boolean result = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
        assertEquals(resultExpected, result);
        assertTrue(!result);
    }

    /*
     * Test cases for Standard methods: newInstance.
     */
    @Test
    public final void testNewInstance0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        NaturalNumber result = n.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testNewInstance6() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(6);
        NaturalNumber nExpected = this.constructorRef(6);
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        NaturalNumber result = n.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testNewInstanceMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("2147483647");
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        NaturalNumber result = n.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testNewInstanceOverMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("21474836478468");
        NaturalNumber nExpected = this.constructorRef("21474836478468");
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        NaturalNumber result = n.newInstance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    /*
     * Test cases for Standard methods: clear.
     */
    @Test
    public final void testClearEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */
        n.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testClear6() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(6);
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */
        n.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testClearMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */
        n.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testClearOverMaxInt() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("21474836474586");
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Call method under test
         */
        n.clear();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * Test cases for Standard methods: transferFrom.
     */
    @Test
    public final void testTransferFromEmptyToEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest();
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromNonEmptyToNonEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(569);
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest(789);
        NaturalNumber resultExpected = this.constructorRef(569);
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromEmptyToNonEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest(769);
        NaturalNumber resultExpected = this.constructorRef();
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromNonEmptyToEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(56467);
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest();
        NaturalNumber resultExpected = this.constructorRef(56467);
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromMaxIntToEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest();
        NaturalNumber resultExpected = this.constructorRef("2147483647");
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromMaxIntToNonEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest("6548");
        NaturalNumber resultExpected = this.constructorRef("2147483647");
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromOverMaxIntToEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("214748364745");
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest();
        NaturalNumber resultExpected = this.constructorRef("214748364745");
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }

    @Test
    public final void testTransferFromOverMaxIntToNonEmpty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("214748364745");
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber result = this.constructorTest("2168");
        NaturalNumber resultExpected = this.constructorRef("214748364745");
        /*
         * Call method under test
         */
        result.transferFrom(n);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(resultExpected, result);
    }
}
