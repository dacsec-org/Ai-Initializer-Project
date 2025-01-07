package org.dacss.projectinitai.advisers.components;

import org.dacss.projectinitai.advisers.PreProcessingAdviserIface;
import org.springframework.stereotype.Component;

@Component
public class PreProcessAdviserComponent<T> implements PreProcessingAdviserIface<T> {

    @Override
    public T preProcess(T message) { return message; }
}
