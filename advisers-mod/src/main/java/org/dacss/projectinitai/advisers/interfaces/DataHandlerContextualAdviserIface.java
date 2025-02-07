package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface DataHandlerContextualAdviserIface<T> {

    T handleData(T data);
}
