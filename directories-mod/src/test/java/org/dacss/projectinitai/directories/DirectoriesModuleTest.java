package org.dacss.projectinitai.directories;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link DirectoriesModuleTest}</h1>
 * Test suite for DirectoriesModule.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link DirectoriesIface#processDirFile(DirectoryActions, String, String)}</li>
 *     <li>{@link DirFileUtil#createDirectory(String)}</li>
 *     <li>{@link DirFileUtil#createFile(String, String)}</li>
 *     <li>{@link DirFileUtil#deleteDirectory(String)}</li>
 *     <li>{@link DirFileUtil#deleteFile(String, String)}</li>
 * </ul>
 */
public class DirectoriesModuleTest {

    private DirectoriesIface directoriesIface;
    private final String userHome = System.getProperty("user.home");

    @BeforeClass
    public void setUp() {
        directoriesIface = new DirFileUtil();
    }

    @Test
    public void testCreateDirectoryStructure() {
        String baseDirPath = Paths.get(userHome, ".project-ai-initializer/models/newTestDir").toString();
        String[] subDirs = {"configs", "info", "model", "checksums"};
        String[] files = {"config.json", "README.md", "model.safetensors", "checksum.txt"};

        // Create base directory
        Flux<Object> flux = directoriesIface.processDirFile(DirectoryActions.CREATE_DIRECTORY, baseDirPath, null);
        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();
        assertTrue(Files.exists(Paths.get(baseDirPath)), "Base directory should be created");

        // Create subdirectories and files
        for (int i = 0; i < subDirs.length; i++) {
            String subDirPath = Paths.get(baseDirPath, subDirs[i]).toString();
            flux = directoriesIface.processDirFile(DirectoryActions.CREATE_DIRECTORY, subDirPath, null);
            StepVerifier.create(flux)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify();
            assertTrue(Files.exists(Paths.get(subDirPath)), "Subdirectory should be created: " + subDirPath);

            String filePath = Paths.get(subDirPath, files[i]).toString();
            flux = directoriesIface.processDirFile(DirectoryActions.CREATE_FILE, subDirPath, files[i]);
            StepVerifier.create(flux)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify();
            assertTrue(Files.exists(Paths.get(filePath)), "File should be created: " + filePath);
        }

        System.out.println("Test 'createDirectoryStructure()' passed");
    }

    @Test(dependsOnMethods = "testCreateDirectoryStructure")
    public void testDeleteDirectoryStructure() {
        String baseDirPath = Paths.get(userHome, ".project-ai-initializer/models/newTestDir").toString();
        String[] subDirs = {"configs", "info", "model", "checksums"};
        String[] files = {"config.json", "README.md", "model.safetensors", "checksum.txt"};

        // Delete files
        for (int i = 0; i < subDirs.length; i++) {
            String subDirPath = Paths.get(baseDirPath, subDirs[i]).toString();
            String filePath = Paths.get(subDirPath, files[i]).toString();
            Flux<Object> flux = directoriesIface.processDirFile(DirectoryActions.DELETE_FILE, subDirPath, files[i]);
            StepVerifier.create(flux)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify();
            assertFalse(Files.exists(Paths.get(filePath)), "File should be deleted: " + filePath);
        }

        // Delete subdirectories
        for (String subDir : subDirs) {
            String subDirPath = Paths.get(baseDirPath, subDir).toString();
            Flux<Object> flux = directoriesIface.processDirFile(DirectoryActions.DELETE_DIRECTORY, subDirPath, null);
            StepVerifier.create(flux)
                    .expectNextCount(1)
                    .expectComplete()
                    .verify();
            assertFalse(Files.exists(Paths.get(subDirPath)), "Subdirectory should be deleted: " + subDirPath);
        }

        // Delete base directory
        Flux<Object> flux = directoriesIface.processDirFile(DirectoryActions.DELETE_DIRECTORY, baseDirPath, null);
        StepVerifier.create(flux)
                .expectNextCount(1)
                .expectComplete()
                .verify();
        assertFalse(Files.exists(Paths.get(baseDirPath)), "Base directory should be deleted");

        System.out.println("Test 'deleteDirectoryStructure()' passed");
    }
}
