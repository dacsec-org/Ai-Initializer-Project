package org.dacss.projectinitai.downloaders;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <h1>{@link LLMDownloaderTest}</h1>
 * Test class for {@link LLMDownloader}.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link LLMDownloader#downloadLLM(DownloadAction, String)}</li>
 * </ul>
 */
public class LLMDownloaderTest {

    private LLMDownloader downloader;
    private DownloadAction action;
    private String baseUrl;

    @SuppressWarnings("InstantiationOfUtilityClass")
    @BeforeClass
    public void setUp() {
        baseUrl = "https://huggingface.co/Qwen/Qwen2.5-0.5B/resolve/main/";
        downloader = new LLMDownloader(baseUrl);
        action = DownloadAction.DOWNLOAD_LLM_MODEL;
    }

    @Test
    public void testFileExistsInDirectory() {
        String testFileUrl = baseUrl + "LICENSE?download=true";
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(testFileUrl)).GET().build();

        Mono<HttpResponse<Void>> responseMono = Mono.fromFuture(client.sendAsync(request, HttpResponse.BodyHandlers.discarding()));

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.statusCode() == 200)
                .expectComplete()
                .verify();
    }
}
