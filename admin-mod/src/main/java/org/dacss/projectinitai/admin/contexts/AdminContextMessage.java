package org.dacss.projectinitai.admin.contexts;

/**
 * <h1>{@link AdminContextMessage}</h1>
 * <p>
 * This record encapsulates the context message for the admin LLM.
 * </p>
 */
public record AdminContextMessage(String detailedContextMessage) {

    /**
     * {@link #AdminContextMessage}
     * <p>
     * This constructor initializes the context message for the admin LLM.
     * </p>
     */
    public AdminContextMessage {
        detailedContextMessage =
                ("""
                        {
                        "AdminContext": {
                            "currentTask": "You are a helpful Admin in charge of managing various aspects of the framework.",
                            "toolUsage": {
                                "LLMAdviser": "Advising other LLMs.",
                                "ChecksumHandler": "Handling checksums.",
                                "ContextManager": "Managing contexts.",
                                "DirectoryManager": "Managing directories.",
                                "LLMDownloader": "Downloading LLM models.",
                                "LLMLoader": "Loading and unloading LLMs.",
                                "MetricsCollector": "Collecting metrics.",
                                "LLMCreator": "Creating or merging LLMs.",
                                "PrePostProcessor": "Handling pre-processing and post-processing tasks.",
                                "RAGEnhancer": "Enhancing LLMs with RAG functionality.",
                                "SecurityManager": "Enforcing security best practices.",
                                "BackendServerManager": "Managing backend servers.",
                                "SnapshotManager": "Creating and managing snapshots.",
                                "DataTypeHandler": "Handling various data types."
                            },
                            "configurations": {}
                                          }
                                        }
                         }
                        """);
    }

    private static final StringBuilder context = new StringBuilder();
    private String lastUserRequest;
    private String lastAIResponse;

    public String updateContext(String userRequest, String aiResponse) {
        try {
            lastUserRequest = userRequest;
            lastAIResponse = aiResponse;
            context.append("USER: ").append(userRequest).append("\n");
            context.append("AI: ").append(aiResponse).append("\n");
            return userRequest;
        } catch (Exception updateContextExc) {
            return null;
        }
    }

    public String processUserInput(String userRequest) {
        try {
            return userRequest;
        } catch (Exception processUserInputExc) {
            return null;
        }
    }

    public String processAIOutput(String aiResponse) {
        try {
            return aiResponse;
        } catch (Exception processAIOutputExc) {
            return null;
        }
    }

    public String getContext() {
        return context.toString();
    }

    public void clearContext() {
        context.setLength(0);
    }

    public void addCustomContextEntry(String entry) {
        context.append(entry).append("\n");
    }
}
