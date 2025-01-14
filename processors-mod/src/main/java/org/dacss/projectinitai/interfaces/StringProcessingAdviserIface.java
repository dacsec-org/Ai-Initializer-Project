package org.dacss.projectinitai.interfaces;


import com.fasterxml.jackson.core.JsonProcessingException;

@FunctionalInterface
public interface StringProcessingAdviserIface {
    String processString(String stringInputOutput) throws JsonProcessingException;
}
