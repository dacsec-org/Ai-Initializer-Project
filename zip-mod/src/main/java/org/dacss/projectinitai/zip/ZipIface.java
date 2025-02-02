package org.dacss.projectinitai.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link ZipIface}</h1>
 * <p>
 *     Functional interface for handling zip operations.
 * </p>
 */
@FunctionalInterface
public interface ZipIface {

    Logger log = LoggerFactory.getLogger(ZipIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    static Object processZipAction(ZipActions action) {
        Object result;
        try {
            result = switch (action) {
                case COMPRESS -> ZipCompressorUtil.createZipFile();
                case EXTRACT -> ZipExtractorUtil.extractZipFile();
                case DESTROY -> ZipDestroyUtil.destroyZipFile();
            };
        } catch (Exception zipExc) {
            log.error(RED + "Error handling zip operation: {}" + RESET, action, zipExc);
            return "Error handling zip operation: " + action;
        } finally {
            log.info(GREEN + "ZipIface action completed: {}" + RESET, action);
        }
        return result;
    }

    Object processZip(ZipActions action);
}
