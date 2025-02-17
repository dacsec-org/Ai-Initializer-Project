package org.dacss.projectinitai.servers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link WebController}</h1> class serves as a REST controller in a Spring WebFlux application.
 * It handles HTTP GET requests and routes them to serve static resources or respond based on the
 * specified URL patterns.
 * <p>
 * The controller provides support for rendering the React frontend application by serving the
 * `index.html` file located in the `static` directory whenever a non-API route is accessed.
 * </P>
 * Request Mapping:
 * - A GET request matching "/" or any path that does not start with "api" will be routed to this method.
 * - This enables seamless integration between a React single-page application (SPA) and the backend API.
 */
@RestController
public class WebController {

    /**
     * <h3>{@link #serveFrontend(String)}</h3>
     * Serves the React frontend application by returning the `index.html` file located in the `static` directory.
     * This method is invoked for requests matching the root path or any non-API route.
     *
     * @param path the path variable representing the requested route. It matches routes that do not start with "api".
     * @return a {@code Mono<ClassPathResource>} containing the `index.html` resource to be served for React routes.
     */
    @GetMapping(value = {"/", "/{path:^(?!api).*}/**"}) // Matches all React routes except API endpoints
    public Mono<ClassPathResource> serveFrontend(@PathVariable String path) {
        return Mono.just(new ClassPathResource("../starter-mod/src/main/frontend/index.html"));
    }
}
