package org.dacss.projectinitai.checksums;

import org.testng.Assert;
import org.testng.annotations.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * <h1>{@link ChecksumsModuleTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link ChecksumGeneratorUtil#generateSHA256(String)}</li>
 *     <li>{@link ChecksumGeneratorUtil#generateSHA512(String)}</li>
 *     <li>{@link ChecksumVerifierUtil#verifyFileChecksum(String, String)}</li>
 * </ul>
 */
public class ChecksumsModuleTest {

    private final Path path = Paths.get("../downloaders-mod/src/main/resources/qwen2.5/model.safetensors");
    private final String expectedSHA256ChecksumValue = "88c142557820ccad55bb59756bfcfcf891de9cc6202816bd346445188a0ed342";
    private final String expectedSHA512ChecksumValue = "e356b9e8d39dbf8d495ca54393445713792ed6e738c9ef6eb5cd60f5abfb746ba4f2d680327a6ca23e1a101d938d38251a1c919567958a153a13eb90fb99bf51";

    @Test(priority = 1)
    public void testGenerateSHA256() {
        Mono<Object> checksumMono = ChecksumGeneratorUtil.generateSHA256(String.valueOf(path));
        StepVerifier.create(checksumMono)
                .expectNextMatches(checksum -> checksum.equals(expectedSHA256ChecksumValue))
                .verifyComplete();
    }

    @Test(priority = 2)
    public void testGenerateSHA512() {
        Mono<Object> checksumMono = ChecksumGeneratorUtil.generateSHA512(String.valueOf(path));
        StepVerifier.create(checksumMono)
                .expectNextMatches(checksum -> checksum.equals(expectedSHA512ChecksumValue))
                .verifyComplete();
    }

    @Test(priority = 3)
    public void testVerifyFileChecksum() throws IOException, NoSuchAlgorithmException {
        boolean resultSHA256 = ChecksumVerifierUtil.verifyFileChecksum(String.valueOf(path), expectedSHA256ChecksumValue);
        boolean resultSHA512 = ChecksumVerifierUtil.verifyFileChecksum(String.valueOf(path), expectedSHA512ChecksumValue);
        if (resultSHA256) {
            Assert.assertTrue(true, "File checksum verification for SHA-256 failed");
        } else if (resultSHA512) {
            Assert.assertTrue(true, "File checksum verification for SHA-512 failed");
        } else {
            Assert.fail("File checksum verification failed for both SHA-256 and SHA-512");
        }
    }
}