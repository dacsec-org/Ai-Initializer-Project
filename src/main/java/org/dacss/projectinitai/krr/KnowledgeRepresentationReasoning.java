package org.dacss.projectinitai.krr;

import lombok.Getter;

/**
 * <h1>{@link KnowledgeRepresentationReasoning}</h1>
 * Enum class representing the different types of Knowledge Representation and Reasoning (KRR) techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the KRR technique.
 */
@Getter
public enum KnowledgeRepresentationReasoning {

    KNOWLEDGE_GRAPHS,
    ONTOLOGIES,
    RULE_BASED_SYSTEMS;

    public String getContextMessage() {
        return switch (this) {
            case KNOWLEDGE_GRAPHS -> "Your purpose is to represent information in a graph structure. Use nodes and edges to model relationships between entities.";
            case ONTOLOGIES -> "Your purpose is to define a set of representational primitives. Use these primitives to model a domain and its relationships.";
            case RULE_BASED_SYSTEMS -> "Your purpose is to use rules as the knowledge representation. Apply these rules to infer new information or make decisions.";
        };
    }
}
