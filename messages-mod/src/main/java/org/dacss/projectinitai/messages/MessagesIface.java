package org.dacss.projectinitai.messages;
/**/

/**
 * <h1>{@link MessagesIface}</h1>
 */
@FunctionalInterface
public interface MessagesIface {
    /**
     * <h2>{@link #processMessages(String, String)}</h2>
     */
    void processMessages(String action, String message);
}
