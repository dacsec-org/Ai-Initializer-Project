package org.dacss.projectinitai.advisers.components;

import org.dacss.projectinitai.advisers.PostProcessingAdviserIface;
import org.springframework.stereotype.Component;


@Component
public class PostProcessingAdviserComponent<T> implements PostProcessingAdviserIface<T> {

    @Override
    public T postProcess(T aiResponse) { return aiResponse; }
}
