package org.dacss.projectinitai.models.utilities;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link CreateNewModelUtil}</h1>
 */
public class CreateNewModelUtil {

    public static Flux<Object> createNewModel() {
        //todo: implement model creation
        return Flux.just((Object) "New model created");
    }
}
