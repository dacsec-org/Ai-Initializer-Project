package org.dacss.projectinitai.directories;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link DirectoriesModuleTest}</h1>
 * Test suite for DirectoriesModule.
 * Methods under test:
 * <ul>
 *     <li>{@link CreateDirFileUtil#createDirectory(String)}</li>
 *     <li>{@link CreateDirFileUtil#createFile(String, String)}</li>
 *     <li>{@link DestroyDirFileUtil#deleteDirectory(String)}</li>
 *     <li>{@link DestroyDirFileUtil#deleteFile(String, String)}</li>
 * </ul>
 */
public class DirectoriesModuleTest {

    private Path testDir;
    private Path testFile;

    @BeforeMethod
    public void setUp() throws IOException {
        testDir = Paths.get("src/test/resources/testDir");
        testFile = testDir.resolve("testFile.txt");
        // Setup test resources
        if (!Files.exists(testDir)) {
            Files.createDirectories(testDir);
        }
        if (!Files.exists(testFile)) {
            Files.createFile(testFile);
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        // Cleanup test resources
        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
        if (Files.exists(testDir)) {
            Files.delete(testDir);
        }
    }

    @Test
    public void testCreateDirectory() {
        String newDirPath = "src/test/resources/newTestDir";
        Flux<Object> flux = CreateDirFileUtil.createDirectory(newDirPath);

        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();

        assertTrue(Files.exists(Paths.get(newDirPath)), "Directory should be created");
        System.out.println("Test 'createDirectory()' passed: " + newDirPath);
    }

    @Test(dependsOnMethods = "testCreateDirectory")
    public void testCreateFile() {
        String newFilePath = "src/test/resources/newTestFile.txt";
        Flux<Object> flux = CreateDirFileUtil.createFile("src/test/resources", "newTestFile.txt");

        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();

        assertTrue(Files.exists(Paths.get(newFilePath)), "File should be created");
        System.out.println("Test 'createFile()' passed: " + newFilePath);
    }

    @Test(dependsOnMethods = "testCreateFile")
    public void testDeleteDirectory() {
        Flux<Object> flux = DestroyDirFileUtil.deleteDirectory(testDir.toString());

        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();

        assertFalse(Files.exists(testDir), "Directory should be deleted");
        System.out.println("Test 'deleteDirectory()' passed: " + testDir);
    }

    @Test(dependsOnMethods = "testDeleteDirectory")
    public void testDeleteFile() {
        Flux<Object> flux = DestroyDirFileUtil.deleteFile(testDir.toString(), "testFile.txt");

        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();

        assertFalse(Files.exists(testFile), "File should be deleted");
        System.out.println("Test 'deleteFile()' passed: " + testFile);
    }
}
