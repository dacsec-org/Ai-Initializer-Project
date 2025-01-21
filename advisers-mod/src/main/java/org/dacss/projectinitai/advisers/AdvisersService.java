package org.dacss.projectinitai.advisers;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h1>{@link AdvisersService}</h1>
 * Service class for the Advisers module.
 */
@Service
@BrowserCallable
public class AdvisersService {

    private final AdviserActionHandler adviserActionHandler;

    @Autowired
    public AdvisersService(AdviserActionHandler adviserActionHandler) {
        this.adviserActionHandler = adviserActionHandler;
    }

    /**
     * This method handles the adviser action.
     * @param action The action to be performed.
     * @param input The input data for the action.
     * @return The result of the action.
     */
    public String handleAdviserAction(String action, String input) {
        switch (action) {
            case "adviseLLM":
                return adviserActionHandler.adviseLLM(input);
            case "handleChecksum":
                return adviserActionHandler.handleChecksum(input);
            case "manageDirectory":
                return adviserActionHandler.manageDirectory(input);
            case "downloadLLM":
                return adviserActionHandler.downloadLLM(input);
            case "manageContext":
                return adviserActionHandler.manageContext(input);
            case "loadLLM":
                return adviserActionHandler.loadLLM(input);
            case "collectMetrics":
                return adviserActionHandler.collectMetrics(input);
            case "createOrMergeLLM":
                return adviserActionHandler.createOrMergeLLM(input);
            case "preProcessData":
                return adviserActionHandler.preProcessData(input);
            case "postProcessData":
                return adviserActionHandler.postProcessData(input);
            case "enforceSecurity":
                return adviserActionHandler.enforceSecurity(input);
            case "manageBackendServer":
                return adviserActionHandler.manageBackendServer(input);
            case "createSnapshot":
                return adviserActionHandler.createSnapshot(input);
            case "handleDataType":
                return adviserActionHandler.handleDataType(input);
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
