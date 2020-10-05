import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        NaturalNumber nnA = new NaturalNumber2(0);
        NaturalNumber nnB = new NaturalNumber2(0);
        NaturalNumber nnVal = new NaturalNumber2(0);
        NaturalNumber zero = new NaturalNumber2(0);

        if (exp.isTag()) {
            if (exp.label().equals("number")) {
                int val = Integer.parseInt(exp.attributeValue("value"));
                nnVal.setFromInt(val);
            } else {
                nnA.copyFrom(evaluate(exp.child(0)));
                nnB.copyFrom(evaluate(exp.child(1)));
            }

            if (exp.label().equals("minus")) {
                /* require: this >= n */
                if (nnA.compareTo(nnB) < 0) {
                    Reporter.fatalErrorToConsole(
                            "ERROR! Subtracting a larger number from a smaller "
                                    + "number. NaturalNumber cannot be negative.");
                } else {
                    nnA.subtract(nnB);
                    nnVal.copyFrom(nnA);
                }

            } else if (exp.label().equals("plus")) {
                /* has no require clause */
                nnA.add(nnB);
                nnVal.copyFrom(nnA);

            } else if (exp.label().equals("times")) {
                /* has no require clause */
                nnA.multiply(nnB);
                nnVal.copyFrom(nnA);

            } else if (exp.label().equals("divide")) {
                /* require: n > 0 */
                if (nnB.equals(zero)) {
                    Reporter.fatalErrorToConsole(
                            "ERROR! You are dividing by 0.");
                } else {
                    nnA.divide(nnB);
                    nnVal.copyFrom(nnA);
                }

            }
        }

        return nnVal;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();

        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

//        /* Start of test */
//        String file = "test/test1_given_16.xml";
//        XMLTree exp = new XMLTree1(file);
//        out.println("Test 1(" + file + "): " + evaluate(exp.child(0)));
//
//        file = "test/test2_singlenum_5.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 2(" + file + "): " + evaluate(exp.child(0)));
//
//        file = "test/test3_random_15.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 3(" + file + "): " + evaluate(exp.child(0)));
//
//        file = "test/test4_plus_times_35.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 4(" + file + "): " + evaluate(exp.child(0)));
//
//        file = "test/test5_plus minus divide_1.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 5(" + file + "): " + evaluate(exp.child(0)));
//
//        /* checking error msg */
//        file = "test/test51_divide by 0.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 50(" + file + "): " + evaluate(exp.child(0)));
//
//        file = "test/test52_small-large_-45.xml";
//        exp = new XMLTree1(file);
//        out.println("Test 50(" + file + "): " + evaluate(exp.child(0)));
//        /* End of test */

        in.close();
        out.close();
    }
}
