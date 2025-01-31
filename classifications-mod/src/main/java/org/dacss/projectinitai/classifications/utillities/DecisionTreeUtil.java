package org.dacss.projectinitai.classifications.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link DecisionTreeUtil}</h1>
 */
@Component
public class DecisionTreeUtil {

    public static Flux<Object> classify() {
        //todo: implement
        return Flux.just("DecisionTreeUtil");
    }

}
