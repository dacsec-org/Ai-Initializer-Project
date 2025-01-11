package org.dacss.projectinitai.loader;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.components.ProcessorFactoryComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LLMProcessorComp {
    private byte[] model;
    private final ContextualAdviserComp<String> contextualAdviserComp;
    private final ProcessorFactoryComp processorFactoryComp;

    @Autowired
    public LLMProcessorComp(ContextualAdviserComp<String> contextualAdviserComp, ProcessorFactoryComp processorFactoryComp) {
        this.contextualAdviserComp = contextualAdviserComp;
        this.processorFactoryComp = processorFactoryComp;
        try {
            String modelPath = "path/to/your/model.blob";
            String expectedChecksum = "your_expected_sha256_checksum";
            if (ChecksumVerifier.verifyChecksum(modelPath, expectedChecksum)) {
                this.model = ModelLoader.loadModel(modelPath);
                // Initialize your model here
            } else {
                log.error("Checksum verification failed");
//                Notification.show("Checksum verification failed");
            }
        } catch (Exception e) {
            log.error("Failed to load the model", e);
//            Notification.show("Failed to load the model: " + e.getMessage());
        }
    }

    public void process(String input) {
        // Use the loaded model to process the input
    }

    public void integrateWithContextualAdviser(String input) {
        contextualAdviserComp.processUserInput(input);
    }

    public void integrateWithProcessorFactory(String input) {
        processorFactoryComp.getStringProcessor(input);
    }

    public void integrateWithContextualAdviserComp(String input) {
        contextualAdviserComp.processUserInput(input);
    }

    public void integrateWithProcessorFactoryComp(String input) {
        processorFactoryComp.getStringProcessor(input);
    }
}
