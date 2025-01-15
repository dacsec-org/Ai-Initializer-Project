package org.dacss.projectinitai.processors.components;

import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TextProcessorComp implements StringProcessingAdviserIface {

    private static final Set<String> STOP_WORDS = new HashSet<>(
            Arrays.asList("and", "the", "is", "in", "at", "of", "on", "a", "an"));

    @Override
    public String processString(String stringInputOutput) {
        stringInputOutput = stringInputOutput.toLowerCase(); // Text Normalization
        stringInputOutput = removePunctuation(stringInputOutput); // Remove punctuation
        stringInputOutput = removeStopWords(stringInputOutput); // Remove stop words
        stringInputOutput = normalizeText(stringInputOutput); // Normalize text
        stringInputOutput = removeNumbers(stringInputOutput); // Remove numbers
        stringInputOutput = removeWhitespace(stringInputOutput); // Remove extra whitespace
        stringInputOutput = removeHtmlTags(stringInputOutput); // Remove HTML tags
        stringInputOutput = removeSpecialCharacters(stringInputOutput); // Remove special characters
        stringInputOutput = expandContractions(stringInputOutput); // Expand contractions
        stringInputOutput = removeUrlsAndEmails(stringInputOutput); // Remove URLs and email addresses
        return stringInputOutput;
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
