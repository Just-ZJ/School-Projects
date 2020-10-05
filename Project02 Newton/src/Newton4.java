import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that requests the user to enter the value of relative error a
 * positive double and it would calculate the square root of the double to
 * within a relative error that the user input. Then, it would repeatedly ask
 * the user if the user wants to calculate another root. If the response is "y",
 * then the program would proceed. if it is anything else, then the program
 * would quit. Whenever it proceeds, the program would prompt the user for a
 * number(a positive double or 0) and then report the square root of that number
 * to within a relative error that the user input.
 *
 *
 * @author Zheng Ji Tan
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            zero or positive number to compute square root of
     * @param err
     *            the relative error that the user input
     *
     * @return estimate of square root
     */
    private static double sqrt(double x, double err) {
        double r = x;
        if (x == 0) {
            r = 0;
        } else {
            r = (r + x / r) / 2;
            final double errorMargin = err / 100.0;
            while (Math.abs(r * r - x) / x >= errorMargin * errorMargin) {
                r = (r + x / r) / 2;
            }
        }

        return r;
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

        /*
         * Requests the user to enter the value for relative error and a
         * positive double and prints out the square root of the double.
         */
        out.print("Please enter a relative error(%): ");
        double relErr = in.nextDouble();
        out.print("Please enter a positive double: ");
        double num = in.nextDouble();
        /*
         * If the user enters zero or a positive value, the program would
         * calculate the square root of it. if a negative value is entered, the
         * program ends.
         */
        double sqrtNum;
        while (num >= 0) {
            sqrtNum = sqrt(num, relErr);
            out.println("The square root of " + num + " is " + sqrtNum);
            out.print("Please enter a positive double: ");
            num = in.nextDouble();
        }
        out.println("The program will now end.");
        in.close();
        out.close();
    }

}
