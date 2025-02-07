package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface AIOutputContextualAdviserIface<T> {

    T processAIOutput(T aiResponse);
}
