package org.dacss.projectinitai.krr;

import lombok.Getter;

@Getter
public enum KnowledgeRepresentationReasoning {

    KNOWLEDGE_GRAPHS,
    ONTOLOGIES,
    RULE_BASED_SYSTEMS;

    public String getContextMessage() {
        return switch (this) {
            case KNOWLEDGE_GRAPHS -> "Knowledge Graphs represent information in a graph structure.";
            case ONTOLOGIES -> "Ontologies define a set of representational primitives.";
            case RULE_BASED_SYSTEMS -> "Rule-Based Systems use rules as the knowledge representation.";
        };
    }
}
