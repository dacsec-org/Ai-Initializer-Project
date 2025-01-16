package org.dacss.projectinitai.utilities;
/**/

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>{@link LLMLinkScraper}</h1>
 * <p>
 * Utility class for scraping links of Language Model Libraries (LLMs).
 * </p>
 */
public class LLMLinkScraper {

    private static final Logger log = LoggerFactory.getLogger(LLMLinkScraper.class);

    /**
     * Scrapes LLM links from the given URL.
     *
     * @param url the URL to scrape
     * @return a list of {@link LLMS} objects
     * @throws IOException if an I/O error occurs
     */
    public static List<LLMS> scrapeLLMLinks(String url) throws IOException {
        if (url.contains("ollama.com")) {
            return scrapeHuggingFaceLinks(url);
        } else if (url.contains("huggingface.co")) {
            return scrapeHuggingFaceLinks(url);
        }
        return new ArrayList<>();
    }

    /**
     * Scrapes LLM links from the huggingface website.
     *
     * @param url the URL to scrape
     * @return a list of {@link LLMS} objects
     * @throws IOException if an I/O error occurs
     */
    private static List<LLMS> scrapeHuggingFaceLinks(String url) throws IOException {
        List<LLMS> llmLinks = new ArrayList<>();
        url += "?search=qwen"; // Append 'search=qwen' to the URL
        log.info("Requesting URL: {}", url);

        try {
            Connection connection = Jsoup.connect(url);
            Connection.Response response = connection.execute();

            // Log the response status and body for debugging
            log.info("Response Status: {}", response.statusCode());
            log.info("Response Body: {}", response.body());

            Document doc = response.parse();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");
                if (href.contains("/models/")) {
                    LLMS llms = new LLMS();
                    String linkText = link.text();

                    llms.setName(linkText);
                    llms.setDescription("Description not available");
                    llms.setType("Unknown type");
                    llms.setAvailableSizes("Unknown sizes");
                    llms.setPulls("Unknown pulls");
                    llms.setTags("Unknown tags");
                    llms.setUpdated("Unknown update date");

                    llms.setInstalled(false);
                    llms.setDateInstalled();
                    llmLinks.add(llms);
                }
            }
        } catch (IOException e) {
            log.error("Failed to fetch LLM links from Hugging Face", e);
            throw e;
        }
        return llmLinks;
    }

    // todo: reimplement the following methods to scrape and display
    //  hugging face data in there respective fields. these were set up for olloama
    public static String scrapeName(String input) {
        Pattern hugggingFaceReg = Pattern.compile("^(\\S+)");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown name";
    }

    public static String scrapeDescription(String input) {
        Pattern hugggingFaceReg = Pattern.compile("^(\\S+)\\s+(.*?)" +
                "(?=\\s+\\S+\\s+\\d+b)");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(2) : "No description available";
    }

    public static String scrapeType(String input) {
        Pattern hugggingFaceReg = Pattern.compile("\\s(\\S+)\\s+\\d+b");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown type";
    }

    public static String scrapeAvailableSizes(String input) {
        Pattern hugggingFaceReg = Pattern.compile("(\\d+b(?:,\\s*\\d+b)*)");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown sizes";
    }

    public static String scrapePulls(String input) {
        Pattern hugggingFaceReg = Pattern.compile("(\\d+(?:\\.\\d+)?[KM]?)" +
                "\\s+Pulls");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown pulls";
    }

    public static String scrapeTags(String input) {
        Pattern hugggingFaceReg = Pattern.compile("(\\d+)\\s+Tags");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown tags";
    }

    public static String scrapeUpdated(String input) {
        Pattern hugggingFaceReg = Pattern.compile("Updated\\s+(.*)");
        Matcher matcher = hugggingFaceReg.matcher(input);
        return matcher.find() ? matcher.group(1) : "Unknown update date";
    }
}
