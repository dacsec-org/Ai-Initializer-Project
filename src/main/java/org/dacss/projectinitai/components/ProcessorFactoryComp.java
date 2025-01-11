package org.dacss.projectinitai.components;

import org.dacss.projectinitai.advisers.processors.*;
import org.dacss.projectinitai.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProcessorFactoryComp {

    private final StringProcessingAdviserIface textProcessor;
    private final StringProcessingAdviserIface jsonProcessor;
    private final StringProcessingAdviserIface xmlProcessor;
    private final StringProcessingAdviserIface csvProcessor;
    private final StringProcessingAdviserIface documentProcessor;
    private final StringProcessingAdviserIface encodingProcessor;
    private final StringProcessingAdviserIface htmlProcessor;
    private final StringProcessingAdviserIface imageProcessor;
    private final StringProcessingAdviserIface missingValuesProcessor;
    private final StringProcessingAdviserIface pdfProcessor;
    private final StringProcessingAdviserIface tokenizationProcessor;
    private final StringProcessingAdviserIface vectorizationProcessor;
    private final ByteProcessingAdviserIface videoProcessor;
    private final ByteProcessingAdviserIface audioProcessor;

    @Autowired
    public ProcessorFactoryComp(
            @Qualifier("textProcessor") StringProcessingAdviserIface textProcessor,
            @Qualifier("jsonProcessor") StringProcessingAdviserIface jsonProcessor,
            @Qualifier("xmlProcessor") StringProcessingAdviserIface xmlProcessor,
            @Qualifier("csvProcessor") StringProcessingAdviserIface csvProcessor,
            @Qualifier("documentProcessor") StringProcessingAdviserIface documentProcessor,
            @Qualifier("encodingProcessor") StringProcessingAdviserIface encodingProcessor,
            @Qualifier("htmlProcessor") StringProcessingAdviserIface htmlProcessor,
            @Qualifier("imageProcessor") StringProcessingAdviserIface imageProcessor,
            @Qualifier("missingValuesProcessor") StringProcessingAdviserIface missingValuesProcessor,
            @Qualifier("pdfProcessor") StringProcessingAdviserIface pdfProcessor,
            @Qualifier("tokenizationProcessor") StringProcessingAdviserIface tokenizationProcessor,
            @Qualifier("vectorizationProcessor") StringProcessingAdviserIface vectorizationProcessor,
            @Qualifier("videoProcessor") ByteProcessingAdviserIface videoProcessor,
            @Qualifier("audioProcessor") ByteProcessingAdviserIface audioProcessor) {
        this.textProcessor = textProcessor;
        this.jsonProcessor = jsonProcessor;
        this.xmlProcessor = xmlProcessor;
        this.csvProcessor = csvProcessor;
        this.documentProcessor = documentProcessor;
        this.encodingProcessor = encodingProcessor;
        this.htmlProcessor = htmlProcessor;
        this.imageProcessor = imageProcessor;
        this.missingValuesProcessor = missingValuesProcessor;
        this.pdfProcessor = pdfProcessor;
        this.tokenizationProcessor = tokenizationProcessor;
        this.vectorizationProcessor = vectorizationProcessor;
        this.videoProcessor = videoProcessor;
        this.audioProcessor = audioProcessor;
    }

    public StringProcessingAdviserIface getStringProcessor(MessageType messageType) {
        return switch (messageType) {
            case TEXT -> textProcessor;
            case JSON -> jsonProcessor;
            case XML -> xmlProcessor;
            case CSV -> csvProcessor;
            case DOCUMENT -> documentProcessor;
            case ENCODING -> encodingProcessor;
            case HTML -> htmlProcessor;
            case IMAGE -> imageProcessor;
            case MISSING_VALUES -> missingValuesProcessor;
            case PDF -> pdfProcessor;
            case TOKENIZATION -> tokenizationProcessor;
            case VECTOR -> vectorizationProcessor;
            default -> throw new IllegalArgumentException("Unsupported message type: " + messageType);
        };
    }

    public ByteProcessingAdviserIface getByteProcessor(MessageType messageType) {
        return switch (messageType) {
            case VIDEO -> videoProcessor;
            case AUDIO -> audioProcessor;
            default -> throw new IllegalArgumentException("Unsupported message type: " + messageType);
        };
    }
}
