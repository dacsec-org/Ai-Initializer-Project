package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface ContextIface<T> {

    T updateContext(T userRequest, T aiResponse);
}
