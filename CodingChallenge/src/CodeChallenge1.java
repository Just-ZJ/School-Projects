import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Coding Challenge 1 for CSE2221 Final Review.
 *
 * @author Zheng Ji Tan
 *
 */
public final class CodeChallenge1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CodeChallenge1() {
    }

    /**
     * Prints an ASCII-art triangle of the given size.
     *
     * @param height
     *            the number of rows high of the final triangle
     * @param out
     *            the open output stream where the triangle should be printed
     * @requires height > 0 and [out is open]
     * @ensures [triangle of given height has been printed on out]
     */
    private static void triangle(int height, SimpleWriter out) {
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                out.print(" ");
            }
            for (int j = 0; j < (height - i) * 2 - 1; j++) {
                out.print("*");
            }
            out.println();
        }

    }

    /**
     * Reports the length of the longest tag string label in t.
     *
     * @param t
     *            the XMLTree
     * @return the length of the longest tag string label in t
     * @ensures longestTagLength = [length of the longest tag in t]
     */
    private static int longestTagLength(XMLTree t) {
        int length = 0;
        if (t.isTag()) {
            length = t.label().length();
        }

        for (int i = 0; i < t.numberOfChildren(); i++) {
            if (t.child(i).isTag()) {
                int temp = longestTagLength(t.child(i));
                if (temp > length) {
                    length = temp;
                }
            }
        }

        return length;
    }

    /**
     * Returns the maximum (i.e. largest) digit of n
     *
     * @param n
     *            the number to be evaluated
     * @return the digit with the highest value from n
     * @ensures maxDigit = [the maximum digit of n]
     */
    private static int maxDigit(NaturalNumber n) {

        int maxDigit = n.divideBy10();
        int digit = maxDigit;
        if (!n.isZero()) {
            int temp = maxDigit(n);
            if (temp > maxDigit) {
                maxDigit = temp;
            }
        }
        n.multiplyBy10(digit);
        return maxDigit;
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

        /* test triangle() */
        out.println(" ** TESTING TRIANGLE ** ");

        out.println("triangle height = 1");
        triangle(1, out);
        out.println("triangle height = 2");
        triangle(2, out);
        out.println("triangle height = 3");
        triangle(3, out);
        out.println("triangle height = 4");
        triangle(4, out);

        /* test longestTagLength() */
        out.println("\n ** TESTING LONGEST TAG LENGTH ** ");

        String xmlFile1 = "http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml";
        XMLTree xml1 = new XMLTree1(xmlFile1);
        out.println("Testing " + xmlFile1);
        out.println("Longest Tag Length is : " + longestTagLength(xml1));

        String xmlFile2 = "https://www.w3schools.com/xml/note.xml";
        XMLTree xml2 = new XMLTree1(xmlFile2);
        out.println("Testing " + xmlFile2);
        out.println("Longest Tag Length is : " + longestTagLength(xml2));

        /* test maxDigit() */

        out.println("\n ** TESTING MAXDIGIT ** ");
        out.println(
                "max digit of 123 is: " + maxDigit(new NaturalNumber2("123")));
        out.println(
                "max digit of 321 is: " + maxDigit(new NaturalNumber2("321")));
        out.println("max digit of 100201 is: "
                + maxDigit(new NaturalNumber2("100201")));
        out.println("max digit of 34567890123 is: "
                + maxDigit(new NaturalNumber2("34567890123")));

        out.println("\n ** TESTING DONE ** ");

        in.close();
        out.close();
    }

}
