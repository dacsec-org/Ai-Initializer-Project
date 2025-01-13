package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.extern.slf4j.Slf4j;

import org.dacss.projectinitai.rags.RAGHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@BrowserCallable
@AnonymousAllowed
public class RAGService {

    private final RAGHandler handler;

    public RAGService(RAGHandler handler) {
        this.handler = handler;
    }

    private void handleRagAction(String action, String source, String destination) {
        switch (action) {
            case "create":
                handler.createRAG(source, destination);
                break;
            case "clone":
                handler.cloneRAG(source, destination);
                break;
            case "delete":
                handler.destroyRAG(source);
                break;
            case "list":
                handler.listConversations(source);
                break;
            case "update":
                handler.updateRAG(source, destination);
                break;
            case "vectorize":
                handler.vectorizeRAG(source);
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
