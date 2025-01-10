package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;

/**
 * <h1>{@link ServerHealthService}</h1>
 * Backend service for checking the health of the server using spring boot
 * actuator. this service is displayed in the 'src/main/frontend/views/metrics.tsx' file.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class ServerHealthService {
    //todo-> find a way to generate usable metrics of the llm server
    // (socketServer), as the acctuator just
    // displays useless developer metrics that are already known.

    private final RestTemplate restTemplate;

    /**
     * {@link #ServerHealthService()} 0-parameter constructor.
     */
    public ServerHealthService() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setMessageConverters(Collections.singletonList(new StringHttpMessageConverter(StandardCharsets.UTF_8)));
    }

    /**
     * {@link #getMetrics()} method.
     * @return Object - returns the metrics of the server.
     */
    public Object getMetrics() {
        String vaadinServer = "http://localhost:30320";
        return Objects.requireNonNull(restTemplate.getForObject(vaadinServer, String.class));
    }
}
