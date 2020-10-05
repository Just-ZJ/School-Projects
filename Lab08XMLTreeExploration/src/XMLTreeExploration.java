import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zheng Ji Tan
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int childNum = xt.numberOfChildren();

        if (childNum > 1 && xt.isTag()) {
            int midChildIndex = childNum / 2;
            XMLTree midChild = xt.child(midChildIndex);
            out.println("The middle child's label is " + midChild.label());
            if (midChild.isTag()) {
                out.print("The middle child is a tag, with ");
                out.println(midChild.numberOfChildren() + " children.");
            } else {
                out.println("The middle child is a text.");
            }
        }
    }

    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures <pre>
     * out.content =
     *   #out.content *  [the name and value of each attribute of the root of xt]
     * </pre>
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {
        //nameAttr is like int x in for loops.
        for (String nameAttr : xt.attributeNames()) {
            out.println("The attribute is " + nameAttr
                    + " and it has a value of " + xt.attributeValue(nameAttr));
        }
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
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");
        /*
         * outputs xmltree to console and also a new window
         */
//        out.print(xml.toString());
        xml.display();
        out.print(xml.attributeNames());
        /*
         * checks if root node is a tag or text and prints out what it is
         * called(label)
         */
        if (xml.isTag()) {
            out.print("Root node is a tag called ");
            out.println(xml.label());
        } else {
            out.print("Rood node is a text called ");
            out.println(xml.label());
        }

        /*
         * checks the number of child of a node
         */
        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        int numChannelChild = channel.numberOfChildren();
        out.println("Channel has " + numChannelChild + " of child");

        XMLTree title = channel.child(1);
        XMLTree titleText = title.child(0);
        out.println("The label of titleText is" + titleText.toString());
        //outputs above statement in one statement
        out.println("The label of titleText is"
                + xml.child(0).child(0).child(1).child(0).toString());

        /*
         * determines whether it has an attribute and also provide the value of
         * the attribute
         */
        final XMLTree astronomy = channel.child(10);
        if (astronomy.hasAttribute("sunset")) {
            out.println(
                    "Astronomy have an attribute called sunset, with a value of ");
            out.println(astronomy.attributeValue("sunset"));
        } else {
            out.println("Astronomy does not have an attribute called sunset");
        }
        if (astronomy.hasAttribute("midday")) {
            out.print(
                    "Astronomy have an attribute called midday, with a value of ");
            out.println(astronomy.attributeValue("midday"));

        } else {
            out.println("Astronomy does not have an attribute called midday");
        }

        printMiddleNode(channel, out);
        printRootAttributes(xml, out);
        in.close();
        out.close();
    }

}
