package org.dacss.projectinitai.nlp;

import lombok.Getter;

/**
 * <h1>{@link NaturalLanguageProcessing}</h1>
 */
@Getter
public enum NaturalLanguageProcessing {

    TEXT_GENERATION,
    SENTIMENT_ANALYSIS,
    NAMED_ENTITY_RECOGNITION,
    MACHINE_TRANSLATION,
    TEXT_SUMMARIZATION;

    public String getContextMessage() {
        return switch (this) {
            case TEXT_GENERATION -> "Text Generation models produce new text based on given input.";
            case SENTIMENT_ANALYSIS -> "Sentiment Analysis determines the sentiment of text.";
            case NAMED_ENTITY_RECOGNITION -> "Named Entity Recognition identifies entities in text.";
            case MACHINE_TRANSLATION -> "Machine Translation translates text from one language to another.";
            case TEXT_SUMMARIZATION -> "Text Summarization creates a summary of a longer text.";
        };
    }
}
