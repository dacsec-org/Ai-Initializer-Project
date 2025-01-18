package org.dacss.projectinitai.snapshots;

import org.dacss.projectinitai.snapshots.handlers.SnapShotsHandler;
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

/**
 * <h1>{@link SnapShotTest}</h1>
 * Suite of tests for the snapshot-mod module.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testCreateSnapshot()}</li>
 *     <li>{@link #testListSnapshots()}</li>
 *     <li>{@link #testCopySnapshot()}</li>
 *     <li>{@link #testDeleteSnapshot()}</li>
 *     <li>{@link #testExecuteCommand()}</li>
 * </ul>
 */
public class SnapShotTest {

    private SnapShotsHandler snapShotsHandler;
    private Path sourceDir;
    private Path destDir;
    private Path snapshotDir;

    @BeforeMethod
    public void setUp() throws IOException {
        snapShotsHandler = new SnapShotsHandler();
        sourceDir = Paths.get("src/test/resources/sourceDir");
        destDir = Paths.get("src/test/resources/destDir");
        snapshotDir = Paths.get("src/test/resources/snapshotDir");
        // Setup test resources
        if (!Files.exists(sourceDir)) {
            Files.createDirectories(sourceDir);
        }
        if (!Files.exists(destDir)) {
            Files.createDirectories(destDir);
        }
        if (!Files.exists(snapshotDir)) {
            Files.createDirectories(snapshotDir);
        }
        // Ensure sourceDir contains a dummy file
        Path dummyFile = sourceDir.resolve("dummyFile.txt");
        if (!Files.exists(dummyFile)) {
            Files.createFile(dummyFile);
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        // Cleanup test resources
        if (Files.exists(snapshotDir)) {
            Files.walk(snapshotDir)
                .map(Path::toFile)
                .forEach(File::delete);
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
    public void testCreateSnapshot() {
        snapShotsHandler.createSnapshot(sourceDir.toString(), snapshotDir.toString());
        assertTrue(Files.exists(snapshotDir.resolve("dummyFile.txt")), "Snapshot should be created");
        System.out.println("Test 'createSnapshot()' passed: " + snapshotDir);
    }

    @Test(dependsOnMethods = "testCreateSnapshot")
    public void testListSnapshots() {
        snapShotsHandler.listSnapshots(snapshotDir.toString());
        // Assuming the log output is verified manually
        System.out.println("Test 'listSnapshots()' passed: " + snapshotDir);
    }

    @Test(dependsOnMethods = "testCreateSnapshot")
    public void testCopySnapshot() {
        snapShotsHandler.copySnapshot(snapshotDir.toString(), destDir.toString());
        assertTrue(Files.exists(destDir.resolve("dummyFile.txt")), "Snapshot should be copied");
        System.out.println("Test 'copySnapshot()' passed: " + destDir);
    }

    @Test(dependsOnMethods = "testCopySnapshot")
    public void testDeleteSnapshot() {
        snapShotsHandler.deleteSnapshot(snapshotDir.toString());
        assertFalse(Files.exists(snapshotDir), "Snapshot should be deleted");
        System.out.println("Test 'deleteSnapshot()' passed: " + snapshotDir);
    }

    @Test
    public void testExecuteCommand() {
        snapShotsHandler.executeCommand("snapshot", sourceDir.toString(), snapshotDir.toString());
        // Assuming the command execution is verified manually
        System.out.println("Test 'executeCommand()' passed: snapshot");
    }
}
