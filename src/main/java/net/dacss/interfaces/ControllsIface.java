package net.dacss.interfaces;

/**
 * This functional-interface will provide the methods for interacting with the
 * LLM's
 */
@FunctionalInterface
public interface ControllsIface {
    String interactWithLLM(String input);
}
