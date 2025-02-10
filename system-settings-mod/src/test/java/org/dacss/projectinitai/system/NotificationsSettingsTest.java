package org.dacss.projectinitai.system;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.testng.Assert.*;

/**
 * <h1>{@link NotificationsSettingsTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link NotificationsSettings#getNotificationsSettings()}</li>
 *     <li>{@link NotificationsSettings#getNotificationLevel()}</li>
 *     <li>{@link NotificationsSettings#setNotificationLevel(String)}</li>
 * </ul>
 */
public class NotificationsSettingsTest {

    @BeforeMethod
    public void setUp() {
        // Reset the notification level before each test
        NotificationsSettings.setNotificationLevel("INFO");
    }

    @Test
    public void testGetNotificationsSettings() {
        System.out.println("Running testGetNotificationsSettings");
        Flux<Object> result = NotificationsSettings.getNotificationsSettings();
        StepVerifier.create(result)
                .expectNext("Notification Level: INFO")
                .verifyComplete();
        System.out.println("Completed testGetNotificationsSettings");
    }

    @Test
    public void testGetNotificationLevel() {
        System.out.println("Running testGetNotificationLevel");
        String level = NotificationsSettings.getNotificationLevel();
        assertEquals(level, "INFO");
        System.out.println("Completed testGetNotificationLevel");
    }

    @Test
    public void testSetNotificationLevel() {
        System.out.println("Running testSetNotificationLevel");
        NotificationsSettings.setNotificationLevel("DEBUG");
        String level = NotificationsSettings.getNotificationLevel();
        assertEquals(level, "DEBUG");
        System.out.println("Completed testSetNotificationLevel");
    }

    @Test
    public void testSetNotificationLevelError() {
        System.out.println("Running testSetNotificationLevelError");
        NotificationsSettings.setNotificationLevel("ERROR");
        String level = NotificationsSettings.getNotificationLevel();
        assertEquals(level, "ERROR");
        System.out.println("Completed testSetNotificationLevelError");
    }

    @Test
    public void testSetNotificationLevelWarning() {
        System.out.println("Running testSetNotificationLevelWarning");
        NotificationsSettings.setNotificationLevel("WARNING");
        String level = NotificationsSettings.getNotificationLevel();
        assertEquals(level, "WARNING");
        System.out.println("Completed testSetNotificationLevelWarning");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetInvalidNotificationLevel() {
        System.out.println("Running testSetInvalidNotificationLevel");
        NotificationsSettings.setNotificationLevel("INVALID");
        System.out.println("Completed testSetInvalidNotificationLevel");
    }
}
