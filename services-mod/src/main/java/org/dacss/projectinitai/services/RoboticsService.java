package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.robotics.RoboticsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@BrowserCallable
@AnonymousAllowed
public class RoboticsService implements RoboticsIface {

    private static final Logger log = LoggerFactory.getLogger(RoboticsService.class);

    public RoboticsService() {
    }


    /**
     * <h2>{@link #execute()}</h2>
     * Perform robotics operation.
     */
    @Override
    public void execute() {

    }
}
