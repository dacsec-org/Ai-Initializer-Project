package org.dacss.projectinitai.clients.remote;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link CohereClient}</h1>
 */
public class CohereClient implements UniversalLLMClientIface {

    private final WebClient webClient;

    @Value("${cohere.api.key}")
    private String apiKey;

    public CohereClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.cohere.ai/v1").build();
    }

    @Override
    public Mono<String> prompt(String input) {
        return webClient.post()
                .uri("/generate")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"prompt\": \"" + input + "\", \"max_tokens\": 150}")
                .retrieve()
                .bodyToMono(String.class);
    }
}