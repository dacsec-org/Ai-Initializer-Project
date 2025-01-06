package org.dacss.projectinitai.utilities;

import org.dacss.projectinitai.views.localllms.LLMS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LLMLinkScraper {
    public static List<LLMS> scrapeLLMLinks(String url) throws IOException {
        List<LLMS> llmLinks = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a[href]"); // Adjust the selector based on the website's structure

        for (Element link : links) {
            String href = link.attr("href");
            if (href.contains("/library/")) {
                LLMS llms = new LLMS();
                String linkText = link.text();

                llms.setName(scrapeName(linkText));
                llms.setDescription(scrapeDescription(linkText));
                llms.setType(scrapeType(linkText));
                llms.setAvailableSizes(scrapeAvailableSizes(linkText));
                llms.setPulls(scrapePulls(linkText));
                llms.setTags(scrapeTags(linkText));
                llms.setUpdated(scrapeUpdated(linkText));

                llms.setInstalled(false); // Default to not installed
                llms.setInstallationDate(); // Set the installation date if installed
                llmLinks.add(llms);
            }
        }
        return llmLinks;
    }

    public static String scrapeName(String input) {

        Pattern ollamaReg =
                Pattern.compile("^(\\S+)");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown name";
    }

    public static String scrapeDescription(String input) {

        Pattern ollamaReg =
                Pattern.compile("^(\\S+)\\s+(.*?)(?=\\s+\\S+\\s+\\d+b)");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(2) : "No description available";
    }

    public static String scrapeType(String input) {

        Pattern ollamaReg =
                Pattern.compile("\\s(\\S+)\\s+\\d+b");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown type";
    }

    public static String scrapeAvailableSizes(String input) {

        Pattern ollamaReg =
                Pattern.compile("(\\d+b(?:,\\s*\\d+b)*)");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown sizes";
    }

    public static String scrapePulls(String input) {

        Pattern ollamaReg =
                Pattern.compile("(\\d+(?:\\.\\d+)?[KM]?)\\s+Pulls");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown pulls";
    }

    public static String scrapeTags(String input) {

        Pattern ollamaReg =
                Pattern.compile("(\\d+)\\s+Tags");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown tags";
    }

    public static String scrapeUpdated(String input) {

        Pattern ollamaReg =
                Pattern.compile("Updated\\s+(.*)");

        Matcher matcher = ollamaReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown update date";
    }
}
