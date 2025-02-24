package org.dacss.projectinitai.messages;

import org.dacss.projectinitai.messages.MessageAction;

/**
 * <h1>{@link Event}</h1>
 * This class represents an event that can be sent to the frontend.
 */
public class Event {
    public static final String SESSION_END = "SESSION_END";
    private MessageAction action;
    private String user;
    private Object payload;

    private Event(MessageAction action, String user, Object payload) {
        this.action = action;
        this.user = user;
        this.payload = payload;
    }

    public static EventBuilder action(MessageAction action) {
        return new EventBuilder(action);
    }

    public String getUser() {
        return user;
    }

    public static class EventBuilder {
        private MessageAction action;
        private String user;
        private Object payload;

        public EventBuilder(MessageAction action) {
            this.action = action;
        }

        public EventBuilder user(String user) {
            this.user = user;
            return this;
        }

        public EventBuilder withPayload(Object payload) {
            this.payload = payload;
            return this;
        }

        public Event build() {
            return new Event(action, user, payload);
        }
    }
}
