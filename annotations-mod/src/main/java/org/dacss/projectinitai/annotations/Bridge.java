package org.dacss.projectinitai.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>{@link Bridge}</h1>
 * Annotation to map a front-end TypeScript 'bridge' class to a back-end service.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bridge {
    /**
     * <h3>{@link #value()}</h3>
     * @return The name of the service as a string.
     */
    String value();
}
