package org.dacss.projectinitai.annotations;

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

public class BridgeModuleTest {
    /*fixme! Reading generated class...
        Unable to read generated class file.
        Check if the file is generated at: target/generated-sources/annotations/org/dacss/projectinitai/annotations/BridgeModuleTest.java
        java.nio.file.NoSuchFileException: target/generated-sources/annotations/org/dacss/projectinitai/annotations/BridgeModuleTest.java
    */

    private BridgeRegistry bridgeRegistry;

    /**
     * Initialize the Spring `ApplicationContext` and register all services.
     */
    @BeforeClass
    public void setup() {
        System.out.println("Setting up ApplicationContext and initializing BridgeRegistry...");
        ApplicationContext context = new AnnotationConfigApplicationContext(MokBridgeService.class);
        bridgeRegistry = new BridgeRegistry(context);
        System.out.println("BridgeRegistry initialized with the following services: " + Arrays.toString(context.getBeanDefinitionNames()));
    }

    /**
     * Test to ensure the `BridgeRegistry` properly retrieves the correct MokBridgeService bean.
     */
    @Test
    public void testBridgeRegistryServiceRetrieval() {
        System.out.println("Testing BridgeRegistry service retrieval...");
        Object service = bridgeRegistry.getService("mok-bridge-service");
        System.out.println("Retrieved service: " + service);

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
            System.out.println("Option: " + data[0] + ", Expected Output: " + data[1]);
        }
        return dataProvider;
    }

    /**
     * Test to retrieve and verify the content of the generated class.
     */
    @Test
    public void testGeneratedClass() {
        System.out.println("Reading generated class...");

        // Dynamically determine the generated class path
        String baseGeneratedPath = "target/generated-sources/annotations"; // Adjust this for Gradle or specific setups
        String generatedClassPath = baseGeneratedPath + "/org/dacss/projectinitai/annotations/BridgeModuleTest.java";

        try {
            // Read the file content as a String
            String generatedClassContent = new String(Files.readAllBytes(Paths.get(generatedClassPath)));
            System.out.println("Generated Class Content:\n" + generatedClassContent);

            // Assert the file content is not empty
            Assert.assertNotNull(generatedClassContent, "Generated class content should not be null.");
            Assert.assertFalse(generatedClassContent.isEmpty(), "Generated class content should not be empty.");
        } catch (IOException e) {
            System.err.println("Unable to read generated class file. Check if the file is generated at: " + generatedClassPath);
            e.printStackTrace();
            Assert.fail("Failed to locate or read the generated class file.");
        }
    }

    @Test(dataProvider = "mokOptionsProvider")
    public void testMokBridgeService(MokOptions option, String expectedOutput) {
        System.out.println("Testing MokBridgeService with option: " + option);

        MokBridgeService service = (MokBridgeService) bridgeRegistry.getService("mok-bridge-service");
        System.out.println("Using service: " + service);

        // Call the method and collect the data from the Flux output
        List<Object> result = new ArrayList<>();
        service.processTestOptions(option).doOnNext(value -> System.out.println("Generated value: " + value)).toIterable().forEach(result::add);

        // Print the collected result
        System.out.println("Collected result: " + result);

        // Assert that the emitted value matches the expected output
        Assert.assertEquals(result.size(), 1, "Flux should emit exactly one item.");
        Assert.assertEquals(result.getFirst(), expectedOutput, "Flux emitted value does not match the expected value.");
    }
}
