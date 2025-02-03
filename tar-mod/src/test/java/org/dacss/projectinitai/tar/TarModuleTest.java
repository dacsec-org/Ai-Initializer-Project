package org.dacss.projectinitai.tar;

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
 * <h1>{@link TarModuleTest}</h1>
 * Suite of tests for the tar-mod classes.
 * <p>
 * Methods under test:
 *     <ul>
 *         <li><h3>{@link #testCreateTar()}</h3></li>
 *         <li><h3>{@link #testExtractTar()}</h3></li>
 *         <li><h3>{@link #testVerifyExtraction()}</h3></li>
 *         <li><h3>{@link #testExtractAndDestroyTar()}</h3></li>
 *         <li><h3>{@link #testDeleteTar()}</h3></li>
 *         <li><h3>{@link #testVerifyExtractionFails()}</h3></li>
 *     </ul>
 * </p>
 */
public class TarModuleTest {

    private Path sourceDir;
    private Path tarFile;
    private Path destDir;

    @BeforeMethod
    public void setUp() throws IOException {
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
    }

    @AfterSuite
    public void tearDown() {
        // Cleanup test resources
        try {
            if (Files.exists(tarFile)) {
                Files.delete(tarFile);
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
    public void testCreateTar() {
        Flux<Object> flux = TarIface.processTarAction(TarActions.COMPRESS);

        StepVerifier.create(flux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Tar file created successfully"))
                .expectComplete()
                .verify();

        assertTrue(Files.exists(tarFile), "Tar file should exist");
    }

    @Test(dependsOnMethods = "testCreateTar")
    public void testExtractTar() {
        Flux<Object> flux = TarIface.processTarAction(TarActions.EXTRACT);

        StepVerifier.create(flux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Tar file extracted successfully"))
                .expectComplete()
                .verify();

        assertTrue(Files.exists(destDir), "Destination directory should exist");
    }

    @Test(dependsOnMethods = "testExtractTar")
    public void testVerifyExtraction() {
        File destDirFile = destDir.toFile();
        assertTrue(TarDestroy.verifyExtraction(destDirFile), "Extraction should be verified");
    }

    @Test(dependsOnMethods = "testVerifyExtraction")
    public void testExtractAndDestroyTar() {
        Flux<Object> extractFlux = TarIface.processTarAction(TarActions.EXTRACT);
        StepVerifier.create(extractFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Tar file extracted successfully"))
                .expectComplete()
                .verify();

        Flux<Object> destroyFlux = TarIface.processTarAction(TarActions.DESTROY);
        StepVerifier.create(destroyFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("All matching files deleted successfully"))
                .expectComplete()
                .verify();

        assertFalse(Files.exists(tarFile), "Tar file should be destroyed");
    }

    @Test(dependsOnMethods = "testExtractAndDestroyTar")
    public void testDeleteTar() {
        // Ensure the tar file exists before attempting to delete it
        if (!Files.exists(tarFile)) {
            Flux<Object> compressFlux = TarIface.processTarAction(TarActions.COMPRESS);
            StepVerifier.create(compressFlux)
                    .expectNextMatches(message -> message instanceof String && ((String) message).contains("Tar file created successfully"))
                    .expectComplete()
                    .verify();
        }

        Flux<Object> destroyFlux = TarIface.processTarAction(TarActions.DESTROY);
        StepVerifier.create(destroyFlux)
                .expectNextMatches(message -> message instanceof String && ((String) message).contains("All matching files deleted successfully"))
                .expectComplete()
                .verify();

        assertFalse(Files.exists(tarFile), "Tar file should be deleted");
    }

    @Test(dependsOnMethods = "testExtractTar")
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
        assertFalse(TarDestroy.verifyExtraction(destDirFile), "Extraction should not be verified");
    }
}
