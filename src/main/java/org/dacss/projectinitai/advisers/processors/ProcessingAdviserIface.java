package org.dacss.projectinitai.advisers.processors;

@FunctionalInterface
public interface ProcessingAdviserIface {

    Object process(Object inputOutput);
}
