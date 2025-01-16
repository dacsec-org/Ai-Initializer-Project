package org.dacss.projectinitai.advisers.interfaces;

/**
 * <h1>{@link DomainSpecificAdviserIface}</h1>
 * Functional interface for domain-specific advisers.
 */
@FunctionalInterface
public interface DomainSpecificAdviserIface {

    /**
     * {@link #provideExpertise(String)}
     *
     * @param input The input data for the adviser.
     * @return String - expertise provided by the adviser.
     */
    String provideExpertise(String input);
}
