package org.dacss.projectinitai.contexts.enums.nlp;

/**
 * <h1>{@link NaturalLanguageProcessing}</h1>
 * Enum class representing the different types of Natural Language Processing (NLP) techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the NLP technique
 * to be injected into conversations.
 */
public enum NaturalLanguageProcessing {

    TEXT_GENERATION,
    SENTIMENT_ANALYSIS,
    NAMED_ENTITY_RECOGNITION,
    MACHINE_TRANSLATION,
    TEXT_SUMMARIZATION;

    public String getContextMessage() {
        return switch (this) {
            case TEXT_GENERATION -> "Your purpose is to produce new text based on given input. Generate coherent and contextually relevant text that aligns with the provided prompt.";
            case SENTIMENT_ANALYSIS -> "Your purpose is to determine the sentiment of text. Analyze the given text to identify and classify the sentiment expressed.";
            case NAMED_ENTITY_RECOGNITION -> "Your purpose is to identify entities in text. Extract and classify named entities such as people, organizations, and locations from the given text.";
            case MACHINE_TRANSLATION -> "Your purpose is to translate text from one language to another. Convert the given text into the target language while preserving its meaning.";
            case TEXT_SUMMARIZATION -> "Your purpose is to create a summary of a longer text. Condense the given text into a shorter version while retaining the key information.";
        };
    }
}
