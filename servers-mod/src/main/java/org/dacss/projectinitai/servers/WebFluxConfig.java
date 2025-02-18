package org.dacss.projectinitai.servers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link WebFluxConfig}</h1>
 * This class configures the Spring WebFlux application, including CORS settings, static resource handling,
 * and routing for HTML fallback and frontend serving.
 */
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    /**
     * <h3>{@link #addCorsMappings(CorsRegistry)}</h3>
     * Configures CORS mappings for the application.
     *
     * @param registry the CORS registry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:30320")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    /**
     * <h3>{@link #staticResourceRouter()}</h3>
     * Configures the router function to serve static resources from the `static` directory.
     *
     * @return a RouterFunction for serving static resources
     */
    @Bean
    public RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/**", new ClassPathResource("frontend/"));
    }

    /**
     * <h3>{@link #htmlFallbackRouter()}</h3>
     * Configures the router function to serve the `index.html` file for any unmatched routes that do not start with `/api`.
     *
     * @return a RouterFunction for serving the HTML fallback
     */
    @Bean
    public RouterFunction<ServerResponse> htmlFallbackRouter() {
        return RouterFunctions.route()
                .GET("/{path:^(?!api).*$}", request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML)
                        .bodyValue(new ClassPathResource("starter-mod/src/main/frontend/index.html")))
                .build();
    }

    /**
     * <h3>{@link #serveFrontend(String)}</h3>
     * Serves the React frontend application by returning the `index.html` file located in the `static` directory.
     * This method is invoked for requests matching the root path or any non-API route.
     *
     * @param path the path variable representing the requested route. It matches routes that do not start with `api`.
     * @return a Mono containing the `index.html` resource to be served for React routes
     */
    @GetMapping(value = {"/", "/{path:^(?!api).*}/**"})
    public Mono<ClassPathResource> serveFrontend(@PathVariable String path) {
        return Mono.just(new ClassPathResource("starter-mod/src/main/frontend/index.html"));
    }
}
