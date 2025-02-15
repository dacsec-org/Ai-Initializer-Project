//package org.dacss.projectinitai.services;
///**/
//import org.dacss.projectinitai.checksums.ChecksumActions;
//import org.dacss.projectinitai.checksums.ChecksumsIface;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.text.MessageFormat;
//import reactor.core.publisher.Flux;
//
//import static org.dacss.projectinitai.checksums.ChecksumGeneratorUtil.generateSHA512;
//import static org.dacss.projectinitai.checksums.ChecksumVerifierUtil.*;
//
///**
// * <h1>{@link ChecksumsService}</h1>
// * Hilla back-end service class for the Checksums module.
// */
//@Service
//
//import org.dacss.projectinitai.annotations.Bridge;
//
//@Bridge("checksums-service")
//public class ChecksumsService implements ChecksumsIface {
//
//    private static final Logger log = LoggerFactory.getLogger(ChecksumsService.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    public ChecksumsService() {}
//
//    @Override
//    public Flux<Object> calculateChecksum(ChecksumActions action, String filePath, String expectedChecksum) {
//        try {
//            switch (action) {
//                case VERIFY:
//                    verifyFileChecksum(filePath, expectedChecksum);
//                    break;
//                case VERIFY_BYTE_ARRAY:
//                    verifyByteArrayChecksum(filePath.getBytes(), expectedChecksum);
//                    break;
//                case GENERATE:
//                    generateSHA512(filePath);
//                }
//        } catch (IOException | NoSuchAlgorithmException checksumsServiceExc) {
//            log.error(RED + "Error from ChecksumsService performing action: {}" + RESET, action, checksumsServiceExc);
//        } finally {
//            log.info(GREEN + "ChecksumsService action completed: {}" + RESET, action);
//        }
//        return Flux.just(MessageFormat.format("Checksum calculation completed for {0}.", filePath));
//    }
//}
