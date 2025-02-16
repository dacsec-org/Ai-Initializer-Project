package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface UserInputContextualAdviserIface<T> {

    T processUserInput(T userRequest);
}
