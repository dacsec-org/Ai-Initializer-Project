package org.dacss.projectinitai.downloaders.utilities;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class LLMLibraryUtilTest {

    @Test
    public void testDownloadLLMJsonFile() {
        LLMLibraryUtil llmLibraryUtil = new LLMLibraryUtil();
        Flux<Object> flux = llmLibraryUtil.downloadLLMJsonFile();

        StepVerifier.create(flux)
                .expectNextMatches(content -> content instanceof String && !((String) content).isEmpty())
                .expectComplete()
                .verify();
    }
}
