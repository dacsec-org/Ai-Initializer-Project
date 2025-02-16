package org.dacss.projectinitai.advisers.interfaces;

@FunctionalInterface
public interface DomainSpecificAdviserIface {

    String provideExpertise(String input);
}
