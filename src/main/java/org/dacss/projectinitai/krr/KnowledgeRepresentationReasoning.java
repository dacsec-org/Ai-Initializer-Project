package org.dacss.projectinitai.krr;

import lombok.Getter;

@Getter
public enum KnowledgeRepresentationReasoning {

    KNOWLEDGE_GRAPHS,
    ONTOLOGIES,
    RULE_BASED_SYSTEMS;
    String value;

    KnowledgeRepresentationReasoning() {}
}
