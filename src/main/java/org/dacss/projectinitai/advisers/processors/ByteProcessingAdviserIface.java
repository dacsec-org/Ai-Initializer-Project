package org.dacss.projectinitai.advisers.processors;

/**
 * <h1>{@link ByteProcessingAdviserIface}</h1>
 */
@FunctionalInterface
public interface ByteProcessingAdviserIface {
    byte[] processBytes(byte[] byteInputOutput);
}
