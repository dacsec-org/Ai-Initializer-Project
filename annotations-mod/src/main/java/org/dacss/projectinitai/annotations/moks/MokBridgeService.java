package org.dacss.projectinitai.annotations.moks;

import org.dacss.projectinitai.annotations.Bridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * mok test vik
 */
@Service
@Bridge("mok-bridge-service")
public class MokBridgeService implements MokIface {

    @Override
    public Flux<Object> processTestOptions(MokOptions options) {
        Flux<Object> flux;
        try {
            flux = switch (options) {
                case OPTION_1 -> MokOptionOne.optionOneMethod();
                case OPTION_2 -> MokOptionTwo.optionTwoMethod();
                case OPTION_3 -> MokOptionThree.optionThreeMethod();
            };
        } catch (Exception e) {
            return Flux.error(e);
        }
        return flux;
    }
}


