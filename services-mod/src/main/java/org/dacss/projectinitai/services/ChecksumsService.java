package org.dacss.projectinitai.services;

import org.dacss.projectinitai.annotations.Bridge;
import org.dacss.projectinitai.checksums.ChecksumActions;
import org.dacss.projectinitai.checksums.ChecksumGeneratorUtil;
import org.dacss.projectinitai.checksums.ChecksumVerifierUtil;
import org.dacss.projectinitai.checksums.ChecksumsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Bridge("checksums-service")
public class ChecksumsService implements ChecksumsIface {

    private static final Logger log = LoggerFactory.getLogger(ChecksumsService.class);
    private String filePath;

    public ChecksumsService() {}

    @Override
    public Flux<Object> calculateChecksum(ChecksumActions action) {
        Mono<Object> mono;
        try {
            mono = switch (action) {
                case SHA256 -> ChecksumGeneratorUtil.generateSHA256(filePath);
                case SHA512 -> ChecksumGeneratorUtil.generateSHA512(filePath);
//                case VERIFY -> ChecksumVerifierUtil.verifyFileChecksum("", "");
            };
        } catch (Exception checksumExc) {
            log.error("{}: ", "ChecksumsIface action failed" + checksumExc.getMessage());
            return Flux.error(checksumExc);
        } finally {
            log.info("{}: ", "ChecksumsIface action completed");
        }
        return mono.flux();
    }
}
