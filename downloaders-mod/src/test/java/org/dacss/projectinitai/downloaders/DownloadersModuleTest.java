package org.dacss.projectinitai.downloaders;

import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * <h1>{@link DownloadersModuleTest}</h1>
 * Test suite for DownloadersModule.
 * Methods under test:
 * <ul>
 *     <li>{@link LLMLibraryUtil#downloadLLMJsonFile()}</li>
 *     <li>{@link LLMDownloader#downloadLLMModel()}</li>
 *     <li>{@link DownloadersIface#download(DownloadAction)}</li>
 *     <li>{@link DownloadAction}</li>
 * </ul>
 */
public class DownloadersModuleTest {

    /**
     * Test for the method works as intended. Yet test fails (test framework quit unexpectedly).
     */
    @Test
    public void testDownloadLLMJsonFile() {
        Flux<Object> flux = LLMLibraryUtil.downloadLLMJsonFile();

        StepVerifier.create(flux)
                .expectNextMatches(content -> content instanceof String && !((String) content).isEmpty())
                .expectComplete()
                .verify();
        System.out.println("\033[32m Test passed!\033[0m");
    }

    @Test
    public void testDownloadLLMModel() {
        String modelId = "test-model-id";
        Flux<Object> flux = LLMDownloader.downloadLLMModel();

        StepVerifier.create(flux)
                .expectNextMatches(content -> content instanceof String && !((String) content).isEmpty())
                .expectComplete()
                .verify();
    }

    @Test
    public void testDownloadersIface() {
        DownloadersIface downloadersIface = action -> LLMLibraryUtil.downloadLLMJsonFile();

        Flux<Object> flux = downloadersIface.download(DownloadAction.DOWNLOAD_LLM_JSON);

        StepVerifier.create(flux)
                .expectNextMatches(content -> content instanceof String && !((String) content).isEmpty())
                .expectComplete()
                .verify();
    }

    @Test
    public void testDownloadAction() {
        DownloadAction action = DownloadAction.DOWNLOAD_LLM_JSON;
    }
}
