//package org.dacss.projectinitai.rags.services;
///**/
//import org.dacss.projectinitai.rags.handlers.RAGHandler;
///**/
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.hilla.BrowserCallable;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
///**
// * <h1>{@link RAGService}</h1>
// * Backend service for handling the RAG (Risk, Assumptions, and Goals) data.
// * This service is displayed in the 'src/main/starter/views/rags.tsx' file.
// */
//@Service
//@BrowserCallable
//@AnonymousAllowed
//public class RAGService {
//
//    private static final Logger log = LoggerFactory.getLogger(RAGService.class);
//    private final RAGHandler handler;
//
//    /**
//     * {@link #RAGService(RAGHandler)} 1-parameter constructor.
//     * @param handler - the RAGHandler object.
//     */
//    public RAGService(RAGHandler handler) {
//        this.handler = handler;
//    }
//
//    /**
//     * {@link #handleRagAction(String, String, String)} method.
//     * @param action - the action to perform.
//     * @param source - the source of the action.
//     * @param destination - the destination of the action.
//     */
//    private void handleRagAction(String action, String source, String destination) {
//        switch (action) {
//            case "create":
//                handler.createRAG(source, destination);
//                break;
//            case "clone":
//                handler.cloneRAG(source, destination);
//                break;
//            case "delete":
//                handler.destroyRAG(source);
//                break;
//            case "list":
//                handler.listConversations(source);
//                break;
//            case "update":
//                handler.updateRAG(source, destination);
//                break;
//            case "vectorize":
//                handler.vectorizeRAG(source);
//                break;
//            default:
//                throw new IllegalArgumentException(STR."Unknown action: \{action}");
//        }
//    }
//}
