package org.dacss.provectinitai.advisers;

import org.dacss.projectinitai.contexts.advisers.components.ContextualAdviserComp;
import org.dacss.projectinitai.advisers.interfaces.AIOutputContextualAdviserIface;
import org.dacss.projectinitai.advisers.implementors.ContextFacadeIfaceImpl;
import org.dacss.projectinitai.advisers.interfaces.ContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.DataHandlerContextualAdviserIface;
import org.dacss.projectinitai.advisers.interfaces.UserInputContextualAdviserIface;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ContextsTest {

    @Test
    public void testContextFacadeIface() {
        ContextFacadeIfaceImpl<String> contextFacade = new ContextFacadeIfaceImpl<>(new ContextualAdviserComp<>());
        assertNotNull(contextFacade.getSystemInfo(), "System info should not be null");
    }

    @Test
    public void testUserInputContextualAdviserIface() {
        UserInputContextualAdviserIface<String> userInputAdviser = new ContextFacadeIfaceImpl<>(new ContextualAdviserComp<>());
        assertNotNull(userInputAdviser.processUserInput("test input"), "Processed user input should not be null");
    }

    @Test
    public void testAIOutputContextualAdviserIface() {
        AIOutputContextualAdviserIface<String> aiOutputAdviser = new ContextFacadeIfaceImpl<>(new ContextualAdviserComp<>());
        assertNotNull(aiOutputAdviser.processAIOutput("test output"), "Processed AI output should not be null");
    }

    @Test
    public void testDataHandlerContextualAdviserIface() {
        DataHandlerContextualAdviserIface<String> dataHandlerAdviser = new ContextFacadeIfaceImpl<>(new ContextualAdviserComp<>());
        assertNotNull(dataHandlerAdviser.handleData("test data"), "Handled data should not be null");
    }

    @Test
    public void testContextualAdviserIface() {
        ContextualAdviserIface<String> contextualAdviser = new ContextFacadeIfaceImpl<>(new ContextualAdviserComp<>());
        assertNotNull(contextualAdviser.updateContext("user request", "AI response"), "Updated context should not be null");
    }
}
