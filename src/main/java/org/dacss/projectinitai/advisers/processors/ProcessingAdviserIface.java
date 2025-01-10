package org.dacss.projectinitai.advisers.processors;

/**
 * <h1>{@link ProcessingAdviserIface}</h1>
 * Functional Interface for Processing Advisers.
 */
@FunctionalInterface
public interface ProcessingAdviserIface {

    Object process(Object inputOutput);
}
