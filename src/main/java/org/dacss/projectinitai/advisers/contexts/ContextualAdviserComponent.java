package org.dacss.projectinitai.advisers.contexts;

import org.springframework.stereotype.Component;

@Component
public class ContextualAdviserComponent<T> implements ContextualAdviserIface<T>, UserInputContextualAdviserIface<T>, AIOutputContextualAdviserIface<T> {

    private final StringBuilder context = new StringBuilder();

    @Override
    public T updateContext(T userRequest, T aiResponse) {
        context.append("USER: ").append(userRequest).append("\n");
        context.append("AI: ").append(aiResponse).append("\n");
        return (T) context.toString();
    }

    @Override
    public T processUserInput(T userRequest) {
        // Implement user input processing logic here
        return userRequest; // Placeholder implementation
    }

    @Override
    public T processAIOutput(T aiResponse) {
        // Implement AI output processing logic here
        return aiResponse; // Placeholder implementation
    }

    public String getContext() {
        return context.toString();
    }
}
