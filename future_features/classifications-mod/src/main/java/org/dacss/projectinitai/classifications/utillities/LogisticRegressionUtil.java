package org.dacss.projectinitai.classifications.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link LogisticRegressionUtil}</h1>
 */
@Component
public class LogisticRegressionUtil {


    public static Flux<Object> classify() {
        //todo: implement
        return Flux.just("LogisticRegressionUtil");
    }
}
