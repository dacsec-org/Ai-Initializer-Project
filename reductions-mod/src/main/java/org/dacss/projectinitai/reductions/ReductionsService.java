package org.dacss.projectinitai.reductions;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ReductionsService}</h1>
 * Backend hilla endpoint service for dimensionality reduction operations.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class ReductionsService {

    private ReductionsHandler handler;

    /**
     * <h2>{@link #ReductionsService()}</h2>
     * 0-arg constructor to instantiate the {@link ReductionsHandler}.
     */
    public ReductionsService() {
        this.handler = new ReductionsHandler();
    }

    /**
     * <h2>{@link #handleReductionsAction(String, String)}</h2>
     * @param action The action to be performed.
     * @param data The data to be processed.
     * @return The result of the action.
     */
    public Object handleReductionsAction(String action, String data) {
        return switch (ReductionsContexts.valueOf(action.toUpperCase())) {
            case PRINCIPAL_COMPONENT_ANALYSIS -> handler.handlePCA(data);
            case LINEAR_DISCRIMINANT_ANALYSIS -> handler.handleLDA(data);
            case FACTOR_ANALYSIS -> handler.handleFactorAnalysis(data);
            case T_SNE -> handler.handleTSNE(data);
            case UMAP -> handler.handleUMAP(data);
        };
    }
}
