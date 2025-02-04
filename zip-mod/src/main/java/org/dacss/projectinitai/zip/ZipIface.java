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

    /**
     * <h3>{@link #processZip(String)}</h3>
     * Processes the extraction of a zip file.
     *
     * @param compressedLLM The path to the compressed LLM zip file.
     * @return A {@link Flux} that emits a success message if the zip operation is completed successfully.
     */
    Flux<Object> processZip(String compressedLLM);
}
