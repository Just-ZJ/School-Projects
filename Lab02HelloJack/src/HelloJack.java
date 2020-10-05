import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program that prompts the user for his/her name, inputs the name, and
 * outputs the message Hello, <name> where <name> is the name entered by the
 * user.
 *
 * @author Zheng Ji Tan
 *
 */
public final class HelloJack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HelloJack() {
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

        out.print("Please enter your name: ");
        String userName = in.nextLine();
        out.println("Hello, " + userName);

        in.close();
        out.close();
    }

}
