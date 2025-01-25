package org.dacss.projectinitai.admin.contexts;

import java.util.Map;


/**
 * <h1>AdminContext</h1>
 * <p>
 *     This class encapsulates the context information for the admin LLM, detailing its purpose and the usage of various tools within the framework.
 * </p>
 */
public class AdminContext {

    private String currentTask;
    private Map<String, String> toolUsage;
    private Map<String, String> configurations;

    /**
     * Constructor for AdminContext.
     *
     * @param currentTask The current task being performed by the admin LLM.
     * @param toolUsage A map of tool names to their usage descriptions.
     * @param configurations A map of configuration names to their values.
     */
    public AdminContext(String currentTask, Map<String, String> toolUsage, Map<String, String> configurations) {
        this.currentTask = currentTask;
        this.toolUsage = toolUsage;
        this.configurations = configurations;
    }


    public String getCurrentTask() { return currentTask; }

    public void setCurrentTask(String currentTask) { this.currentTask = currentTask; }

    public Map<String, String> getToolUsage() { return toolUsage; }

    public void setToolUsage(Map<String, String> toolUsage) { this.toolUsage = toolUsage; }

    public Map<String, String> getConfigurations() { return configurations; }

    public void setConfigurations(Map<String, String> configurations) { this.configurations = configurations; }

    @Override
    public String toString() {
        return "AdminContext{" +
                "currentTask='" + currentTask + '\'' +
                ", toolUsage=" + toolUsage +
                ", configurations=" + configurations +
                '}';
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
