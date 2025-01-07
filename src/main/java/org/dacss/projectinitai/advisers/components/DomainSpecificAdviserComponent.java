package org.dacss.projectinitai.advisers.components;

import org.dacss.projectinitai.advisers.DomainSpecificAdviserIface;
import org.springframework.stereotype.Component;

@Component
public class DomainSpecificAdviserComponent<T> implements DomainSpecificAdviserIface<T> {

    @Override
    public T provideExpertise(T message) {
        return message;
    }
}
