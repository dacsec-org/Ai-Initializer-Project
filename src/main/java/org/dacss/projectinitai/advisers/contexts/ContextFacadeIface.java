package org.dacss.projectinitai.advisers.contexts;

public interface ContextFacadeIface<T> {
    String getSystemInfo();
    String getToolInfo();
    String getUserInfo();
    String getDataInfo();
    T updateContext(T userRequest, T aiResponse);
    T processUserInput(T userRequest);
    T processAIOutput(T aiResponse);
}
