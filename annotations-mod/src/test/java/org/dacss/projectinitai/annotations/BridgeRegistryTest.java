//package org.dacss.projectinitai.annotations;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.context.ApplicationContext;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//class BridgeRegistryTest {
//
//    @Mock
//    private ApplicationContext applicationContext;
//
//    private BridgeRegistry bridgeRegistry;
//
//    // Dummy service annotated with @Bridge for testing purposes
//    @Bridge("test-service")
//    public static class MockService { }
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Create a mocked Spring ApplicationContext with one registered bean
//        Map<String, Object> beansWithAnnotation = new HashMap<>();
//        beansWithAnnotation.put("mockServiceBean", new MockService());
//        when(applicationContext.getBeansWithAnnotation(Bridge.class)).thenReturn(beansWithAnnotation);
//
//        // Initialize the BridgeRegistry
//        bridgeRegistry = new BridgeRegistry(applicationContext);
//    }
//
//    @Test
//    void testServiceRetrievedSuccessfully() {
//        // Retrieve the service using its name
//        Object service = bridgeRegistry.getService("test-service");
//
//        // Verify the service is properly retrieved
//        assertNotNull(service);
//        assertInstanceOf(MockService.class, service);
//    }
//
//    @Test
//    void testServiceNotFound() {
//        // Attempt to retrieve a service that doesn't exist
//        Object service = bridgeRegistry.getService("non-existent-service");
//
//        // Verify no service is returned
//        assertNull(service);
//    }
//
//    @Test
//    void testServiceRegistryContainsExpectedService() {
//        // Check if the BridgeRegistry properly retains the service with the correct name
//        Object service = bridgeRegistry.getService("test-service");
//
//        // Assert the correct class is stored under the expected name
//        assertEquals(MockService.class, service.getClass());
//    }
//}
