import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program should ask the user for the name of an XML file containing a list of
 * URLs for RSS v2.0 feeds (see below for the format of this file) and for the
 * name of an output file in which to generate an HTML page with links to the
 * pages for the individual RSS feeds (see below for an example). It should then
 * read the input XML file into an XMLTree object and then process the list of
 * RSS feeds from the XMLTree. For each RSS feed, the program should generate an
 * HTML page with a table of links to all the news items in the feed (just like
 * in the previous project). The program should also generate an HTML page with
 * an index of links to the individual feed pages.
 *
 * @author Zheng Ji Tan
 *
 */

public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * removed due to checkstyle error.
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * initial value for when title/link/description tag has no children
         */
        String htmlPageTitle = "Empty Title";
        String htmlPageLink = "";
        String htmlPageDescription = "No Description";
        /*
         * loops through all children of channel to find the following tag:
         * title, link, description
         */
        for (int i = 0; i < channel.numberOfChildren(); i++) {
            //finds the title tag
            if (channel.child(i).label().equals("title")
                    && channel.child(i).isTag()) {
                XMLTree title = channel.child(i);
                /*
                 * checks if title tag has only 1 child, which is a text
                 */
                if (title.numberOfChildren() == 1 && !title.child(0).isTag()) {
                    htmlPageTitle = title.child(0).label();
                }
            }
            //finds the link tag
            if (channel.child(i).label().equals("link")
                    && channel.child(i).isTag()) {
                XMLTree link = channel.child(i);
                /*
                 * checks if link tag has only 1 child, which is a text
                 */
                if (link.numberOfChildren() == 1 && !link.child(0).isTag()) {
                    htmlPageLink = link.child(0).label();
                }
            }
            //finds the description tag
            if (channel.child(i).label().equals("description")
                    && channel.child(i).isTag()) {
                XMLTree description = channel.child(i);
                /*
                 * checks if description tag has only 1 child, which is a text
                 */
                if (description.numberOfChildren() == 1
                        && !description.child(0).isTag()) {
                    htmlPageDescription = description.child(0).label();
                }
            }
        }

        out.println("<html>");
        out.println("   <head>");
        out.println("       <title>" + htmlPageTitle + "</title>");
        out.println("   </head>");
        out.println("");
        out.println("   <body>");
        out.println("       <h1><a href=\"" + htmlPageLink + "\">"
                + htmlPageTitle + "</a></h1>");
        out.println("       <p>" + htmlPageDescription + "</p>");
        out.println("       <table border=\"1\">");
        out.println("           <tr>");
        out.println("               <th>Date</th>");
        out.println("               <th>Source</th>");
        out.println("               <th>News</th>");
        out.println("           </tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("       </table>");
        out.println("   </body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int tagIndex = -1;
        /*
         * starts searching from the last child of xml to get the index of first
         * child
         */
        for (int i = xml.numberOfChildren() - 1; i >= 0; i--) {
            if (xml.child(i).label().equals(tag) && xml.child(i).isTag()) {
                tagIndex = i;
            }
        }
        return tagIndex;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        /*
         * check if pubDate is present
         */
        int pubDateIndex = getChildElement(item, "pubDate");
        String htmlItemPubDate = "No date available";

        if (pubDateIndex >= 0) {
            XMLTree itemPubDate = item.child(pubDateIndex);
            /*
             * checks if pubDate tag has only 1 child, which is a text
             */
            if (itemPubDate.numberOfChildren() == 1
                    && !itemPubDate.child(0).isTag()) {
                htmlItemPubDate = itemPubDate.child(0).label();
            }
        }
        out.println("           <tr>");
        out.println("               <td>" + htmlItemPubDate + "</td>");

        /*
         * check if source is available. if sourceIndex=-1, source not present.
         * else source present
         */
        int sourceIndex = getChildElement(item, "source");
        String htmlItemSource = "No source available";
        String htmlItemSourceLink = "";

        if (sourceIndex < 0) {
            out.println("               <td>" + htmlItemSource + "</td>");
        } else {
            /*
             * If a <source> tag appears as a child of an <item> tag, it must
             * have a url attribute.
             */
            XMLTree itemSource = item.child(sourceIndex);
            htmlItemSource = itemSource.child(0).label();
            htmlItemSourceLink = itemSource.attributeValue("url");
            out.println("               <td><a href=\"" + htmlItemSourceLink
                    + "\">" + htmlItemSource + "</a></td>");
        }

        /*
         * check if link is present. If link is present, it should have the
         * child with the needed information.
         */
        int linkIndex = getChildElement(item, "link");
        String htmlItemLink = "";

        if (linkIndex >= 0) {
            XMLTree itemLink = item.child(linkIndex);
            htmlItemLink = itemLink.child(0).label();
        }

        /*
         * if no title tag, no title child, no description tag, no description
         * child then print no title available.
         */
        int titleIndex = getChildElement(item, "title");
        int descriptionIndex = getChildElement(item, "description");
        XMLTree itemTitle = item.child(0);
        XMLTree itemDescription = item.child(0);
        String htmlItemTitle = "";
        String htmlItemDescription = "";
        if (titleIndex >= 0) {
            itemTitle = item.child(titleIndex);

        }
        if (descriptionIndex >= 0) {
            itemDescription = item.child(descriptionIndex);
        }
        /*
         * checks if title is present, has only 1 text child. If it has a text
         * child, show title
         */
        if (titleIndex >= 0 && itemTitle.numberOfChildren() == 1
                && !itemTitle.child(0).isTag()) {
            htmlItemTitle = itemTitle.child(0).label();
            out.println("               <td><a href=\"" + htmlItemLink + "\">"
                    + htmlItemTitle + "</a></td>");
            /*
             * if title is present & empty & description present & description
             * has one text child present, show description
             */
        } else if (descriptionIndex >= 0
                && item.child(descriptionIndex).numberOfChildren() == 1
                && !item.child(descriptionIndex).child(0).isTag()) {
            htmlItemDescription = itemDescription.child(0).label();
            out.println("               <td>" + htmlItemDescription + "</td>");
        } else {
            out.println("               <td><p>No title available</p></td>");
        }
        out.println("           </tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        XMLTree rss = new XMLTree1(url);
        SimpleWriter outRssFile = new SimpleWriter1L(file);
        /*
         * checks if there is an attribute called version and if version is a
         * double.
         */
        double rssVersion = 0.0;
        if (FormatChecker.canParseDouble(rss.attributeValue("version"))
                && rss.hasAttribute("version")) {
            rssVersion = Double.parseDouble(rss.attributeValue("version"));
        }
        /*
         * checks if it is a rss tag and whether it has a version of 2.0
         */
        if (rss.isTag() && rss.label().equals("rss")
                && Double.compare(rssVersion, 2.0) == 0) {
            out.println("Creating " + file + "...");
            XMLTree channel = rss.child(0);
            out.println("Creating " + file + " header...");
            outputHeader(channel, outRssFile);

            /*
             * to find index of first item tag in channel
             */
            out.println("Creating " + file + " table rows...");
            int itemIndex = getChildElement(channel, "item");
            for (int i = itemIndex; i < channel.numberOfChildren(); i++) {
                XMLTree item = channel.child(i);
                /*
                 * to ensure that it is an item tag
                 */
                if (item.label().equals("item") && item.isTag()) {
                    processItem(item, outRssFile);
                }
            }
            out.println("Closing tags in " + file + "...");
            outputFooter(outRssFile);
            out.println(file + " is now finished.");
            out.println();
        } else {
            out.println(
                    "Invalid version 2.0 rss file. Moving on to the next link...");
        }

        outRssFile.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print(
                "Enter the XML file containing a list of URLs for RSS v2.0 feeds: ");

        XMLTree feedRoot = new XMLTree1(in.nextLine());
        /*
         * repeatedly ask for input until it is .html
         */
        out.print(
                "Enter the name of an output file, including the .html extension: ");
        String fileURL = in.nextLine();
        while (!fileURL.contains(".html")) {
            out.print("This is not a valid file name. ");
            out.print(
                    "Enter the name of an output file, including the .html extension: ");
            fileURL = in.nextLine();
        }
        SimpleWriter outFile = new SimpleWriter1L(fileURL);

//        XMLTree feedRoot = new XMLTree1(
//                "http://web.cse.ohio-state.edu/software/2221/web-sw1/assignments"
//                        + "/projects/rss-aggregator/feeds.xml");
//        SimpleWriter outFile = new SimpleWriter1L("index.html");

        String feedRootTitle = "";
        if (feedRoot.hasAttribute("title")) {
            feedRootTitle = feedRoot.attributeValue("title");
        }
        /*
         * html file header
         */
        outFile.println("<html>");
        outFile.println("   <head>");
        outFile.println("       <title>" + feedRootTitle + "</title>");
        outFile.println("   </head>");
        outFile.println("");
        outFile.println("   <body>");
        outFile.println("           <h1>" + feedRootTitle + "</h1>");
        outFile.println("           <ul>");
        /*
         * prints out an unordered list for each rss url
         */
        for (int i = 0; i < feedRoot.numberOfChildren(); i++) {
            XMLTree feed = feedRoot.child(i);
            String feedHTMLFileName = "";
            String feedRSSURL = "";
            String feedPageName = "";
            if (feed.hasAttribute("file")) {
                feedHTMLFileName = feed.attributeValue("file");
            }
            if (feed.hasAttribute("name")) {
                feedPageName = feed.attributeValue("name");
            }
            if (feed.hasAttribute("url")) {
                feedRSSURL = feed.attributeValue("url");
            }
            outFile.print("             <li>");
            outFile.print("<a href=" + feedHTMLFileName + ">" + feedPageName
                    + "</a>");
            outFile.println("</li>");
            /*
             * creates a html page for each rss link
             */
            processFeed(feedRSSURL, feedHTMLFileName, out);
        }

        /*
         * html file footer(close tags)
         */
        outFile.println("           </ul>");
        outFile.println("   </body>");
        outFile.println("</html>");

        in.close();
        out.close();
        outFile.close();
    }
}