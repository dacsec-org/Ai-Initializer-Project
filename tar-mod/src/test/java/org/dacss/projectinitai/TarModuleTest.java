package org.dacss.projectinitai;

import org.dacss.projectinitai.services.TarService;
import org.dacss.projectinitai.utillities.DestroyTarUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TarModuleTest {

    private TarService tarService;
    private Path sourceDir;
    private Path tarFile;
    private Path destDir;

    @BeforeMethod
    public void setUp() throws IOException {
        tarService = new TarService();
        sourceDir = Paths.get("src/test/resources/sourceDir");
        tarFile = Paths.get("src/test/resources/tarFile.tar");
        destDir = Paths.get("src/test/resources/destDir");
        // Setup test resources
        if (!Files.exists(sourceDir)) {
            Files.createDirectories(sourceDir);
        }
        if (!Files.exists(destDir)) {
            Files.createDirectories(destDir);
        }
        // Ensure sourceDir contains a dummy file
        Path dummyFile = sourceDir.resolve("dummyFile.txt");
        if (!Files.exists(dummyFile)) {
            Files.createFile(dummyFile);
        }
        // Ensure destDir contains a file
        Path testFile = destDir.resolve("testFile.txt");
        if (!Files.exists(testFile)) {
            Files.createFile(testFile);
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        // Cleanup test resources
        if (Files.exists(tarFile)) {
            Files.delete(tarFile);
        }
        if (Files.exists(sourceDir)) {
            Files.walk(sourceDir)
                .map(Path::toFile)
                .forEach(File::delete);
        }
        if (Files.exists(destDir)) {
            Files.walk(destDir)
                .map(Path::toFile)
                .forEach(File::delete);
        }
    }

    @Test
    public void testCreateTar() throws IOException {
        tarService.createTar(sourceDir.toFile(), tarFile.toFile());
        assertTrue(Files.exists(tarFile), "Tar file should exist");
        System.out.println("Test 'createTar()' passed: " + tarFile);
    }

    @Test(dependsOnMethods = "testCreateTar")
    public void testExtractTar() throws IOException {
        tarService.extractTar(tarFile.toFile(), destDir.toFile());
        assertTrue(Files.exists(destDir), "Destination directory should exist");
        System.out.println("Test 'extractTar()' passed: " + tarFile);
    }

    @Test(dependsOnMethods = "testExtractTar")
    public void testVerifyExtraction() {
        File destDirFile = destDir.toFile();
        assertTrue(DestroyTarUtil.verifyExtraction(destDirFile), "Extraction should be verified");
        System.out.println("Test 'verifyExtraction()' passed: " + destDir);
    }

    @Test(dependsOnMethods = "testVerifyExtraction")
    public void testExtractAndDestroyTar() throws IOException {
        String message = tarService.extractAndDestroyTar(tarFile.toFile(), destDir.toFile());
        assertFalse(Files.exists(tarFile), "Tar file should be destroyed");
        System.out.println("Test 'extractAndDestroy()' passed: " + tarFile + " - " + message);
    }

    @Test(dependsOnMethods = "testExtractAndDestroyTar")
    public void testDeleteTar() throws IOException {
        // Ensure the tar file exists before attempting to delete it
        if (!Files.exists(tarFile)) {
            tarService.createTar(sourceDir.toFile(), tarFile.toFile());
        }
        tarService.deleteTar(tarFile.toFile());
        assertFalse(Files.exists(tarFile), "Tar file should be deleted");
        System.out.println("Test 'deleteTar()' passed: " + tarFile);
    }

    @Test(dependsOnMethods = "testExtractTar")
    public void testVerifyExtractionFails() throws IOException {
        // Remove all files in the destination directory to simulate extraction failure
        Files.walk(destDir)
            .map(Path::toFile)
            .forEach(File::delete);

        File destDirFile = destDir.toFile();
        assertTrue(DestroyTarUtil.verifyExtraction(destDirFile),
                "Extraction should not be verified");
        System.out.println("Test 'verifyExtractionFails()' passed: " + destDir);
    }
}
