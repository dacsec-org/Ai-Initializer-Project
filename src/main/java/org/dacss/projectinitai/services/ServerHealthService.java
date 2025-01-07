package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertFalseValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    //TODO: implement the ServerHealthService class

    private final RestTemplate restTemplate;

    /**
     * {@link #ServerHealthService()} 0-parameter constructor.
     */
    public ServerHealthService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * {@link #getMetrics()} method.
     * @return Object - returns the metrics of the server.
     */
    public Object getMetrics() {
        String vaadinServer = "http://localhost:30320/metrics";
        return Objects.requireNonNull(
                restTemplate.getForObject(vaadinServer,Object.class));
    }
}
