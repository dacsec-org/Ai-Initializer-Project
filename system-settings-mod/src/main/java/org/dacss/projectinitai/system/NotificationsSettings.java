package org.dacss.projectinitai.system;

import reactor.core.publisher.Mono;

/**
 * <h1>{@link NotificationsSettings}</h1>
 * Queries, and sets the notifications pop-ups setting levels of the framework.
 */
public class NotificationsSettings {
    private static String notificationLevel = "INFO"; // Default notification level

    /**
     * <h3>{@link #NotificationsSettings()}</h3>
     * Private constructor to prevent instantiation.
     */
    private NotificationsSettings() {}

    /**
     * <h3>{@link #getNotificationsSettings()}</h3>
     * Returns the current notifications settings.
     *
     * @return A Mono containing the current notifications settings.
     */
    public static Mono<Object> getNotificationsSettings() {
        return Mono.just("Notification Level: " + notificationLevel);
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
        notificationLevel = level;
    }
}
/**/
