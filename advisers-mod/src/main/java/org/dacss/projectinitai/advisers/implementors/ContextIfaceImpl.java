package org.dacss.projectinitai.advisers.implementors;

import org.dacss.projectinitai.contexts.ContextsHandler;
import org.dacss.projectinitai.advisers.interfaces.ContextIface;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiFunction;

/**
 * <h1>{@link ContextIfaceImpl}</h1>
 * Implementation of {@link ContextIface}.
 */
@Component
public class ContextIfaceImpl<T> implements ContextIface<T> {

    /**
     * {@link ContextsHandler}
     */
    private final ContextsHandler<T> CAC;

    /**
     * {@link #ContextIfaceImpl(ContextsHandler)}
     *
     * @param CAC {@link ContextsHandler <T>}
     */
    @Autowired
    public ContextIfaceImpl(ContextsHandler<T> CAC) {
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
