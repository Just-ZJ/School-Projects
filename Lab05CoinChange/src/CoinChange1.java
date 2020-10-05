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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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
        final int zero = 0;
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        change[zero] = amt / coinValue[zero];
        amt = amt - change[zero] * coinValue[zero];

        change[one] = amt / coinValue[one];
        amt = amt - change[one] * coinValue[one];

        change[two] = amt / coinValue[two];
        amt = amt - change[two] * coinValue[two];

        change[three] = amt / coinValue[three];
        amt = amt - change[three] * coinValue[three];

        change[four] = amt / coinValue[four];
        amt = amt - change[four] * coinValue[four];

        change[five] = amt / coinValue[five];
        amt = amt - change[five] * coinValue[five];

        out.println("The change is " + change[zero] + coins[zero] + ", "
                + change[one] + coins[one] + ", " + change[two] + coins[two]
                + ", " + change[three] + coins[three] + ", " + change[four]
                + coins[four] + " and " + change[five] + coins[five]);
        in.close();
        out.close();
    }

}
