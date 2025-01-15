package org.dacss.projectinitai.processors.interfaces;

/**
 * <h1>{@link ByteProcessingAdviserIface}</h1>
 */
@FunctionalInterface
public interface ByteProcessingAdviserIface {
    byte[] processBytes(byte[] byteInputOutput);
}
