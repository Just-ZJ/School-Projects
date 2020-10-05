import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series, its length and its max value
     * starting with the given integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n, len = 1, max = n;
        final int three = 3;
        out.print(x + " ");
        while (x != 1) {
            if (x % 2 == 0) {
                x = x / 2;
                out.print(x + " ");
            } else {
                x = three * x + 1;
                out.print(x + " ");
            }
            if (x > max) {
                max = x;
            }
            len++;
        }
        out.println();
        out.println("The length of the series is " + len);
        out.println("The maximum value of the series is " + max);

    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.print("Enter a positive integer: ");
        String strUserNum = in.nextLine();

        while (!FormatChecker.canParseInt(strUserNum)) {
            out.print("Enter a positive integer: ");
            strUserNum = in.nextLine();
        }
        int userNum = Integer.parseInt(strUserNum);
        return userNum;
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

        int userNum = getPositiveInteger(in, out);
        generateSeries(userNum, out);
        out.println();
        out.println("Would you like to calculate another series? [y/n]");
        char reMatch = in.nextLine().toUpperCase().charAt(0);
        while (reMatch == 'Y') {
            userNum = getPositiveInteger(in, out);
            generateSeries(userNum, out);
            out.println();
            out.println("Would you like to calculate another series? [y/n]");
            reMatch = in.nextLine().toUpperCase().charAt(0);
        }

        in.close();
        out.close();
    }

}
