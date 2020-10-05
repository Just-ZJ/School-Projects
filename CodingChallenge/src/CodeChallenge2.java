import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CodeChallenge2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CodeChallenge2() {
    }

    /**
     * Reports the value of the second-largest entry in an array.
     *
     * @param a
     *            integer array
     * @return the second largest integer in the array
     * @requires [a has at least 2 entries]
     * @ensures secondLargest = [largest value not including max entry]
     */
    private static int secondLargest(int[] a) {
        int max, sec;
        if (a[0] > a[1]) {
            max = a[0];
            sec = a[1];
        } else {
            max = a[1];
            sec = a[0];
        }

        for (int i = 2; i < a.length; i++) {
            int tmp = a[i];
            if (tmp > max) {
                max = tmp;
            } else if (tmp > sec && tmp <= max) {
                sec = tmp;
            }
        }
        return sec;
    }

    /**
     * returns a copy of src.
     *
     * @param src
     *            the source of the copied value
     * @return the copied value
     * @ensures copy = src
     */
    public static NaturalNumber copy(NaturalNumber src) {
        NaturalNumber copy = src.newInstance();
        int digit = src.divideBy10();
        if (!src.isZero()) {
            copy.copyFrom(copy(src));
        }
        copy.multiplyBy10(digit);
        src.multiplyBy10(digit);
        return copy;

    }

    /**
     * Convert the given Expression Tree to an String.
     *
     * @param exp
     *            XML formatted expression tree
     * @return fully parenthesized expression
     * @requires [exp is a subtree of a well-formed XML arithmetic expression]
     *           and [the label of the root of exp is not "expression"]
     * @ensures stringTree = the encoded expression, fully parenthesized
     */
    private static String stringTree(XMLTree exp) {
        String ansFinal = "";
        String subAnsOne = "";
        String subAnsTwo = "";

        if (exp.numberOfChildren() != 0) {
            subAnsOne = stringTree(exp.child(0));
            subAnsTwo = stringTree(exp.child(1));

            if (exp.label().equals("plus")) {
                ansFinal = "(" + subAnsOne + "+" + subAnsTwo + ")";
            }

            if (exp.label().equals("divide")) {
                ansFinal = "(" + subAnsOne + "/" + subAnsTwo + ")";
            }

            if (exp.label().equals("times")) {
                ansFinal = "(" + subAnsOne + "*" + subAnsTwo + ")";
            }

            if (exp.label().equals("minus")) {
                ansFinal = "(" + subAnsOne + "-" + subAnsTwo + ")";
            }

        } else {
            ansFinal = exp.attributeValue("value");
        }

        return ansFinal;

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

        /* test private static int secondLargest(int[] a) */
        out.println(" ** TESTING SECONDLARGEST ** ");
        int[] a = { 1, 5, -7, 4, 1 };
        int[] b = { 1, 5, -7, 4, 5 };
        int[] c = { -2, -3, -7, -4, -5 };
        int[] d = { 1, 0, -7, 0, -5 };
        out.println(
                "Second Largest of {1, 5, -7, 4, 1} is " + secondLargest(a));
        out.println(
                "Second Largest of {1, 5, -7, 4, 5} is " + secondLargest(b));
        out.println("Second Largest of {-2, -3, -7, -4, -5} is "
                + secondLargest(c));
        out.println(
                "Second Largest of {1, 0, -7, 0, -5} is " + secondLargest(d));

        /* test NaturalNumber copy(NaturalNumber src)) */
        out.println("\n ** TESTING COPY ** ");
        out.println("Copy of 123 is " + copy(new NaturalNumber1L("123")));
        out.println("Copy of 1001 is " + copy(new NaturalNumber1L("1001")));
        out.println("Copy of 1 is " + copy(new NaturalNumber1L("1")));
        out.println("Copy of 123123123123 is "
                + copy(new NaturalNumber1L("123123123123")));

        /* test stringTree(XMLTree exp) */
        out.println("\n ** TESTING STRINGTREE ** ");
        XMLTree cc2 = new XMLTree2("data/cc2.xml");
        out.println("data/cc2.xml becomes this string: "
                + stringTree(cc2.child(0)));

        out.println("\n ** TESTING DONE ** ");

        in.close();
        out.close();
    }
}
