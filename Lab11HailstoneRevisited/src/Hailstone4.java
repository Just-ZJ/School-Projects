import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zheng Ji Tan
 *
 */
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        final NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber x = new NaturalNumber2();
        NaturalNumber max = new NaturalNumber2(0);
        int count = 1;

        x.copyFrom(n);

        out.print(n + " ");
        /*
         * finds the hailstone series
         */
        while (!n.equals(one)) {
            if (n.divide(two).equals(zero)) {
                x.divide(two);
                out.print(n + " ");
            } else {
                x.multiply(three);
                x.add(one);
                out.print(n + " ");
            }
            /*
             * finds maximum value
             */
            if (max.compareTo(x) < 0) {
                max.copyFrom(x);
            }
            n.copyFrom(x);
            count++;
        }
        out.println();
        out.println("The length of the series is " + count);
        out.println("The maximum value of the series is " + max);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber largestVal = new NaturalNumber2(0);
        NaturalNumber smallestStart = new NaturalNumber2(0);

        out.print("Enter a positive integer: ");
        NaturalNumber n = new NaturalNumber2(in.nextLine());
        while (!n.equals(zero)) {
            generateSeries(n, out);
            out.println();
            out.print("Enter a positive integer: ");
            if (largestVal.compareTo(n) < 0) {
                largestVal.copyFrom(n);
                smallestStart.copyFrom(n);
            }
            n = new NaturalNumber2(in.nextLine());
        }

        in.close();
        out.close();
    }

}
