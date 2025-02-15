package org.dacss.projectinitai.system;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.testng.Assert.*;

/**
 * <h1>{@link NotificationsTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link Notifications#getNotificationsSettings()}</li>
 *     <li>{@link Notifications#getNotificationLevel()}</li>
 *     <li>{@link Notifications#setNotificationLevel(String)}</li>
 * </ul>
 */
public class NotificationsTest {

    @BeforeMethod
    public void setUp() {
        // Reset the notification level before each test
        Notifications.setNotificationLevel("INFO");
    }

    @Test
    public void testGetNotificationsSettings() {
        System.out.println("Running testGetNotificationsSettings");
        Flux<Object> result = Notifications.getNotificationsSettings();
        StepVerifier.create(result)
                .expectNext("Notification Level: INFO")
                .verifyComplete();
        System.out.println("Completed testGetNotificationsSettings");
    }

    @Test
    public void testGetNotificationLevel() {
        System.out.println("Running testGetNotificationLevel");
        String level = Notifications.getNotificationLevel();
        assertEquals(level, "INFO");
        System.out.println("Completed testGetNotificationLevel");
    }

    @Test
    public void testSetNotificationLevel() {
        System.out.println("Running testSetNotificationLevel");
        Notifications.setNotificationLevel("DEBUG");
        String level = Notifications.getNotificationLevel();
        assertEquals(level, "DEBUG");
        System.out.println("Completed testSetNotificationLevel");
    }

    @Test
    public void testSetNotificationLevelError() {
        System.out.println("Running testSetNotificationLevelError");
        Notifications.setNotificationLevel("ERROR");
        String level = Notifications.getNotificationLevel();
        assertEquals(level, "ERROR");
        System.out.println("Completed testSetNotificationLevelError");
    }

    @Test
    public void testSetNotificationLevelWarning() {
        System.out.println("Running testSetNotificationLevelWarning");
        Notifications.setNotificationLevel("WARNING");
        String level = Notifications.getNotificationLevel();
        assertEquals(level, "WARNING");
        System.out.println("Completed testSetNotificationLevelWarning");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetInvalidNotificationLevel() {
        System.out.println("Running testSetInvalidNotificationLevel");
        Notifications.setNotificationLevel("INVALID");
        System.out.println("Completed testSetInvalidNotificationLevel");
    }
}
