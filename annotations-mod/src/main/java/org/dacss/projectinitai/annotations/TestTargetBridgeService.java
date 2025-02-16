package org.dacss.projectinitai.annotations;

import javax.swing.text.html.Option;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.dacss.projectinitai.annotations.TestOptions.*;

/**
 * mok test vik
 */
@Service
@Bridge("testTargetBridgeService")
public class TestTargetBridgeService implements TestIface {

    @Override
    public Flux<Object> processTestOptions(TestOptions options) {
        Flux<Object> flux;
        try {
            flux = switch (options) {
                case OPTION_1 -> OptionOne.optionOneMethod();
                case OPTION_2 -> OptionTwo.optionTwoMethod();
                case OPTION_3 -> OptionThree.optionThreeMethod();
            };
        } catch (Exception e) {
            return Flux.error(e);
        }
        return flux;
    }
}


