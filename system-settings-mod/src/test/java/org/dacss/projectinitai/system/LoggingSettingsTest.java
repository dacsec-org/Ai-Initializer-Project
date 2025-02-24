package org.dacss.projectinitai.system;

import org.dacss.projectinitai.system.options.LoggingSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * <h1>{@link LoggingSettingsTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link LoggingSettings#setLogLevel(String)}</li>
 *     <li>{@link LoggingSettings#getLogLevel()}</li>
 *     <li>{@link LoggingSettings#setLogLevel(String)} (invalid)</li>
 *     <li>{@link LoggingSettings#getLogLevel()} (default)</li>
 * </ul>
 */
public class LoggingSettingsTest {

    @BeforeMethod
    public void setUp() {
        // Reset log level to default before each test
        LoggingSettings.setLogLevel("INFO");
    }

    @Test
    public void testSetLogLevel() {
        LoggingSettings.setLogLevel("TRACE");
        assertEquals(LoggingSettings.getLogLevel(), "TRACE", "Log level should be TRACE");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());

        LoggingSettings.setLogLevel("DEBUG");
        assertEquals(LoggingSettings.getLogLevel(), "DEBUG", "Log level should be DEBUG");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());

        LoggingSettings.setLogLevel("INFO");
        assertEquals(LoggingSettings.getLogLevel(), "INFO", "Log level should be INFO");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());

        LoggingSettings.setLogLevel("WARN");
        assertEquals(LoggingSettings.getLogLevel(), "WARN", "Log level should be WARN");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());

        LoggingSettings.setLogLevel("ERROR");
        assertEquals(LoggingSettings.getLogLevel(), "ERROR", "Log level should be ERROR");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());

        LoggingSettings.setLogLevel("FATAL");
        assertEquals(LoggingSettings.getLogLevel(), "FATAL", "Log level should be FATAL");
        System.out.println("Log level set to: " + LoggingSettings.getLogLevel());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSetLogLevelInvalid() {
        LoggingSettings.setLogLevel("INVALID");
    }

    @Test
    public void testGetLogLevel() {
        assertEquals(LoggingSettings.getLogLevel(), "INFO", "Default log level should be INFO");
        System.out.println("Default log level: " + LoggingSettings.getLogLevel());
    }
}
