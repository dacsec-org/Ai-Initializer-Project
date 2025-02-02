package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
//import com.vaadin.hilla.Endpoint;
import org.springframework.stereotype.Service;

/**
 * THIS IS STRAIGHT FROM THE VAADIN PROJECT STARTER. in there version, there is an @index.tsx rather than a view.tsx,
 * but they dont have 20 views like we do. I will try to make this work with the view.tsx file.
 * <p>
 * A simple service that returns a greeting to a person.
 * <p>
 * This service is a Spring service bean, and it is
 * automatically discovered by Spring due to the
 * <code>@Service</code> annotation.
 * <p>
 * The service is also annotated with
 * <code>@Endpoint</code>, which means that it can be
 * called from the frontend using the
 * <code>EndpointUtil</code> class.
 * <p>
 * The service is also annotated with
 * <code>@BrowserCallable</code>, which means that it
 * can be called from the frontend using the
 * <code>BrowserUtil</code> class.
 * <p>
 * The service is also annotated with
 * <code>@AnonymousAllowed</code>, which means that it
 * can be called from the frontend without requiring
 * authentication.
 */
@Service
//@Endpoint
@BrowserCallable
@AnonymousAllowed
public class HelloWorldService {

    /**
     * A simple endpoint method that returns a greeting
     * to a person whose name is given as a parameter.
     * <p>
     * Both the parameter and the return value are
     * automatically considered to be Nonnull, due to
     * existence of <code>package-info.java</code>
     * in the same package that defines a
     * <code>@org.springframework.lang.NonNullApi</code>
     * for the current package.
     * <p>
     * Note that you can override the default Nonnull
     * behavior by annotating the parameter with
     * <code>@dev.hilla.Nullable</code>.
     *
     * @param name that assumed to be nonnull
     * @return a nonnull greeting
     */
    public String sayHello(String name) {
        if (name.isEmpty()) {
            // This is a backend log message
            System.out.println("Hello stranger from the hello world service backend");
            // This is a frontend log message
            return "Hello stranger";
        } else {
            // This is a backend log message
            System.out.println("Hello " + name + " from the hello world service backend");
            // This is a frontend log message
            return "Hello " + name;
        }
    }
}
