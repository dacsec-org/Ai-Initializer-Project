package org.dacss.projectinitai.annotations;

import java.util.Arrays;
import java.util.Map;
import org.dacss.projectinitai.annotations.moks.MokBridgeService;
import org.dacss.projectinitai.annotations.moks.MokOptions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BridgeModuleTest {

    private BridgeRegistry bridgeRegistry;
    private ApplicationContext context; // Class-level variable

    @BeforeClass
    public void setup() {
        System.out.println("Setting up ApplicationContext and initializing BridgeRegistry...");
        context = new AnnotationConfigApplicationContext("org.dacss.projectinitai.services", MokBridgeService.class.getPackage().getName());
        bridgeRegistry = new BridgeRegistry(context);
        System.out.println("BridgeRegistry initialized with the following services: " + Arrays.toString(context.getBeanDefinitionNames()));
    }

    @Test
    public void testBridgeRegistryServiceRetrieval() {
        System.out.println("Testing BridgeRegistry service retrieval...");
        Object service = bridgeRegistry.getService("mok-bridge-service");
        System.out.println("Retrieved service: " + service);

        Assert.assertNotNull(service, "The service retrieved should not be null.");
        Assert.assertTrue(service instanceof MokBridgeService, "The service should be an instance of MokBridgeService.");
    }

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

    @Test(dataProvider = "mokOptionsProvider")
    public void testMokBridgeService(MokOptions option, String expectedOutput) {
        System.out.println("Testing MokBridgeService with option: " + option);

        MokBridgeService service = (MokBridgeService) bridgeRegistry.getService("mok-bridge-service");
        System.out.println("Using service: " + service);

        List<Object> result = new ArrayList<>();
        service.processTestOptions(option).doOnNext(value -> System.out.println("Generated value: " + value)).toIterable().forEach(result::add);

        System.out.println("Collected result: " + result);

        Assert.assertEquals(result.size(), 1, "Flux should emit exactly one item.");
        Assert.assertEquals(result.get(0), expectedOutput, "Flux emitted value does not match the expected value.");
    }
}
