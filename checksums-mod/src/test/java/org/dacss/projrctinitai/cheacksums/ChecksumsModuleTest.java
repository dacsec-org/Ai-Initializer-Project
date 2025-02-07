//package org.dacss.projrctinitai.cheacksums;
///**/
//import org.dacss.projectinitai.checksums.ChecksumActions;
//import org.dacss.projectinitai.checksums.ChecksumGeneratorUtil;
//import org.dacss.projectinitai.checksums.ChecksumVerifierUtil;
//import org.dacss.projectinitai.checksums.ChecksumsIface;
///**/
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
///**/
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.NoSuchAlgorithmException;
///**/
//import static org.testng.Assert.assertNotNull;
//import static org.testng.Assert.assertTrue;
//
///**
// * <h1>{@link ChecksumsModuleTest}</h1>
// * Test suite for ChecksumsModule.
// * Methods under test:
// * <ul>
// *     <li>{@link ChecksumGeneratorUtil#generateSHA512(String)}</li>
// *     <li>{@link ChecksumVerifierUtil#verifyFileChecksum(String, String)}</li>
// *     <li>{@link ChecksumVerifierUtil#verifyByteArrayChecksum(byte[], String)}</li>
// *     <li>{@link ChecksumsIface#processChecksumAction(ChecksumActions)}</li>
// *
// */
//public class ChecksumsModuleTest {
//
//    private Path testFile;
//    private byte[] testData;
//
//    @BeforeMethod
//    public void setUp() throws IOException {
//        testFile = Paths.get("src/test/resources/testFile.txt");
//        if (!Files.exists(testFile)) {
//            Files.createFile(testFile);
//        }
//        Files.writeString(testFile, "This is a test file for checksum generation and verification.");
//        testData = "Test data for checksum".getBytes();
//    }
//
//    @AfterSuite
//    public void tearDown() throws IOException {
//        if (Files.exists(testFile)) {
//            Files.delete(testFile);
//        }
//    }
//
//    @Test
//    public void testGenerateChecksum() throws IOException {
//        String checksum = ChecksumGeneratorUtil.generateSHA512(testFile.toString());
//        assertNotNull(checksum, "Checksum should not be null");
//        System.out.println("Test 'generateChecksum()' passed: " + checksum);
//    }
//
//    @Test(dependsOnMethods = "testGenerateChecksum")
//    public void testVerifyFileChecksum() throws IOException, NoSuchAlgorithmException {
//        String checksum = ChecksumGeneratorUtil.generateSHA512(testFile.toString());
//        boolean isValid = ChecksumVerifierUtil.verifyFileChecksum(testFile.toString(), checksum);
//        assertTrue(isValid, "Checksum should be valid");
//        System.out.println("Test 'verifyFileChecksum()' passed: " + true);
//    }
//
//    @Test(dependsOnMethods = "testGenerateChecksum")
//    public void testVerifyByteArrayChecksum() throws NoSuchAlgorithmException, IOException {
//        String hexString = bytesToHex(testData);
//        String checksum = ChecksumGeneratorUtil.generateSHA512(hexString);
//        boolean isValid = ChecksumVerifierUtil.verifyByteArrayChecksum(testData, checksum);
//        assertTrue(isValid, "Checksum should be valid");
//        System.out.println("Test 'verifyByteArrayChecksum()' passed: " + true);
//    }
//
//    @Test(dependsOnMethods = {"testGenerateChecksum", "testVerifyFileChecksum", "testVerifyByteArrayChecksum"})
//    public void testProcessChecksumAction() {
//        Flux<Object> flux = ChecksumsIface.processChecksumAction(ChecksumActions.GENERATE);
//
//        StepVerifier.create(flux)
//                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Generate checksum"))
//                .expectComplete()
//                .verify();
//        System.out.println("Test 'processChecksumAction()' passed!");
//    }
//
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
//}
