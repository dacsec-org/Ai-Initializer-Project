package org.dacss.projectinitai.advisers.implementors;


import org.dacss.projectinitai.advisers.interfaces.ContextIface;

import java.util.function.BiFunction;

/**
 * <h1>{@link ContextIfaceImpl}</h1>
 */
public class ContextIfaceImpl<T> implements ContextIface<T> {


    public ContextIfaceImpl() {
    }

    /**
     * {@link #updateContext(T, T)}
     *
     * @param userRequest
     * @param aiResponse
     * @return T - updated context
     */
    @Override
    public T updateContext(T userRequest, T aiResponse) {
        return null;
    }
}

//    /**
//     * {@link #updateContext(T, T)}
//     *
//     * @param userRequest T
//     * @param aiResponse  T
//     * @return T - updated context
//     */
//    @Override
//    public T updateContext(T userRequest, T aiResponse) {
//        BiFunction<T, T, T> updateContextFunction = CAC::updateContext;
//        return updateContextFunction.apply(userRequest, aiResponse);
//    }
//}
