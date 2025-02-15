package org.dacss.projectinitai.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h1>{@link BridgeRegistry}</h1>
 * Registry for backend services annotated with {@link Bridge}.
 * <p>
 * This class is responsible for maintaining a map of service names to service instances.
 * It uses the Spring application context to find all beans annotated with {@link Bridge}
 * and registers them in the map.
 * </p>
 */
@Component
public class BridgeRegistry {

    private final Map<String, Object> bridgeMap;

    /**
     * <h3>{@link #BridgeRegistry(ApplicationContext)}</h3>
     * Constructor that initializes the registry with the given Spring application context.
     * @param context The Spring application context.
     */
    public BridgeRegistry(ApplicationContext context) {
        this.bridgeMap = context.getBeansWithAnnotation(Bridge.class)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue().getClass().getAnnotation(Bridge.class).value(),
                        Map.Entry::getValue
                ));
    }

    /**
     * <h3>{@link #getService(String)}</h3>
     * Retrieves a service instance by its name.
     * @param bridgeName The name of the service.
     * @return The service instance.
     */
    public Object getService(String bridgeName) {
        return bridgeMap.get(bridgeName);
    }
}
