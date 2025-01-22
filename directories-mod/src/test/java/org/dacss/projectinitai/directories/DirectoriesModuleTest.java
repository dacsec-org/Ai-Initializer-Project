//todo: refactor tests to accommodate the new implementations

//package org.dacss.projectinitai.directories;
//
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//public class DirectoriesModuleTest {
//
//    private DirFileService dirFileService;
//    private Path testDir;
//    private Path testFile;
//
//    @BeforeMethod
//    public void setUp() throws IOException {
//        dirFileService = new DirFileService();
//        testDir = Paths.get("src/test/resources/testDir");
//        testFile = testDir.resolve("testFile.txt");
//        // Setup test resources
//        if (!Files.exists(testDir)) {
//            Files.createDirectories(testDir);
//        }
//        if (!Files.exists(testFile)) {
//            Files.createFile(testFile);
//        }
//    }
//
//    @AfterSuite
//    public void tearDown() throws IOException {
//        // Cleanup test resources
//        if (Files.exists(testFile)) {
//            Files.delete(testFile);
//        }
//        if (Files.exists(testDir)) {
//            Files.delete(testDir);
//        }
//    }
//
//    @Test
//    public void testCreateDirectory() {
//        String newDirPath = "src/test/resources/newTestDir";
//        dirFileService.handleOperation(DirFileService.Operation.CREATE_DIRECTORY, newDirPath, null);
//        assertTrue(Files.exists(Paths.get(newDirPath)), "Directory should be created");
//        System.out.println(STR."Test 'createDirectory()' passed: \{newDirPath}");
//    }
//
//    @Test(dependsOnMethods = "testCreateDirectory")
//    public void testCreateFile() {
//        String newFilePath = "src/test/resources/newTestFile.txt";
//        dirFileService.handleOperation(DirFileService.Operation.CREATE_FILE, "src/test/resources", "newTestFile.txt");
//        assertTrue(Files.exists(Paths.get(newFilePath)), "File should be created");
//        System.out.println(STR."Test 'createFile()' passed: \{newFilePath}");
//    }
//
//    @Test(dependsOnMethods = "testCreateFile")
//    public void testDeleteDirectory() {
//        dirFileService.handleOperation(DirFileService.Operation.DELETE_DIRECTORY, testDir.toString(), null);
//        assertFalse(Files.exists(testDir), "Directory should be deleted");
//        System.out.println(STR."Test 'deleteDirectory()' passed: \{testDir}");
//    }
//
//    @Test(dependsOnMethods = "testDeleteDirectory")
//    public void testDeleteFile() {
//        dirFileService.handleOperation(DirFileService.Operation.DELETE_FILE, testDir.toString(), "testFile.txt");
//        assertFalse(Files.exists(testFile), "File should be deleted");
//        System.out.println(STR."Test 'deleteFile()' passed: \{testFile}");
//    }
//}
