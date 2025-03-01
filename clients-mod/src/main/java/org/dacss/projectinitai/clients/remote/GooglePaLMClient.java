package org.dacss.projectinitai.clients.remote;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link GooglePaLMClient}</h1>
 */
public class GooglePaLMClient implements UniversalLLMClientIface {

    private final WebClient webClient;

    @Value("${google.palm.api.key}")
    private String apiKey;

    public GooglePaLMClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://palm.googleapis.com/v1").build();
    }

    @Override
    public Mono<String> prompt(String input) {
        return webClient.post()
                .uri("/generateText")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"prompt\": \"" + input + "\", \"max_tokens\": 150}")
                .retrieve()
                .bodyToMono(String.class);
    }
}