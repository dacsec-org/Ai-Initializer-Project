package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.advisers.AdvisersIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link AdvisersService}</h1>
 * Service class for the Advisers module.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class AdvisersService implements AdvisersIface {


    private static final Logger log = LoggerFactory.getLogger(AdvisersService.class);

    public AdvisersService() {
    }

//    /**
//     * This method handles the adviser action.
//     * @param action The action to be performed.
//     * @param input The input data for the action.
//     * @return The result of the action.
//     */
//    public String handleAdviserAction(String action, String input) {
//        return switch (action) {
//            case "adviseLLM" -> adviserActionHandler.adviseLLM(input);
//            case "handleChecksum" -> adviserActionHandler.handleChecksum(input);
//            case "manageDirectory" -> adviserActionHandler.manageDirectory(input);
//            case "downloadLLM" -> adviserActionHandler.downloadLLM(input);
//            case "manageContext" -> adviserActionHandler.manageContext(input);
//            case "loadLLM" -> adviserActionHandler.loadLLM(input);
//            case "collectMetrics" -> adviserActionHandler.collectMetrics(input);
//            case "createOrMergeLLM" -> adviserActionHandler.createOrMergeLLM(input);
//            case "preProcessData" -> adviserActionHandler.preProcessData(input);
//            case "postProcessData" -> adviserActionHandler.postProcessData(input);
//            case "enforceSecurity" -> adviserActionHandler.enforceSecurity(input);
//            case "manageBackendServer" -> adviserActionHandler.manageBackendServer(input);
//            case "createSnapshot" -> adviserActionHandler.createSnapshot(input);
//            case "handleDataType" -> adviserActionHandler.handleDataType(input);
//            default -> throw new IllegalArgumentException(STR."Unknown action: \{action}");
//        };
//    }

    /**
     * <h2>{@link AdvisersIface#advise()}</h2>
     * advise on the data.
     */
    @Override
    public void advise() {

    }
}
