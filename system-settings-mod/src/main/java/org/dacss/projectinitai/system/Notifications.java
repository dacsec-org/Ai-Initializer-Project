package org.dacss.projectinitai.system;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link Notifications}</h1>
 * Queries, and sets the notifications pop-ups setting levels of the framework.
 */
public class Notifications {
    /**
     * Default notification level.
     */
    private static String notificationLevel = "INFO";

    /**
     * Default 0-arg constructor.
     */
    public Notifications() {}

    /**
     * <h3>{@link #getNotificationsSettings()}</h3>
     * Returns the current notifications settings.
     *
     * @return A Flux containing the current notifications settings.
     */
    public static Flux<Object> getNotificationsSettings() {
        return Flux.just("Notification Level: " + notificationLevel);
    }

    /**
     * <h3>{@link #getNotificationLevel()}</h3>
     * Returns the current notification level.
     *
     * @return The current notification level.
     */
    public static String getNotificationLevel() {
        return notificationLevel;
    }

    /**
     * <h3>{@link #setNotificationLevel(String)}</h3>
     * Sets the notification level.
     *
     * @param level The notification level to set.
     */
    public static void setNotificationLevel(String level) {
        switch (level.toUpperCase()) {
            case "DEBUG":
            case "INFO":
            case "WARNING":
            case "ERROR":
                notificationLevel = level;
                break;
            default:
                throw new IllegalArgumentException("Invalid notification level: " + level);
        }
    }
}
