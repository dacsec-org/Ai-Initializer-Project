package org.dacss.projectinitai.advisers.processors;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>{@link TextProcessor}</h1>
 * Text Pre-Processor class to pre-process text data before feeding it to the AI model.
 */
public class TextProcessor implements ProcessingAdviserIface<String> {

    private static final Set<String> STOP_WORDS = new HashSet<>(
            Arrays.asList("and", "the", "is", "in", "at", "of", "on", "a", "an"));

    @Override
    public String preProcess(String text) {
        text = text.toLowerCase(); // Text Normalization
        text = removePunctuation(text); // Remove punctuation
        text = removeStopWords(text); // Remove stop words
        text = normalizeText(text); // Normalize text
        text = removeNumbers(text); // Remove numbers
        text = removeWhitespace(text); // Remove extra whitespace
        text = removeHtmlTags(text); // Remove HTML tags
        text = removeSpecialCharacters(text); // Remove special characters
        text = expandContractions(text); // Expand contractions
        text = removeUrlsAndEmails(text); // Remove URLs and email addresses
        return text;
    }

    private String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    private String removeStopWords(String text) {
        return Arrays.stream(text.split("\\s+"))
                .filter(word -> !STOP_WORDS.contains(word))
                .collect(Collectors.joining(" "));
    }

    private String normalizeText(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private String removeNumbers(String text) {
        return text.replaceAll("\\d+", "");
    }

    private String removeWhitespace(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    private String removeHtmlTags(String text) {
        return text.replaceAll("<[^>]*>", "");
    }

    private String removeSpecialCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    private String expandContractions(String text) {
        // Implement contraction expansion logic here
        return text.replaceAll("don't", "do not")
                   .replaceAll("can't", "cannot");
    }

    private String removeUrlsAndEmails(String text) {
        return text.replaceAll("https?://\\S+\\s?", "")
                   .replaceAll("\\S+@\\S+\\.\\S+", "");
    }

}
