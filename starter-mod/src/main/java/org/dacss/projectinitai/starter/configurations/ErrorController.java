package org.dacss.projectinitai.starter.configurations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.reactive.result.view.ViewResolver;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link ErrorController}</h1>
 * Replaces the white label error page with a custom error page.
 */
@Configuration
public class ErrorController {

    @Bean
    @Order(-2)
    public ErrorWebExceptionHandler globalErrorWebExceptionHandler() {
        return (exchange, ex) -> {
            if (ex instanceof NoResourceFoundException) {
                return ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.TEXT_HTML)
                        .bodyValue("<html><body><h1>Page Not Found</h1><p>The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.</p></body></html>")
                        .flatMap(response -> response.writeTo(exchange, new ServerResponse.Context() {
                            @Override
                            public @NotNull List<HttpMessageWriter<?>> messageWriters() {
                                return HandlerStrategies.withDefaults().messageWriters();
                            }

                            @Override
                            public @NotNull List<ViewResolver> viewResolvers() {
                                return HandlerStrategies.withDefaults().viewResolvers();
                            }
                        }));
            }
            return Mono.error(ex);
        };
    }
}
