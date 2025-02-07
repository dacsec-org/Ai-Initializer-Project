package org.dacss.projectinitai.messages;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MessagesIface}</h1>
 * <p>
 *     Functional interface for handling messages.
 * </p>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface MessagesIface {

    Flux<Object> processMessages(MessageAction action);
}
