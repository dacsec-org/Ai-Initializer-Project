package org.dacss.projectinitai.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

class BridgeAnnotationTest {

    // Dummy service annotated with @Bridge for testing purposes
    @Bridge("mock-service")
    public static class MockService { }

    @Test
    void testBridgeAnnotationPresence() {
        // Verify that the annotation is present on the MockService class
        Annotation annotation = MockService.class.getAnnotation(Bridge.class);
        assertNotNull(annotation, "@Bridge annotation should be present.");
    }

    @Test
    void testBridgeAnnotationValue() {
        // Verify the value of the annotation
        Bridge bridge = MockService.class.getAnnotation(Bridge.class);
        assertNotNull(bridge, "@Bridge annotation should not be null.");
        assertEquals("mock-service", bridge.value(), "Annotation value should match the expected service name.");
    }

    @Test
    void testBridgeAnnotationRetentionAndTarget() {
        // Verify that the annotation is retained at runtime and targets types
        Bridge bridge = MockService.class.getAnnotation(Bridge.class);
        assertNotNull(bridge, "@Bridge annotation should be retained at runtime.");
        assertTrue(Bridge.class.isAnnotationPresent(Retention.class), "@Bridge annotation should have runtime retention.");
        assertTrue(Bridge.class.isAnnotationPresent(Target.class), "@Bridge annotation should have a target declared.");
    }
}
