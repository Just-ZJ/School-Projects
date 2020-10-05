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
public final class ABCDGuesser2 {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
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
     * A method that takes a constant,w,x,y,z that the user input and calculates
     * the de Jager formula w^a*x^b*y^c*z^d, where each of a, b, c, and d is one
     * of the 17 numbers {-5, -4, -3, -2, -1, -1/2, -1/3, -1/4, 0, 1/4, 1/3,
     * 1/2, 1, 2, 3, 4, 5}. It then tries all combinations of the de Jager
     * formula and returns the best value of a,b,c,d that gives the best
     * approximation of the constant up to a relative error that is lesser than
     * 1%.
     *
     * @param constant
     *            a positive real number that the user input
     * @param w
     *            a positive real number that is not 1.0 that the user input
     * @param x
     *            a positive real number that is not 1.0 that the user input
     * @param y
     *            a positive real number that is not 1.0 that the user input
     * @param z
     *            a positive real number that is not 1.0 that the user input
     *
     * @return an array that contains the value of a,b,c,d,best approximation &
     *         relative error
     */
    private static double[] deJager(double constant, double w, double x,
            double y, double z) {

        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final double percent101 = 1.01;
        final int percent100 = 100;
        final double[] numABCD = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0.0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0,
                1.0, 2.0, 3.0, 4.0, 5.0 };

        //results=[a,b,c,d,best approx, relative error]
        final double[] results = new double[six];
        double compare = constant * percent101;
        double sum = three;

        for (int a = 0; a < numABCD.length; a++) {
            for (int b = 0; b < numABCD.length; b++) {
                for (int c = 0; c < numABCD.length; c++) {
                    for (int d = 0; d < numABCD.length; d++) {
                        sum = Math.pow(w, numABCD[a]) * Math.pow(x, numABCD[b])
                                * Math.pow(y, numABCD[c])
                                * Math.pow(z, numABCD[d]);

                        if (sum > constant && sum < compare) {
                            compare = sum;
                            results[0] = numABCD[a];
                            results[1] = numABCD[b];
                            results[2] = numABCD[c];
                            results[three] = numABCD[d];
                        }

                    }
                }
            }
        }
        results[four] = compare;
        results[five] = compare / constant * percent100 - percent100;
        return results;
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
        final int five = 5;

        //gets constant & 4 personal number from user
        out.println("What should constant μ be approximated to? ");
        double constant = getPositiveDouble(in, out);
        out.println("Pick 4 personal numbers ");
        double personalW = getPositiveDoubleNotOne(in, out);
        double personalX = getPositiveDoubleNotOne(in, out);
        double personalY = getPositiveDoubleNotOne(in, out);
        double personalZ = getPositiveDoubleNotOne(in, out);

        //finds the best approximation
        double[] results = deJager(constant, personalW, personalX, personalY,
                personalZ);

        //prints out the result
        out.println("The value of a is " + results[0]);
        out.println("The value of b is " + results[1]);
        out.println("The value of c is " + results[2]);
        out.println("The value of d is " + results[three]);
        out.println("The best approximation is " + results[four]);
        out.print("The relative error is ");
        out.print(results[five], 2, false);
        out.println("%.");

        in.close();
        out.close();

    }

}
