package org.dacss.projectinitai.rags.handlers;
/**/
import org.dacss.projectinitai.processors.components.VectorizationProcessorComp;
import org.dacss.projectinitai.rags.components.ConversationListerComp;
import org.dacss.projectinitai.rags.utilities.RAGCreatorUtil;
import org.dacss.projectinitai.rags.utilities.RAGClonerUtil;
import org.dacss.projectinitai.rags.utilities.RAGDestroyerUtil;
import org.dacss.projectinitai.rags.utilities.RAGUpdaterUtil;
import org.slf4j.Logger;
/**/

/**
 * <h1>{@link RAGHandler}</h1>
 */
public class RAGHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RAGHandler.class);
    private final RAGCreatorUtil creator;
    private final RAGDestroyerUtil destroyer;
    private final RAGClonerUtil cloner;
    private final RAGUpdaterUtil updater;
    private final ConversationListerComp lister;
    private final VectorizationProcessorComp vectorize;

    public RAGHandler(RAGCreatorUtil creator, RAGDestroyerUtil destroyer,
                      RAGClonerUtil cloner, RAGUpdaterUtil updater, ConversationListerComp lister
            , VectorizationProcessorComp vectorize) {
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
