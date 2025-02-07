package org.dacss.projectinitai.robotics;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

/**
 * <h1>{@link RoboticsIface}</h1>
 */
@BrowserCallable
@AnonymousAllowed
@FunctionalInterface
public interface RoboticsIface {
    /**
     * <h2>{@link #execute()}</h2>
     * Perform robotics operation on the data.
     */
    void execute();
}
