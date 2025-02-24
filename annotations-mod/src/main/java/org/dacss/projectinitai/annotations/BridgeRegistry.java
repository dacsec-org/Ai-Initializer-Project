package org.dacss.projectinitai.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BridgeRegistry {

    private final Map<String, Object> bridgeMap;

    public BridgeRegistry(ApplicationContext context) {
        this.bridgeMap = context.getBeansWithAnnotation(Bridge.class)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue().getClass().getAnnotation(Bridge.class).value(),
                        Map.Entry::getValue
                ));
    }

    public Object getService(String bridgeName) {
        return bridgeMap.get(bridgeName);
    }

    public Set<String> getRegisteredServices() {
        return BridgeAnnotationProcessor.getRegisteredServices();
    }
}
