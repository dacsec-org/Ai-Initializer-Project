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
}
