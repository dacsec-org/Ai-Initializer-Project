package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.springframework.stereotype.Service;

/**
 * A simple service that says hello to a user.
 * if this does not work in the frontend, there are issues.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class HelloWorldService {

    public String sayHello(String name) {
        if (name.isEmpty()) {
            return "Hello stranger";
        } else {
            return "Hello " + name;
        }
    }
}
