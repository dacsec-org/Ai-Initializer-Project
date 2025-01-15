package org.dacss.projectinitai.rags;

import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.advisers.processors.VectorizationProcessor;
import org.dacss.projectinitai.processors.components.VectorizationProcessorComp;

@Slf4j
public class RAGHandler {

    private final RAGCreator creator;
    private final RAGDestroyer destroyer;
    private final RAGCloner cloner;
    private final RAGUpdater updater;
    private final RAGConversationLister lister;
    private final VectorizationProcessor vectorize;

    public RAGHandler(RAGCreator creator, RAGDestroyer destroyer,
                      RAGCloner cloner, RAGUpdater updater, RAGConversationLister lister, VectorizationProcessorComp vectorize) {
        this.creator = creator;
        this.destroyer = destroyer;
        this.cloner = cloner;
        this.updater = updater;
        this.lister = lister;
        this.vectorize = vectorize;
    }

    public void createRAG(String source, String destination) {
        try {
            creator.createRAG(source, destination);
        } catch (Exception createRagExc) {
            log.error("Failed to create RAG", createRagExc);
        }
    }

    public void destroyRAG(String ragPath) {
        try {
            destroyer.destroyRAG(ragPath);
        } catch (Exception destroyRagExc) {
            log.error("Failed to destroy RAG", destroyRagExc);
        }
    }

    public void cloneRAG(String source, String destination) {
        try {
            cloner.cloneRAG(source, destination);
        } catch (Exception cloneRagExc) {
            log.error("Failed to clone RAG", cloneRagExc);
        }
    }

    public void updateRAG(String source, String destination) {
        try {
            updater.updateRAG(source, destination);
        } catch (Exception updateRagExc) {
            log.error("Failed to update RAG", updateRagExc);
        }
    }

    public void listConversations(String source) {
        try {
            lister.listChatHistory(source);
        } catch (Exception listConversationsExc) {
            log.error("Failed to list conversations", listConversationsExc);
        }
    }

    public void vectorizeRAG(String ragPath) {
        try {
            vectorize.processJson(ragPath);
        } catch (Exception vectorizeRagExc) {
            log.error("Failed to vectorize RAG", vectorizeRagExc);
        }
    }

}
