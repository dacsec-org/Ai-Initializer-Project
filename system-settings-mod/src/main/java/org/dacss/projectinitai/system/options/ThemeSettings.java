package org.dacss.projectinitai.system.options;

import reactor.core.publisher.Flux;

/**
 * <h1>{@link ThemeSettings}</h1>
 * queries, and sets the theme settings of the framework
 */
public class ThemeSettings {
    public static Flux<Object> getThemeSettings() {
        //todo: implement, this may be better handled with tsx as the themes are in the frontend
        return Flux.empty();
    }
}
