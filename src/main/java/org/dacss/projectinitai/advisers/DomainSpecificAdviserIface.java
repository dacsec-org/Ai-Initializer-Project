package org.dacss.projectinitai.advisers;

/**
 * <h1>{@link DomainSpecificAdviserIface}</h1>
 * Interface for Domain Specific Advisers.
 */
@FunctionalInterface
public interface DomainSpecificAdviserIface<T> {
    T provideExpertise(T userRequest);
}
