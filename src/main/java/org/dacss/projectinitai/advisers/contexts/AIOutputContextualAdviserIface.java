package org.dacss.projectinitai.advisers.contexts;

import org.dacss.projectinitai.generative.Generative;
import org.dacss.projectinitai.optimization.Optimization;

public interface AIOutputContextualAdviserIface<T> {
    T processAIOutput(T aiResponse);
    String getGenerativeContext(Generative generative);
    String getOptimizationContext(Optimization optimization);
}
