package org.dacss.projectinitai.processors.interfaces;


import com.fasterxml.jackson.core.JsonProcessingException;

@FunctionalInterface
public interface StringProcessingAdviserIface {
    String processString(String stringInputOutput) throws JsonProcessingException;
}
