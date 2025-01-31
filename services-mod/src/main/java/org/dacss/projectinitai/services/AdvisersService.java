package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.advisers.AdviseAction;
import org.dacss.projectinitai.advisers.AdvisersIface;
import org.dacss.projectinitai.advisers.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link AdvisersService}</h1>
 * Service class for the Advisers module.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class AdvisersService implements AdvisersIface {

    private static final Logger log = LoggerFactory.getLogger(AdvisersService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public AdvisersService() {
    }

    @Override
    public Flux<Object> advise(AdviseAction action) {
        Flux<Object> flux;
        try {
            flux = switch (action) {
                case LOG_ACTION -> LoggerUtil.logAction();
                case FORMAT_INPUT -> FormatInUtil.formatInput();
                case FORMAT_OUTPUT -> FormatOutUtil.formatOutput();
                case VALIDATE_INPUT -> ValidateInUtil.validateInput();
                case VALIDATE_OUTPUT -> ValidateOutUtil.validateOutput();
            };
        } catch (Exception advisersServiceExc) {
            log.error(RED + "Error from AdvisersService performing action: {}" + RESET, action, advisersServiceExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "AdvisersService action completed: {}" + RESET, action);
        }
        return flux;
    }
}
