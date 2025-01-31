package org.dacss.projectinitai.messages;
/**/

import reactor.core.publisher.Flux;

/**
 * <h1>{@link MessagesIface}</h1>
 */
@FunctionalInterface
public interface MessagesIface {

    Flux<Object> processMessages(MessageAction action);
}
