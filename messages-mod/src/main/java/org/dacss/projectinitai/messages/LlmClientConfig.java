package org.dacss.projectinitai.messages;

import org.dacss.projectinitai.clients.UniversalLLMClientIface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = "org.dacss.projectinitai.clients")
public class LlmClientConfig {
    @Bean
    public UniversalLLMClientIface llmClient() {
        return new UniversalLLMClientIface() {
            @Override
            public Mono<String> prompt(String message) {
                return null;
            }
        };
    }
}
