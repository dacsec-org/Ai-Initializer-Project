package org.dacss.projectinitai.advisers.processors;


import com.fasterxml.jackson.core.JsonProcessingException;

@FunctionalInterface
public interface StringProcessingAdviserIface {
    String processString(String stringInputOutput) throws JsonProcessingException;
}
