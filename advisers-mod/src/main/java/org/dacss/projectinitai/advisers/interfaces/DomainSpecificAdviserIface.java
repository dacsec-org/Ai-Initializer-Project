package org.dacss.projectinitai.advisers.interfaces;

/**
 * <h1>{@link DomainSpecificAdviserIface}</h1>
 * Functional interface for domain-specific advisers.
 */
@FunctionalInterface
public interface DomainSpecificAdviserIface {
    /**
     * Provide expertise in a specific domain.
     *
     * @param input The input data for providing expertise.
     * @return The expertise provided based on the input data.
     */
    String provideExpertise(String input);
}
