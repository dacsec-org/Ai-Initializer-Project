package org.dacss.projectinitai.annotations;

import java.nio.file.Path;
import java.util.Arrays;
import org.dacss.projectinitai.annotations.BridgeRegistry;
import org.dacss.projectinitai.annotations.moks.MokBridgeService;
import org.dacss.projectinitai.annotations.moks.MokOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>{@link BridgeModuleTest}</h1>
 * The BridgeModuleTest class contains unit tests for testing the functionality
 * of the BridgeRegistry and MokBridgeService components in the application.
 * It leverages Spring's ApplicationContext and JUnit for initializing, retrieving,
 * and validating the behavior of registered services.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testBridgeRegistryServiceRetrieval()}</li>
 *     <li>{@link #testMokBridgeService(MokOptions, String)}</li>
 *     <li>{@link #testGeneratedClass()}</li>
 * </ul>
 */
public class BridgeModuleTest {

    private BridgeRegistry bridgeRegistry;

    /**
     * Initialize the Spring `ApplicationContext` and register all services.
     */
    @BeforeClass
    public void setup() {
        System.out.println("Setting up ApplicationContext and initializing BridgeRegistry...");
        ApplicationContext context = new AnnotationConfigApplicationContext(MokBridgeService.class);
        bridgeRegistry = new BridgeRegistry(context);
        System.out.println(STR."BridgeRegistry initialized with the following services: \{Arrays.toString(context.getBeanDefinitionNames())}");
    }

    /**
     * Test to ensure the `BridgeRegistry` properly retrieves the correct MokBridgeService bean.
     */
    @Test
    public void testBridgeRegistryServiceRetrieval() {
        System.out.println("Testing BridgeRegistry service retrieval...");
        Object service = bridgeRegistry.getService("mok-bridge-service");
        System.out.println(STR."Retrieved service: \{service}");

        // Ensure the service retrieved is not null and is an instance of MokBridgeService
        Assert.assertNotNull(service, "The service retrieved should not be null.");
        Assert.assertTrue(service instanceof MokBridgeService, "The service should be an instance of MokBridgeService.");
    }

    /**
     * Test all MokOptions using the `MokBridgeService`.
     * Uses a data provider to verify all possible options provided by the enum `MokOptions`.
     */
    @DataProvider(name = "mokOptionsProvider")
    public Object[][] mokOptionsProvider() {
        System.out.println("Creating data provider for MokOptions...");
        Object[][] dataProvider = {
                {MokOptions.OPTION_1, "MokOptionOne - Sample Data"},
                {MokOptions.OPTION_2, "MokOptionTwo - Sample Data"},
                {MokOptions.OPTION_3, "MokOptionThree - Sample Data"}
        };
        for (Object[] data : dataProvider) {
            System.out.println(STR."Option: \{data[0]}, Expected Output: \{data[1]}");
        }
        return dataProvider;
    }

    @Test
    public void testGeneratedClass() throws Exception {
        // Define the expected file path for the generated controller
        Path generatedControllerPath = Paths.get(
                "target/generated-sources/annotations/MokBridgeServiceController.java"
        );

        // Check if the file exists
        Assert.assertTrue(Files.exists(generatedControllerPath), "Generated controller file is missing.");

        // Optionally, validate the content of the file for expected annotations
        String generatedFileContent = Files.readString(generatedControllerPath);
        Assert.assertTrue(
                generatedFileContent.contains("@RestController"),
                "Generated file does not contain @RestController annotation."
        );
        Assert.assertTrue(
                generatedFileContent.contains("public class MokBridgeServiceController"),
                "Generated file does not contain expected class definition."
        );
    }

    @Test(dataProvider = "mokOptionsProvider")
    public void testMokBridgeService(MokOptions option, String expectedOutput) {
        System.out.println(STR."Testing MokBridgeService with option: \{option}");

        MokBridgeService service = (MokBridgeService) bridgeRegistry.getService("mok-bridge-service");
        System.out.println(STR."Using service: \{service}");

        // Call the method and collect the data from the Flux output
        List<Object> result = new ArrayList<>();
        service.processTestOptions(option).doOnNext(value -> System.out.println(STR."Generated value: \{value}")).toIterable().forEach(result::add);

        // Print the collected result
        System.out.println(STR."Collected result: \{result}");

        // Assert that the emitted value matches the expected output
        Assert.assertEquals(result.size(), 1, "Flux should emit exactly one item.");
        Assert.assertEquals(result.getFirst(), expectedOutput, "Flux emitted value does not match the expected value.");
    }
}
