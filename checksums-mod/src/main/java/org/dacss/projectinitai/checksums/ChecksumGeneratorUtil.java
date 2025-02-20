package org.dacss.projectinitai.checksums;

import reactor.core.publisher.Mono;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumGeneratorUtil {

    public static Mono<Object> generateChecksum(String filePath, String algorithm) {
        return Mono.fromCallable(() -> {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            try (FileInputStream fis = new FileInputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }
            byte[] hashBytes = digest.digest();
            StringBuilder hash = new StringBuilder();
            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        });
    }

    public static Mono<Object> generateSHA256(String filePath) {
        return generateChecksum(filePath, "SHA-256");
    }

    public static Mono<Object> generateSHA512(String filePath) {
        return generateChecksum(filePath, "SHA-512");
    }
}
