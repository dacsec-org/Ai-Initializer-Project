package org.dacss.projectinitai.classifications.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link NaiveBayesUtil}</h1>
 */
@Component
public class NaiveBayesUtil {

    public static Flux<Object> classify() {
        //todo: implement
        return Flux.just("NaiveBayesUtil");
    }
}
