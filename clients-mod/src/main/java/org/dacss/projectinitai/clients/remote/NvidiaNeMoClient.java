package org.dacss.projectinitai.clients.remote;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link NvidiaNeMoClient}</h1>
 */
public class NvidiaNeMoClient implements UniversalLLMClientIface {

    private final WebClient webClient;

    @Value("${nvidia.nemo.api.key}")
    private String apiKey;

    public NvidiaNeMoClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.nvidia.com/nemo/v1").build();
    }

    @Override
    public Mono<String> prompt(String input) {
        return webClient.post()
                .uri("/generate")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue("{\"text\": \"" + input + "\"}")
                .retrieve()
                .bodyToMono(String.class);
    }
}