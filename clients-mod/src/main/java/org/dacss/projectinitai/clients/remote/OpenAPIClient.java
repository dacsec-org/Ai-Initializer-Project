package org.dacss.projectinitai.clients.remote;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link OpenAPIClient}</h1>
 */
public class OpenAPIClient implements UniversalLLMClientIface {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    public OpenAPIClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    @Override
    public Mono<String> prompt(String input) {
        return webClient.post()
                .uri("/completions")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"model\": \"text-davinci-003\", \"prompt\": \"" + input + "\", \"max_tokens\": 150}")
                .retrieve()
                .bodyToMono(String.class);
    }
}