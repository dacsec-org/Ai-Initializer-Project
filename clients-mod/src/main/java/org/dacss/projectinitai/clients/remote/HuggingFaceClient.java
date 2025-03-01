package org.dacss.projectinitai.clients.remote;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link HuggingFaceClient}</h1>
 */
public class HuggingFaceClient implements UniversalLLMClientIface {

    private final WebClient webClient;

    @Value("${huggingface.api.key}")
    private String apiKey;

    public HuggingFaceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api-inference.huggingface.co/models").build();
    }

    @Override
    public Mono<String> prompt(String input) {
        return webClient.post()
                .uri("/gpt2")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"inputs\": \"" + input + "\"}")
                .retrieve()
                .bodyToMono(String.class);
    }
}
