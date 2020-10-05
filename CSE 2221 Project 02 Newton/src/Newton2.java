import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that requests the user to enter a positive double and it would
 * calculate the square root of the double. Then, it would repeatedly ask the
 * user if the user wants to calculate another root. If the response is "y",
 * then the program would proceed. if it is anything else, then the program
 * would quit. Whenever it proceeds, the program would prompt the user for a
 * number(a positive double or 0) and then report the square root of that number
 * to within a relative error of no more than 0.01%.
 *
 *
 * @author Zheng Ji Tan
 *
 */
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            zero or positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double r = x;
        if (x == 0) {
            r = 0;
        } else {
            r = (r + x / r) / 2;
            final double errorMargin = 0.01 / 100.0;
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
         * Requests the user to enter a positive double and prints out the
         * square root.
         */
        out.print("Please enter a positive double: ");
        double num = in.nextDouble();
        double sqrtNum = sqrt(num);
        out.println("The square root of " + num + " is " + sqrtNum);
        /*
         * checks if the user wants to calculate another square root. if the
         * response is "y", the program would proceed. if it is anything else,
         * the program would quit.
         */
        out.print(
                "Would you like to calculate the square root of a number? [Y/N]  ");
        char userAns = in.nextLine().toUpperCase().charAt(0);
        if (userAns == 'Y') {
            out.print("Please enter a positive double: ");
            num = in.nextDouble();
            sqrtNum = sqrt(num);
            out.println("The square root of " + num + " is " + sqrtNum);
        } else {
            out.println("The program will now end.");
        }

        in.close();
        out.close();
    }

}
