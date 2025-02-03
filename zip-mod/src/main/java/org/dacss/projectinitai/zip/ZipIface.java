package org.dacss.projectinitai.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ZipIface}</h1>
 * Functional interface for handling zip operations.
 */
@FunctionalInterface
public interface ZipIface {

    Logger log = LoggerFactory.getLogger(ZipIface.class);
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

    /**
     * <h3>{@link ZipActions}</h3>
     * Provides the available zip actions.
     *
     * @param action The zip action to be performed.
     * @return A {@link Flux} that emits a success message if the zip operation is completed successfully.
     */
    static Flux<Object> processZipAction(ZipActions action) {
        try {
            return switch (action) {
                case COMPRESS -> ZipCompressor.createZipFile();
                case EXTRACT -> ZipExtractor.extractZipFile();
                case DESTROY -> ZipExtractor.destroyZipFile();
            };
        } catch (Exception zipExc) {
            log.error(RED + "Error handling zip operation: {}" + RESET, action, zipExc);
            return Flux.error(new RuntimeException("Error handling zip operation: " + action));
        } finally {
            log.info(GREEN + "ZipIface action completed: {}" + RESET, action);
        }
    }

    /**
     * <h3>{@link #processZip(ZipActions)}</h3>
     * Processes a zip action.
     *
     * @param action The zip action to be performed.
     * @return A {@link Flux} that emits a success message if the zip operation is completed successfully.
     */
    Flux<Object> processZip(ZipActions action);
}
