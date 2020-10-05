import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program would ask for the name of the input file from the user, as well
 * as the name of the output file. The program would then output a file with the
 * given name, and the output would be a single well-formed HTML file displaying
 * the name of the input file in a heading followed by a table listing the words
 * and their corresponding counts. The words would appear in alphabetical order.
 *
 * @author Zheng Ji Tan (tan.955)
 *
 */
public final class WordCounter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounter() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String>, Serializable {
        private static final long serialVersionUID = -8554858568197410078L;

        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
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
    public static void outputHtmlHeader(SimpleWriter out, String title) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("   <head>");
        out.println("       <title>" + title + "</title>");
        out.println("   </head>");
        out.println("   <body>");
        out.println("       <h2>Words Counted in " + title + "</h2>");
    }

    /**
     * Outputs the "body" tags of the HTML file. In the body, there would be a
     * table that consists of has each word in alphabetical order in one column,
     * and its count in another column.
     *
     * @param out
     *            the output stream
     * @param orderedKeys
     *            a queue of strings that is in lexicographical order
     * @param wordCount
     *            a word -> count map
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "body" tags]
     */
    public static void outputHtmlBody(SimpleWriter out,
            Queue<String> orderedKeys, Map<String, Integer> wordCount) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("       <hr />");
        out.println("       <table border=\"1\">");
        out.println("           <tr>");
        out.println("               <th>Words</th>");
        out.println("               <th>Counts</th>");
        out.println("           </tr>");

        //prints out the keys and its corresponding values in alphabetical order
        while (orderedKeys.length() > 0) {
            String keyName = orderedKeys.dequeue();
            out.println("           <tr>");
            out.println("               <td>" + keyName + "</td>");
            out.println(
                    "               <td>" + wordCount.value(keyName) + "</td>");
            out.println("           </tr>");
        }
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
    public static void outputHtmlFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("       </table>");
        out.println("   </body>");
        out.println("</html>");
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param separatorStr
     *            the given {@code String}
     * @param separator
     *            the {@code Set} to be replaced
     * @replaces separator
     * @ensures separator = entries(separatorStr)
     */
    public static void generateSeparator(String separatorStr,
            Set<Character> separator) {
        assert separatorStr != null : "Violation of: str is not null";
        assert separator != null : "Violation of: strSet is not null";

        //checks if separatorStr is empty
        if (separatorStr.length() > 0) {
            Character tmp = separatorStr.charAt(0);
            /*
             * adds the separator from separatorStr into separator, if it is not
             * inside
             */
            if (!separator.contains(tmp)) {
                separator.add(tmp);
            }
            //removes the first character in separatorStr
            String str2 = separatorStr.substring(1, separatorStr.length());
            generateSeparator(str2, separator);
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

        //concatenate the character at position to result
        result += text.charAt(position);

        /*
         * checks if the index of the next character after position is over the
         * text length (to prevent index out of bounds).
         */
        if ((position + 1) < text.length()) {
            /*
             * checks if the current character is a separator
             */
            if (separators.contains(text.charAt(position))) {
                //checks if the next character is a separator
                if (separators.contains(text.charAt(position + 1))) {
                    result += nextWordOrSeparator(text, position + 1,
                            separators);
                }
            } else {
                //checks if the next character is a word
                if (!separators.contains(text.charAt(position + 1))) {
                    result += nextWordOrSeparator(text, position + 1,
                            separators);
                }
            }
        }
        return result;
    }

    /**
     * Takes in a line of words and separators, and adds only words to
     * {@code  Map<String, Integer>}. If {@code  Map<String, Integer>} already
     * has that word as a key, it would add 1 to the value of that key instead.
     *
     * @param line
     *            a line of words and separators
     * @param separators
     *            a set of separators
     * @param wordCount
     *            a word -> count map
     * @requires |line| > 0 && |separators| > 0
     * @updates wordCount
     * @ensures {@code Map<String, Integer>} adds all unique words in
     *          {@code String} as keys, with the number of times the word
     *          appeared as the value.
     */
    public static void processLine(String line, Set<Character> separators,
            Map<String, Integer> wordCount) {
        assert line.length() > 0 : "Line can not be empty";
        assert separators.size() > 0 : "The set of separators cannot be empty";

        String tmpLine = line;
        String word = nextWordOrSeparator(tmpLine, 0, separators);

        /*
         * checks if word is a separator. if it is not a separator, it would be
         * added as a key to wordCount. If wordCount already has that key, it
         * would add 1 to the value of that key instead.
         */
        if (!separators.contains(word.charAt(0)) && !wordCount.hasKey(word)) {
            wordCount.add(word, 1);
        } else if (wordCount.hasKey(word)) {
            int val = wordCount.value(word);
            val++;
            wordCount.replaceValue(word, val);
        }
        tmpLine = tmpLine.substring(word.length(), line.length());

        //checks if tmpLine is empty
        if (tmpLine.length() > 0) {
            processLine(tmpLine, separators, wordCount);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        //asks for the name of input and output file
        out.print("Please enter the name of an input file: ");
        String inputFileName = in.nextLine();
        out.print("Please enter the name of an output file: ");
        String outputFileName = in.nextLine();

        SimpleReader inputFileReader = new SimpleReader1L(inputFileName);
        SimpleWriter outToHtml = new SimpleWriter1L(outputFileName);

        //creates a set of separators
        String separatorString = ".,\t! @#$%^&*_+-()[]{}<>/:;=~`";
        Set<Character> separators = new Set1L<>();
        generateSeparator(separatorString, separators);

        /*
         * counts the number of times each word appears in inputFileReader and
         * store them in wordCount with the word as the key and its count as the
         * value
         */
        Map<String, Integer> wordCount = new Map1L<>();
        while (!inputFileReader.atEOS()) {
            String line = inputFileReader.nextLine();
            if (line.length() > 0) {
                processLine(line, separators, wordCount);
            }
        }

        //orders all the keys in wordCount in alphabetical order
        Queue<String> orderedKeys = new Queue1L<>();
        for (Map.Pair<String, Integer> keyPair : wordCount) {
            orderedKeys.enqueue(keyPair.key());
        }
        Comparator<String> cs = new StringLT();
        orderedKeys.sort(cs);

        //outputs the header, body and footer of the html file
        outputHtmlHeader(outToHtml, inputFileName);
        outputHtmlBody(outToHtml, orderedKeys, wordCount);
        outputHtmlFooter(outToHtml);

        out.println("The file \"" + outputFileName + "\" has been created.");

        in.close();
        out.close();
        inputFileReader.close();
        outToHtml.close();
    }
}
