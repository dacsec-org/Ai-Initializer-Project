package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.nlp.NLPIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.dacss.projectinitai.nlp.utillities.MachineTranslationUtil.translateText;
import static org.dacss.projectinitai.nlp.utillities.NamedEntityRecognitionUtil.recognizeNamedEntity;
import static org.dacss.projectinitai.nlp.utillities.SentimentAnalysisUtil.analyzeSentiment;
import static org.dacss.projectinitai.nlp.utillities.TextGenerationUtil.generateText;
import static org.dacss.projectinitai.nlp.utillities.TextSummarizationUtil.summarizeText;

/**
 * <h1>{@link NLPService}</h1>
 * Backend hilla endpoint service for NLP operations.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class NLPService implements NLPIface {

    private static final Logger log = LoggerFactory.getLogger(NLPService.class);

    /**
     * <h2>{@link #NLPService()}</h2>
     */
    public NLPService() {

    }

    /**
     * <h2>{@link #processText()}</h2>
     * Perform NLP on the data.
     */
    @Override
    public void processText(String action, String data) {
        try {
            switch (action) {
                case "text_generation":
                    generateText(data);
                    break;
                case "sentiment_analysis":
                    analyzeSentiment(data);
                    break;
                case "named_entity_recognition":
                    recognizeNamedEntity(data);
                    break;
                case "machine_translation":
                    translateText(data);
                    break;
                case "text_summarization":
                    summarizeText(data);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (Exception nlpServiceExc) {
            log.error("Error processing text: {}", nlpServiceExc.getMessage());
            throw nlpServiceExc;
        }

    }
}
