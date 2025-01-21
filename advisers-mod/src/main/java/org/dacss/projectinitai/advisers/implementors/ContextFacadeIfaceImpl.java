package org.dacss.projectinitai.advisers.implementors;

import org.dacss.projectinitai.contexts.ContextsHandler;
import org.dacss.projectinitai.advisers.interfaces.ContextFacadeIface;
import org.dacss.projectinitai.contexts.ContextType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <h1>{@link ContextFacadeIfaceImpl}</h1>
 * Implementation of {@link ContextFacadeIface}.
 */
@Component
public class ContextFacadeIfaceImpl<T> implements ContextFacadeIface<T> {

    /**
     * {@link ContextsHandler}
     */
    private final ContextsHandler<T> CAC;

    /**
     * {@link #ContextFacadeIfaceImpl(ContextsHandler)}
     *
     * @param CAC {@link ContextsHandler <T>}
     */
    @Autowired
    public ContextFacadeIfaceImpl(ContextsHandler<T> CAC) {
        this.CAC = CAC;
    }

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest T
     * @param aiResponse  T
     * @return T - updated context
     */
    @Override
    public T updateContext(T userRequest, T aiResponse) {
        BiFunction<T, T, T> updateContextFunction = CAC::updateContext;
        return updateContextFunction.apply(userRequest, aiResponse);
    }
}
