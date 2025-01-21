package org.dacss.projrctinitai.cheacksums;
/**/

import org.dacss.projectinitai.checksums.ChecksumHandler;
import org.dacss.projectinitai.checksums.ChecksumsService;
import org.dacss.projectinitai.checksums.utillities.ChecksumVerifierUtil;
import org.dacss.projectinitai.checksums.utillities.ChecksumGeneratorUtil;
/**/
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * <h1>{@link ChecksumsModuleTest}</h1>
 * Suite for the checksums-mod module.
 * <p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testHandleChecksumActionGenerate()}</li>
 *     <li>{@link #testHandleChecksumActionVerify()}</li>
 *     <li>{@link #testVerifyChecksum()}</li>
 *     <li>{@link #testGenerateChecksum()}</li>
 * </ul>
 */
public class ChecksumsModuleTest {

    private ChecksumsService checksumsService;
    private ChecksumHandler checksumHandler;
    private Path testFile;

    @BeforeMethod
    public void setUp() throws IOException {
        checksumHandler = new ChecksumHandler();
        checksumsService = new ChecksumsService(checksumHandler);
        testFile = Paths.get("src/test/resources/testFile.txt");
        if (!Files.exists(testFile)) {
            Files.createFile(testFile);
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
    }

    @Test
    public void testHandleChecksumActionGenerate() {
        String checksum = (String) checksumsService.handleChecksumAction("generate", testFile.toString(), null);
        assertNotNull(checksum, "Checksum should not be null");
        System.out.println("Test 'handleChecksumActionGenerate()' passed: " + checksum);
    }

    @Test(dependsOnMethods = "testHandleChecksumActionGenerate")
    public void testHandleChecksumActionVerify() {
        String checksum = (String) checksumsService.handleChecksumAction("generate", testFile.toString(), null);
        boolean isValid = (boolean) checksumsService.handleChecksumAction("verify", testFile.toString(), checksum);
        assertTrue(isValid, "Checksum should be valid");
        System.out.println("Test 'handleChecksumActionVerify()' passed: " + isValid);
    }

    @Test
    public void testVerifyChecksum() throws IOException, NoSuchAlgorithmException {
        String checksum = ChecksumGeneratorUtil.generateSHA512(testFile.toString());
        boolean isValid = ChecksumVerifierUtil.verifyChecksum(testFile.toString(), checksum);
        assertTrue(isValid, "Checksum should be valid");
        System.out.println("Test 'verifyChecksum()' passed: " + isValid);
    }

    @Test
    public void testGenerateChecksum() throws IOException {
        String checksum = ChecksumGeneratorUtil.generateSHA512(testFile.toString());
        assertNotNull(checksum, "Checksum should not be null");
        System.out.println("Test 'generateChecksum()' passed: " + checksum);
    }
}
