package org.dacss.projectinitai.zip;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link ZipModuleTest}</h1>
 * Suite of tests for the zip-mod classes.
 * <p>
 * Methods under test:
 *     <ul>
 *         <li><h3>{@link #testCreateZip()}</h3></li>
 *         <li><h3>{@link #testExtractZip()}</h3></li>
 *         <li><h3>{@link #testVerifyExtraction()}</h3></li>
 *         <li><h3>{@link #testExtractAndDestroyZip()}</h3></li>
 *         <li><h3>{@link #testDeleteZip()}</h3></li>
 *         <li><h3>{@link #testVerifyExtractionFails()}</h3></li>
 *     </ul>
 * </p>
 */
public class ZipModuleTest {

    private Path sourceDir;
    private Path zipFile;
    private Path destDir;

    @BeforeMethod
    public void setUp() throws IOException {
        sourceDir = Paths.get("src/test/resources/sourceDir");
        zipFile = Paths.get("src/test/resources/zipFile.zip");
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
    }

    @AfterSuite
    public void tearDown() {
        // Cleanup test resources
        try {
            if (Files.exists(zipFile)) {
                Files.delete(zipFile);
            }
            if (Files.exists(sourceDir)) {
                try (Stream<Path> paths = Files.walk(sourceDir)) {
                    paths.map(Path::toFile).forEach(file -> {
                        if (!file.delete()) {
                            System.err.println("Failed to delete " + file);
                        }
                    });
                }
            }
            if (Files.exists(destDir)) {
                try (Stream<Path> paths = Files.walk(destDir)) {
                    paths.map(Path::toFile).forEach(file -> {
                        if (!file.delete()) {
                            System.err.println("Failed to delete " + file);
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateZip() {
        ZipCompressor zipCompressor = new ZipCompressor(sourceDir.toString(), zipFile.toString());
        Flux<Object> flux = ZipIface.processZipAction(ZipActions.COMPRESS);

        StepVerifier.create(flux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file created successfully"))
                .expectComplete()
                .verify();

        assertTrue(Files.exists(zipFile), "Zip file should exist");
    }

    @Test(dependsOnMethods = "testCreateZip")
    public void testExtractZip() {
        ZipExtractor zipExtractor = new ZipExtractor(zipFile.toString());
        Flux<Object> flux = ZipIface.processZipAction(ZipActions.EXTRACT);

        StepVerifier.create(flux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file extracted successfully"))
                .expectComplete()
                .verify();

        assertTrue(Files.exists(destDir), "Destination directory should exist");
    }

    @Test(dependsOnMethods = "testExtractZip")
    public void testVerifyExtraction() {
        File destDirFile = destDir.toFile();
        assertTrue(ZipExtractor.verifyExtraction(destDirFile), "Extraction should be verified");
    }

    @Test(dependsOnMethods = "testVerifyExtraction")
    public void testExtractAndDestroyZip() {
        ZipExtractor zipExtractor = new ZipExtractor(zipFile.toString());
        Flux<Object> extractFlux = ZipIface.processZipAction(ZipActions.EXTRACT);
        StepVerifier.create(extractFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file extracted successfully"))
                .expectComplete()
                .verify();

        Flux<Object> destroyFlux = ZipIface.processZipAction(ZipActions.DESTROY);
        StepVerifier.create(destroyFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file deleted successfully"))
                .expectComplete()
                .verify();

        assertFalse(Files.exists(zipFile), "Zip file should be destroyed");
    }

    @Test(dependsOnMethods = "testExtractAndDestroyZip")
    public void testDeleteZip() {
        // Ensure the zip file exists before attempting to delete it
        if (!Files.exists(zipFile)) {
            ZipCompressor zipCompressor = new ZipCompressor(sourceDir.toString(), zipFile.toString());
            Flux<Object> compressFlux = ZipIface.processZipAction(ZipActions.COMPRESS);
            StepVerifier.create(compressFlux)
                    .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file created successfully"))
                    .expectComplete()
                    .verify();
        }

        Flux<Object> destroyFlux = ZipIface.processZipAction(ZipActions.DESTROY);
        StepVerifier.create(destroyFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file deleted successfully"))
                .expectComplete()
                .verify();

        assertFalse(Files.exists(zipFile), "Zip file should be deleted");
    }

    @Test(dependsOnMethods = "testExtractZip")
    public void testVerifyExtractionFails() throws IOException {
        // Remove all files in the destination directory to simulate extraction failure
        try (Stream<Path> paths = Files.walk(destDir)) {
            paths.map(Path::toFile).forEach(file -> {
                if (!file.delete()) {
                    System.err.println("Failed to delete " + file);
                }
            });
        }

        File destDirFile = destDir.toFile();
        assertFalse(ZipExtractor.verifyExtraction(destDirFile), "Extraction should not be verified");
    }
}
