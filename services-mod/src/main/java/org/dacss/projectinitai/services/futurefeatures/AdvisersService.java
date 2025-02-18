package org.dacss.projectinitai.services.futurefeatures;

import org.dacss.projectinitai.advisers.AdviseAction;
import org.dacss.projectinitai.advisers.AdvisersIface;
import org.dacss.projectinitai.advisers.utilities.*;
import org.dacss.projectinitai.annotations.Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link AdvisersService}</h1>
 * Service class for the Advisers module.
 */
@Service
@Bridge("AdvisersService")
public class AdvisersService implements AdvisersIface {

    private static final Logger log = LoggerFactory.getLogger(AdvisersService.class);

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
            log.error("{}: Error from AdvisersService performing action: {}", advisersServiceExc, action);
            return Flux.empty();
        } finally {
            log.info("{}: AdvisersService action completed:", action);
        }
        return flux;
    }
}
