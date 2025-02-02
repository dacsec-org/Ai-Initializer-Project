package org.dacss.projectinitai.tar;
/**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <h1>{@link TarIface}</h1>
 * <p>
 *     Functional interface for handling tar operations.
 * </p>
 */
@FunctionalInterface
public interface TarIface {

    Logger log = LoggerFactory.getLogger(TarIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    static Object processTarAction(TarActions action) {
        Object result;
        try {
            result = switch (action) {
                case COMPRESS -> TarCompressorUtil.createTarFile();
                case EXTRACT -> TarExtractorUtil.extractTarFile();
                case DESTROY -> TarDestroyUtil.destroyTarFile();
            };
        } catch (Exception tarExc) {
            log.error(RED + "Error handling tar operation: {}" + RESET, action, tarExc);
            return "Error handling tar operation: " + action;
        } finally {
            log.info(GREEN + "TarIface action completed: {}" + RESET, action);
        }
        return result;
    }

    Object processTar(TarActions action);
}
