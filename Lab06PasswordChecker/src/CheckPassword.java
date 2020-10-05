import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        final int passLength = 8;
        if (s.length() < passLength) {
            out.println("Please enter a password that is 8 characters long");
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean check = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean check = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsNumber(String s) {
        boolean check = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                check = true;
            }
        }
        return check;
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

        out.print("Please enter your password: ");
        String pass = in.nextLine();
        final int passLength = 8;
        int passedTest = 0;
        while (passedTest < 2 || pass.length() < passLength) {

            checkPassword(pass, out);
            out.print("Please enter your password: ");
            pass = in.nextLine();

            boolean upper = containsUpperCaseLetter(pass);
            boolean lower = containsLowerCaseLetter(pass);
            boolean num = containsNumber(pass);
            if (upper) {
                passedTest++;
            }
            if (lower) {
                passedTest++;
            }
            if (num) {
                passedTest++;
            }
            if (passedTest < 2) {
                out.println(
                        "Please enter a password that has an upper case letter, "
                                + "a lower case letter and digits.");
                passedTest = 0;
            }
            break;
        }
        out.println("The password that you have entered is qualified.");
        in.close();
        out.close();
    }

}
