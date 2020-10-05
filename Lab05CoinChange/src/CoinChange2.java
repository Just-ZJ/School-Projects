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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        out.print("Enter an amount in cents: ");
        int amt = in.nextInteger();
        final String[] coins = { " dollar", " half-dollar", " quarter", " dime",
                " nickel", " penny" };
        final int[] coinValue = { 100, 50, 25, 10, 5, 1 };
        int[] change = new int[coins.length];
        out.print("The change is ");

        for (int i = 0; i < coins.length; i++) {
            change[i] = amt / coinValue[i];
            amt = amt - change[i] * coinValue[i];
            out.print(change[i] + coins[i] + ", ");
        }

        in.close();
        out.close();
    }

}
