//package org.dacss.projectinitai.admin.services;
///**/
//import org.dacss.projectinitai.admin.handlers.AdminHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.hilla.BrowserCallable;
//
///**
// * <h1>{@link AdminService}</h1>
// * <p>
// *     Hilla endpoint class is used to provide services to the admin module via the 'admin.tsx' class file.
// * </p>
// */
//@Service
//@BrowserCallable
//@AnonymousAllowed
//public class AdminService {
//
//    private final AdminHandler adminHandler;
//
//    @Autowired
//    public AdminService(AdminHandler adminHandler) {
//        this.adminHandler = adminHandler;
//    }
//
//    /**
//     * This method handles the admin action.
//     * @param action The action to be performed.
//     * @param source The source path or identifier.
//     * @param destination The destination path or identifier.
//     */
//    public void handleAdminAction(String action, String source, String destination) {
//        switch (action) {
//            case "adviseLLM":
//                adminHandler.adviseLLM(source);
//                break;
//            case "handleChecksum":
//                adminHandler.handleChecksum(source);
//                break;
//            case "manageDirectory":
//                adminHandler.manageDirectory(source);
//                break;
//            case "downloadLLM":
//                adminHandler.downloadLLM(source);
//                break;
//            case "manageContext":
//                adminHandler.manageContext(source);
//                break;
//            case "loadLLM":
//                adminHandler.loadLLM(source);
//                break;
//            case "collectMetrics":
//                adminHandler.collectMetrics();
//                break;
//            case "createOrMergeLLM":
//                adminHandler.createOrMergeLLM(source);
//                break;
//            case "preProcessData":
//                adminHandler.preProcessData(source);
//                break;
//            case "postProcessData":
//                adminHandler.postProcessData(source);
//                break;
//            case "enforceSecurity":
//                adminHandler.enforceSecurity();
//                break;
//            case "manageBackendServer":
//                adminHandler.manageBackendServer(source);
//                break;
//            case "createSnapshot":
//                adminHandler.createSnapshot(source, destination);
//                break;
//            case "handleDataType":
//                adminHandler.handleDataType(source);
//                break;
//            default:
//                throw new IllegalArgumentException(STR."Unknown action: \{action}");
//        }
//    }
//}
