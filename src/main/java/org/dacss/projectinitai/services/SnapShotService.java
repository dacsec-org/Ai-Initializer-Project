package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.snapshots.SnapShotHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@BrowserCallable
@AnonymousAllowed
public class SnapShotService {

    private final SnapShotHandler handle;

    public SnapShotService(SnapShotHandler handle) {
        this.handle = handle;
    }

    private void handleSnapshotAction(String action, String source, String destination) {
        switch (action) {
            case "create":
                handle.createSnapshot(source, destination);
                break;
            case "list":
                handle.listSnapshots(source);
                break;
            case "update":
                handle.copySnapshot(source, destination);
                break;
            case "delete":
                handle.deleteSnapshot(source);
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
