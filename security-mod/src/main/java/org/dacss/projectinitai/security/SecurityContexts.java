package org.dacss.projectinitai.security;

/**
 * <h1>{@link SecurityContexts}</h1>
 * Enum class representing the different types of Security techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Security technique.
 */
public enum SecurityContexts {
    PROJECT_SECURITY,
    CYBER_SECURITY;

    public String getContextMessage() {
        return switch (this) {
            //for the Admin to handle project security
            case PROJECT_SECURITY -> """
                    Your purpose is to handle project security.
                    Use techniques to ensure the security of the project.
                    """;
            //for the Admin to handle local cybersecurity tools(parrot, kali, etc)
            case CYBER_SECURITY -> """
                    Your purpose is to model and implement cybersecurity measures.
                    Use techniques to protect against cyber threats.
                    """;
        };
    }
}
