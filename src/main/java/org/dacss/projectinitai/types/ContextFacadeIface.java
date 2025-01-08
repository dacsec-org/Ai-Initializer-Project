package org.dacss.projectinitai.types;

import org.dacss.projectinitai.advisers.contexts.AIOutputContextualAdviserIface;
import org.dacss.projectinitai.advisers.contexts.ContextualAdviserIface;
import org.dacss.projectinitai.advisers.contexts.UserInputContextualAdviserIface;

/**
 * <h1>{@link ContextFacadeIface}</h1>
 * @param <T> The type of the context object.
 */
public interface ContextFacadeIface<T> extends
        SystemContextIface
        , ToolContextIface
        , UserContextIface
        , DataContextIface
        , ContextualAdviserIface<T>
        , UserInputContextualAdviserIface<T>
        , AIOutputContextualAdviserIface<T> {
}
