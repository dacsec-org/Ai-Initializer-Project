package org.dacss.projectinitai.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.views.localllms.LLMS;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class LLMLinkScraper {

    public static List<LLMS> scrapeLLMLinks(String url) throws IOException {
        if (url.contains("ollama.com")) {
            return scrapeOllamaLinks(url);
        } else if (url.contains("huggingface.co")) {
            return scrapeHuggingFaceLinks(url);
        }
        return new ArrayList<>();
    }

    private static List<LLMS> scrapeOllamaLinks(String url) throws IOException {
        List<LLMS> llmLinks = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

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

                llms.setInstalled(false);
                llms.setInstallationDate();
                llmLinks.add(llms);
            }
        }
        return llmLinks;
    }

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
                    llms.setInstallationDate();
                    llmLinks.add(llms);
                }
            }
        } catch (IOException e) {
            log.error("Failed to fetch LLM links from Hugging Face", e);
            throw e;
        }
        return llmLinks;
    }

    // todo: reimplement the following methods to display cards with the
    //  scraped data. these were set up for olloama
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
