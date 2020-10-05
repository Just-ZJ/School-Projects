import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zheng Ji Tan
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Oddity() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Oddity
         */
        SimpleWriter out = new SimpleWriter1L();
        final int[] values = { 8, -4, 3, 0, -5 };
        int i = 0;
        while (i < values.length) {
            int remainder = values[i] % 2;
            if (remainder != 0) {
                out.println("odd");
            } else {
                out.println("even");
            }
            i = i + 1;
        }
        out.println("-----New Program-----");
        /*
         * Time for a Change
         */
        final int a = 456;
        final double x = a * 1.0;
        final double y = 100.0 * 4.56;
        if (Double.compare(x, y) == 0) {
            out.println("equal");
        } else {
            out.println("not equal");
        }
        out.println("-----New Program-----");
        /*
         * Integer Division
         */
        final double microsPerDay = 24.0 * 60.0 * 60.0 * 1000.0 * 1000.0;
        final int millisPerDay = 24 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);
        out.println("-----New Program-----");
        /*
         * Elementary. Error: 5432l the 1 is actually a l
         */
        final int num1 = 12345;
        final int num2 = 54321;
        out.println(num1 + num2);
        out.println("-----New Program-----");
        /*
         * Additional Activities
         */
        out.close();
    }
}
