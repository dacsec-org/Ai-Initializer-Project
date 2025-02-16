package org.dacss.projectinitai.annotations;

import javax.lang.model.element.TypeElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnnotationsBridgeTest {

    @Mock
    private ApplicationContext applicationContext;

    private BridgeRegistry bridgeRegistry;
    private BridgeAnnotationProcessor annotationProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Spring ApplicationContext with registered Bridge services
        Map<String, Object> beansWithAnnotation = new HashMap<>();
        beansWithAnnotation.put("testTargetBridgeService", new TestTargetBridgeService());
        when(applicationContext.getBeansWithAnnotation(Bridge.class)).thenReturn(beansWithAnnotation);

        // Initialize the BridgeRegistry
        bridgeRegistry = new BridgeRegistry(applicationContext);

        // Initialize the BridgeAnnotationProcessor
        annotationProcessor = new BridgeAnnotationProcessor();
    }

    @Test
    void testBridgeRegistryRegistersAnnotatedServices() {
        // Check that the BridgeRegistry has properly registered the service
        Object service = bridgeRegistry.getService("testTargetBridgeService");
        assertNotNull(service);
        assertInstanceOf(TestTargetBridgeService.class, service);
    }

    @Test
    void testBridgeRegistryRetrieveNonExistentService() {
        // Expect null for services not registered
        Object service = bridgeRegistry.getService("nonExistentService");
        assertNull(service);
    }

    @Test
    void testBridgeAnnotationProcessorGeneratesClassHeader() {
        // Simulate annotation processing and validate the generated class header
        String packageName = "org.dacss.projectinitai.generated";
        String className = "GeneratedHandler";
        String serviceName = "testService";

        String generatedHeader = annotationProcessor.generateClassHeader(packageName, className, serviceName);
        assertTrue(generatedHeader.contains("package " + packageName));
        assertTrue(generatedHeader.contains("public class " + className));
        assertTrue(generatedHeader.contains(serviceName));
    }

    @Test
    void testBridgeAnnotationProcessorGeneratesMethod() {
        // Simulate annotation processing and validate the generated method
        TypeElement mockTypeElement = null; // Use a mock if necessary
        String serviceName = "testTargetBridgeService";

        String generatedMethod = annotationProcessor.generateMethod(mockTypeElement, serviceName);
        assertTrue(generatedMethod.contains("public"));
        assertTrue(generatedMethod.contains(serviceName));
    }

    @Test
    void testBridgeAnnotationAssociatesServiceNameCorrectly() {
        // Retrieve TestTargetBridgeService with Bridge annotation and validate the name
        TestTargetBridgeService service = (TestTargetBridgeService) bridgeRegistry.getService("testTargetBridgeService");
        assertNotNull(service);
    }

    @Test
    void testTestTargetBridgeServiceProcessingOptions() {
        // Validate that TestTargetBridgeService processes options correctly
        TestTargetBridgeService service = new TestTargetBridgeService();

        Flux<Object> optionOneFlux = service.processTestOptions(TestOptions.OPTION_1);
        assertNotNull(optionOneFlux);

        Flux<Object> optionTwoFlux = service.processTestOptions(TestOptions.OPTION_2);
        assertNotNull(optionTwoFlux);

        Flux<Object> optionThreeFlux = service.processTestOptions(TestOptions.OPTION_3);
        assertNotNull(optionThreeFlux);
    }

    @Test
    void testTestIfaceWorksWithBridgeAnnotation() {
        // Ensure that TestTargetBridgeService works with the TestIface
        TestIface testService = new TestTargetBridgeService();

        Flux<Object> result = testService.processTestOptions(TestOptions.OPTION_1);
        assertNotNull(result);
    }
}
