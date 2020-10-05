import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map2;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Zheng Ji Tan
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String>, Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -8554858568197410078L;

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Returns {@code termDef} with a map of terms with their definitions.
     *
     * @param file
     *            the file containing a list of terms and its definitions
     * @param termDef
     *            the term -> definition map
     * @updates termDef
     * @requires {@code file} is not empty, and {@code file} consists of a
     *           single term on the first line, its definition on the next one
     *           or more lines (terminated by an empty line), another term on
     *           the next line, its definition on the next one or more lines
     *           (terminated by an empty line), etc. Also, all the terms in
     *           {@code file} have to be unique.
     *
     * @ensures {@code termDef} contains a map of all the terms in {@code file}
     *          with its correct definition, and that all the term from
     *          {@code file} is in the key of {@code termDef}.
     */
    public static void processInputFile(String file,
            Map<String, String> termDef) {

        SimpleReader input = new SimpleReader1L(file);
        /*
         * adds terms and definition to maps
         */
        while (!input.atEOS()) {
            String term = input.nextLine();
            String tmp = input.nextLine();
            String def = "" + tmp;
            /*
             * finds the blank line at the end of each definition, and ensure
             * that it is not at EOS
             */
            while (!tmp.isEmpty() && !input.atEOS()) {
                tmp = input.nextLine();
                def = def.concat(tmp);
            }
            termDef.add(term, def);
        }
        input.close();
    }

    /**
     * Returns a map{@code termDef} of terms with their respective .html
     * filename.
     *
     * @param termDef
     *            the term -> definition map
     * @param termLink
     *            the term -> .html filename map
     * @updates termLink
     * @requires |termDef| > 0
     * @ensures <pre>
     *          |termDef|=|termLink|
     *          termLink contains a map of terms, with its respective html
     *          file name.
     *          </pre>
     */
    public static void mapTermsAndLinks(Map<String, String> termDef,
            Map<String, String> termLink) {

        for (Map.Pair<String, String> x : termDef) {
            String term = x.key();
            String pageURL = term + ".html";
            termLink.add(term, pageURL);
        }
    }

    /**
     * Outputs the "opening" tags in the generated HTML file.
     *
     * @param out
     *            the output stream
     * @param title
     *            the title of the page
     * @updates out.content
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    public static void outputHeader(SimpleWriter out, String title) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>" + title + "</title>");
        out.println("    </head>");
        out.println("");
        out.println("    <body>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file.
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    public static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("    </body>");
        out.println("</html>");
    }

    /**
     * Outputs the "body" tags in the generated index.html file.
     *
     * @param out
     *            the output stream
     * @param termLink
     *            the term -> .html filename map
     * @param qTerms
     *            a queue of terms in alphabetical order
     * @updates out.content
     * @requires out.is_open, and all the strings present in {@code qTerms} are
     *           the keys in {@code termLink}}
     * @ensures <pre>
     * out.content = #out.content *
     *   [a HTML heading for the page, as well as an Index]*
     *   [a HTML list with each key in alphabetical order from termLink, and
     *   the value of the key in the attribute tag]
     * </pre>
     */
    public static void processIndexHtml(SimpleWriter out,
            Map<String, String> termLink, Queue<String> qTerms) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("        <h2>List of Glossary Terms</h2>");
        out.println("        <hr />");
        out.println("        <h3>Index</h3>");
        out.println("        <ul>");

        Queue<String> qTermsCopy = qTerms.newInstance();
        while (qTerms.length() > 0) {
            String term = qTerms.dequeue();
            if (termLink.hasKey(term)) {
                String pageURL = termLink.value(term);
                out.println("            <li><a href=\"" + pageURL + "\">"
                        + term + "</a></li>");
            }
            qTermsCopy.enqueue(term);
        }
        qTerms.transferFrom(qTermsCopy);
        out.println("        </ul>");
    }

    /**
     * Outputs the "body" tags in the generated .html file of each term.
     *
     * @param termDef
     *            the term -> definition map
     * @param termLink
     *            the term -> .html filename map
     * @param outFolder
     *            the name of the folder where the output files should be stored
     * @updates out.content
     * @ensures .html file of each term has the term as the heading, and a
     *          definition that has links to any terms found in termLink,
     *          followed by a link back to index.html
     *
     */
    public static void processTermsHtml(Map<String, String> termDef,
            Map<String, String> termLink, String outFolder) {

        for (Map.Pair<String, String> x : termDef) {
            String term = x.key();
            String def = x.value();
            String defWithLink = addAttriTagToDef(def, termLink);
            if (termLink.hasKey(term)) {
                String pageURL = termLink.value(term);
                SimpleWriter out = new SimpleWriter1L(outFolder + pageURL);
                outputHeader(out, term);
                out.println("         <h2><b><i><font color=\"red\">" + term
                        + "</font></i></b></h2>");
                out.println("         <blockquote>" + defWithLink
                        + "</blockquote>");
                out.println("         <hr />");
                out.println(
                        "         <p>Return to <a href=\"index.html\">index</a>.</p>");
                outputFooter(out);
                out.close();
            }
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        if (str.length() > 0) {
            Character tmp = str.charAt(0);
            if (!strSet.contains(tmp)) {
                strSet.add(tmp);
            }
            String str2 = str.substring(1, str.length());
            generateElements(str2, strSet);
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String result = "";
        int newPos = position;
        if (separators.contains(text.charAt(newPos))) {
            result += text.charAt(newPos);
            if ((newPos + 1) < text.length()
                    && separators.contains(text.charAt(newPos + 1))) {
                result += nextWordOrSeparator(text, newPos + 1, separators);
            }
        } else {
            result += text.charAt(newPos);
            if ((newPos + 1) < text.length()
                    && !separators.contains(text.charAt(newPos + 1))) {
                result += nextWordOrSeparator(text, newPos + 1, separators);
            }
        }
        return result;
    }

    /**
     * Looks for all the terms that are in the definition and create a link to
     * the respective html page.
     *
     * @param def
     *            a line of string containing the definition of a term
     * @param termLink
     *            the term -> .html filename map
     * @return String
     *
     * @ensures a modified {@code def} that has wraps every word found in the
     *          key of {@code termLink} with a html attribute tag that links to
     *          the html page of that particular key
     */
    public static String addAttriTagToDef(String def,
            Map<String, String> termLink) {
        /*
         * creates a set of seperators
         */
        String defWithTag = "";
        Set<Character> seperatorSet = new Set1L<>();
        String seperators = ".,\t! @#$%^&*()_+-{}/:;<>=~`";
        generateElements(seperators, seperatorSet);
        /*
         * finds any key that is in def and concat with the <a href""><a/> tag
         */
        int position = 0;
        while (position < def.length()) {
            String token = nextWordOrSeparator(def, position, seperatorSet);
            String oldDef = defWithTag;
            if (!seperatorSet.contains(token.charAt(0))) {
                String tmp = "";
                if (termLink.hasKey(token)) {
                    tmp = "<a href=\"" + termLink.value(token) + "\">" + token
                            + "</a>";
                } else {
                    tmp = token;
                }
                defWithTag += tmp;
            } else {
                defWithTag = oldDef + token;
            }
            position += token.length();
        }
        return defWithTag;
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
        /*
         * asks user for name of input file and output folder
         */
        out.print("Please enter the name of an input file: ");
        String inFile = in.nextLine();
        out.print("Please enter the name of an output folder: ");
        String outFolder = in.nextLine();

        /*
         * checks if outFolder has "/" at the end
         */
        if (outFolder.charAt(outFolder.length() - 1) != ('/')) {
            outFolder = outFolder + ("/");
        }

        /*
         * a map of terms and its definitions
         */
        Map<String, String> termDef = new Map2<>();
        processInputFile(inFile, termDef);

        /*
         * sorts the terms in qTerms alphabetically
         */
        Queue<String> qTerms = new Queue2<>();
        for (Map.Pair<String, String> x : termDef) {
            qTerms.enqueue(x.key());
        }
        Comparator<String> cs = new StringLT();
        qTerms.sort(cs);

        /*
         * a map of terms and its html filename
         */
        Map<String, String> termLink = new Map2<>();
        mapTermsAndLinks(termDef, termLink);

        /*
         * creates an index.html
         */
        String indexURl = outFolder + "index.html";
        SimpleWriter outIndex = new SimpleWriter1L(indexURl);
        outputHeader(outIndex, "List of Glossary Terms");
        processIndexHtml(outIndex, termLink, qTerms);
        outputFooter(outIndex);

        /*
         * creates a .html file for each term
         */
        processTermsHtml(termDef, termLink, outFolder);

        outIndex.close();
        in.close();
        out.close();
    }
}
