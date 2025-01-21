package org.dacss.projectinitai.snapshots;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link SnapShotsService}</h1>
 * Backend hilla endpoint service for snapshot operations.
 */
@Service
@BrowserCallable
public class SnapShotsService {

    private SnapShotsHandler handler;

    /**
     * <h2>{@link #SnapShotsService()}</h2>
     * 0-arg constructor to instantiate the {@link SnapShotsHandler}.
     */
    public SnapShotsService() {
        this.handler = new SnapShotsHandler();
    }

    /**
     * <h2>{@link #handleSnapShotsAction(String, String, String)}</h2>
     * @param action The action to be performed.
     * @param source The source path.
     * @param destination The destination path.
     * @return The result of the action.
     */
    public Object handleSnapShotsAction(String action, String source, String destination) {
        return switch (SnapShotsContexts.valueOf(action.toUpperCase())) {
            case CREATE -> handler.handleCreate(source, destination);
            case LIST -> handler.handleList(source);
            case DELETE -> handler.handleDelete(source);
            case COPY -> handler.handleCopy(source, destination);
            case EXECUTE_COMMAND -> handler.handleExecuteCommand(source, destination);
        };
    }
}
