package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface ContextualAdviserIface<T> {

    T updateContext(T userRequest, T aiResponse);
}
