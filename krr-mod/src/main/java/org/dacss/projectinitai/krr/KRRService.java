package org.dacss.projectinitai.krr;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link KRRService}</h1>
 * Backend hilla endpoint service for KRR operations.
 */
@Service
@BrowserCallable
public class KRRService {

    private KRRHandler handler;

    /**
     * <h2>{@link #KRRService()}</h2>
     * 0-arg constructor to instantiate the {@link KRRHandler}.
     */
    public KRRService() {
        this.handler = new KRRHandler();
    }

    /**
     * <h2>{@link #handleKRRAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleKRRAction(String action, String data) {
        return switch (KRRContexts.valueOf(action.toUpperCase())) {
            case KNOWLEDGE_GRAPHS -> handler.handleKnowledgeGraphs(data);
            case ONTOLOGIES -> handler.handleOntologies(data);
            case RULE_BASED_SYSTEMS -> handler.handleRuleBasedSystems(data);
        };
    }
}
