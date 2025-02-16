package org.dacss.projectinitai.classifications.utillities;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link RandomForestUtil}</h1>
 */
@Component
public class RandomForestUtil {

    public static Flux<Object> classify() {
        //todo: implement
        return Flux.just("RandomForestUtil");
    }
}
