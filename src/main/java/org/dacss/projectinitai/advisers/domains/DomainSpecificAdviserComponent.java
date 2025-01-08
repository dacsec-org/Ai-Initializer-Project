package org.dacss.projectinitai.advisers.domains;

import org.springframework.stereotype.Component;

@Component
public class DomainSpecificAdviserComponent<T> implements DomainSpecificAdviserIface<T> {

    @Override
    public T provideExpertise(T message) {
        return message;
    }
}
