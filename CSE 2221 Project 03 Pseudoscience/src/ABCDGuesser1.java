import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zheng Ji Tan
 *
 */
public final class ABCDGuesser1 {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        String userInput = "h";
        double num = -1.0;

        while (num <= 0 || !FormatChecker.canParseDouble(userInput)) {
            out.print("Enter a positive real number: ");
            userInput = in.nextLine();
            if (FormatChecker.canParseDouble(userInput)) {
                num = Double.parseDouble(userInput);
            }
        }
        return num;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        String userInput = "h";
        double num = -1.0;

        while (num <= 0 || !FormatChecker.canParseDouble(userInput)
                || num == 1.0) {
            out.print("Enter a positive real number other than 1.0: ");
            userInput = in.nextLine();
            if (FormatChecker.canParseDouble(userInput)) {
                num = Double.parseDouble(userInput);
            }
        }
        return num;
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

        final int three = 3;
        final int four = 4;
        final double percent101 = 1.01;
        final double[] numABCD = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0.0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0,
                1.0, 2.0, 3.0, 4.0, 5.0 };

        //gets constant & 4 personal number from user
        out.println("What should constant μ be approximated to? ");
        double constant = getPositiveDouble(in, out);
        out.println("Pick 4 personal numbers ");
        double personalW = getPositiveDoubleNotOne(in, out);
        double personalX = getPositiveDoubleNotOne(in, out);
        double personalY = getPositiveDoubleNotOne(in, out);
        double personalZ = getPositiveDoubleNotOne(in, out);

        double sum = three;
        double[] indexABCD = new double[four];
        double compare = constant * percent101;
        int a = 0, b = 0, c = 0, d = 0;

        //finds the best approximation
        while (a < numABCD.length) {
            b = 0;
            while (b < numABCD.length) {
                c = 0;
                while (c < numABCD.length) {
                    d = 0;
                    while (d < numABCD.length) {

                        sum = Math.pow(personalW, numABCD[a])
                                * Math.pow(personalX, numABCD[b])
                                * Math.pow(personalY, numABCD[c])
                                * Math.pow(personalZ, numABCD[d]);

                        if (sum > constant && sum < compare) {
                            compare = sum;
                            indexABCD[0] = numABCD[a];
                            indexABCD[1] = numABCD[b];
                            indexABCD[2] = numABCD[c];
                            indexABCD[three] = numABCD[d];
                        }
                        d++;
                    }
                    c++;
                }
                b++;
            }
            a++;
        }

        //prints out the result
        final double relError = compare / constant * 100 - 100;
        out.println("The value of a is " + indexABCD[0]);
        out.println("The value of b is " + indexABCD[1]);
        out.println("The value of c is " + indexABCD[2]);
        out.println("The value of d is " + indexABCD[three]);
        out.println("The best approximation is " + compare);
        out.print("The relative error is ");
        out.print(relError, 2, false);
        out.println("%.");
        in.close();
        out.close();
    }

}
