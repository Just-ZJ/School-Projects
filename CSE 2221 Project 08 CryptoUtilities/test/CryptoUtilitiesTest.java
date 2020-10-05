import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Zheng Ji Tan
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of randomNumber
     */
    @Test //smallest number
    public void testRandomNumber_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber random = CryptoUtilities.randomNumber(n);
        assertEquals(nExpected, n);
        assertTrue(random.compareTo(zero) >= 0);
        assertTrue(random.compareTo(n) <= 0);
    }

    @Test
    public void testRandomNumber_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber random = CryptoUtilities.randomNumber(n);
        assertEquals(nExpected, n);
        assertTrue(random.compareTo(zero) >= 0);
        assertTrue(random.compareTo(n) <= 0);
    }

    @Test //large number
    public void testRandomNumber_7527786() {
        NaturalNumber n = new NaturalNumber2(7527786);
        NaturalNumber nExpected = new NaturalNumber2(7527786);
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber random = CryptoUtilities.randomNumber(n);
        assertEquals(nExpected, n);
        assertTrue(random.compareTo(zero) >= 0);
        assertTrue(random.compareTo(n) <= 0);
    }

    /*
     * Tests of reduceToGCD
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //flipped: n < m
    public void testReduceToGCD_21_30() {
        NaturalNumber n = new NaturalNumber2(21);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(30);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //prime number(7)
    public void testReduceToGCD_30_7() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //flipped: prime number(7)
    public void testReduceToGCD_7_30() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(30);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //prime number 13
    public void testReduceToGCD_26_13() {
        NaturalNumber n = new NaturalNumber2(26);
        NaturalNumber nExpected = new NaturalNumber2(13);
        NaturalNumber m = new NaturalNumber2(13);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //one number with 0
    public void testReduceToGCD_33_0() {
        NaturalNumber n = new NaturalNumber2(33);
        NaturalNumber nExpected = new NaturalNumber2(33);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //2 prime number
    public void testReduceToGCD_33_7() {
        NaturalNumber n = new NaturalNumber2(33);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //number 1
    public void testReduceToGCD_458_1() {
        NaturalNumber n = new NaturalNumber2(458);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(1);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //2 large numbers
    public void testReduceToGCD_6549816_68411787() {
        NaturalNumber n = new NaturalNumber2(6549816);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(68411787);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test //routine
    public void testReduceToGCD_45_54() {
        NaturalNumber n = new NaturalNumber2(45);
        NaturalNumber nExpected = new NaturalNumber2(9);
        NaturalNumber m = new NaturalNumber2(54);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_9() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test //routine: basic 1-10
    public void testIsEven_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //large even number
    public void testIsEven_65445116() {
        NaturalNumber n = new NaturalNumber2(65445116);
        NaturalNumber nExpected = new NaturalNumber2(65445116);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test //large odd number
    public void testIsEven_65494167() {
        NaturalNumber n = new NaturalNumber2(65494167);
        NaturalNumber nExpected = new NaturalNumber2(65494167);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test //mod prime
    public void testPowerMod_8_3_5() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(3);
        NaturalNumber pExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber mExpected = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test //prime numbers
    public void testPowerMod_7_7_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(7);
        NaturalNumber pExpected = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_4523_54338_4() {
        NaturalNumber n = new NaturalNumber2(4523);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(54338);
        NaturalNumber pExpected = new NaturalNumber2(54338);
        NaturalNumber m = new NaturalNumber2(4);
        NaturalNumber mExpected = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test //power 0
    public void testPowerMod_4523_0_4() {
        NaturalNumber n = new NaturalNumber2(4523);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(4);
        NaturalNumber mExpected = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test //power 1
    public void testPowerMod_4523_1_4() {
        NaturalNumber n = new NaturalNumber2(4523);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(4);
        NaturalNumber mExpected = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test //power 2
    public void testPowerMod_4565423_2_7() {
        NaturalNumber n = new NaturalNumber2(4565423);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber pExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * isWitnessToCompositeness
     */
    @Test //smallest w & n not prime
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, true);
    }

    @Test //smallest w & n is prime
    public void testIsWitnessToCompositeness_2_5() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, false);
    }

    @Test //large w not prime & n not prime
    public void testIsWitnessToCompositeness_453782_7563737() {
        NaturalNumber w = new NaturalNumber2(453782);
        NaturalNumber wExpected = new NaturalNumber2(453782);
        NaturalNumber n = new NaturalNumber2(7563737);
        NaturalNumber nExpected = new NaturalNumber2(7563737);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, true);
    }

    @Test //large w & n is prime
    public void testIsWitnessToCompositeness_453782_7563761() {
        NaturalNumber w = new NaturalNumber2(453782);
        NaturalNumber wExpected = new NaturalNumber2(453782);
        NaturalNumber n = new NaturalNumber2(7563761);
        NaturalNumber nExpected = new NaturalNumber2(7563761);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, false);
    }

    @Test //w is prime & n is not prime
    public void testIsWitnessToCompositeness_7_4532() {
        NaturalNumber w = new NaturalNumber2(7);
        NaturalNumber wExpected = new NaturalNumber2(7);
        NaturalNumber n = new NaturalNumber2(4532);
        NaturalNumber nExpected = new NaturalNumber2(4532);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, true);
    }

    @Test //w is prime & n prime
    public void testIsWitnessToCompositeness_11_19() {
        NaturalNumber w = new NaturalNumber2(11);
        NaturalNumber wExpected = new NaturalNumber2(11);
        NaturalNumber n = new NaturalNumber2(19);
        NaturalNumber nExpected = new NaturalNumber2(19);
        boolean witness = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(witness, false);
    }

    /*
     * Tests of isPrime1
     */
    @Test //n=2, smallest, prime
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=3, prime
    public void testIsPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=4, not prime
    public void testIsPrime1_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=5, prime
    public void testIsPrime1_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=6, prime
    public void testIsPrime1_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=7, prime
    public void testIsPrime1_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=8, prime
    public void testIsPrime1_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=9, not prime
    public void testIsPrime1_9() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, not prime but even
    public void testIsPrime1_9984652() {
        NaturalNumber n = new NaturalNumber2(9984652);
        NaturalNumber nExpected = new NaturalNumber2(9984652);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, not prime but odd
    public void testIsPrime1_9984651() {
        NaturalNumber n = new NaturalNumber2(9984651);
        NaturalNumber nExpected = new NaturalNumber2(9984651);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, prime but odd
    public void testIsPrime1_9984673() {
        NaturalNumber n = new NaturalNumber2(9984673);
        NaturalNumber nExpected = new NaturalNumber2(9984673);
        boolean isPrime = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    /*
     * Tests of isPrime2. same test cases as is prime 1
     */
    @Test //n=2, smallest, prime
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=3, prime
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=4, not prime
    public void testIsPrime2_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=5, prime
    public void testIsPrime2_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=6, prime
    public void testIsPrime2_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=7, prime
    public void testIsPrime2_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    @Test //n=8, prime
    public void testIsPrime2_8() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //n=9, not prime
    public void testIsPrime2_9() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, not prime but even
    public void testIsPrime2_9984652() {
        NaturalNumber n = new NaturalNumber2(9984652);
        NaturalNumber nExpected = new NaturalNumber2(9984652);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, not prime but odd
    public void testIsPrime2_9984651() {
        NaturalNumber n = new NaturalNumber2(9984651);
        NaturalNumber nExpected = new NaturalNumber2(9984651);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, false);
    }

    @Test //large n, prime but odd
    public void testIsPrime2_9984673() {
        NaturalNumber n = new NaturalNumber2(9984673);
        NaturalNumber nExpected = new NaturalNumber2(9984673);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(isPrime, true);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    @Test //smallest n, prime
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //n=3, prime
    public void testGenerateNextLikelyPrime_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //n=4, not prime
    public void testGenerateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //n=6, not prime
    public void testGenerateNextLikelyPrime_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //n=7, prime
    public void testGenerateNextLikelyPrime_7() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //n=9, not prime and odd
    public void testGenerateNextLikelyPrime_9() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test //large number
    public void testGenerateNextLikelyPrime_4567648() {
        NaturalNumber n = new NaturalNumber2(4567648);
        NaturalNumber nExpected = new NaturalNumber2(4567649);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
