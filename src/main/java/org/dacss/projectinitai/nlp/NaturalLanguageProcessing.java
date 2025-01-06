package org.dacss.projectinitai.nlp;

import lombok.Getter;

@Getter
public enum NaturalLanguageProcessing {

    TEXT_GENERATION,
    SENTIMENT_ANALYSIS,
    NAMED_ENTITY_RECOGNITION,
    MACHINE_TRANSLATION,
    TEXT_SUMMARIZATION;
    String value;

    NaturalLanguageProcessing() {}
}
