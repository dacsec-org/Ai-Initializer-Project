//package org.dacss.projectinitai.services;
//
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.hilla.Endpoint;
//import org.dacss.projectinitai.security.SecurityActions;
//import org.dacss.projectinitai.security.SecurityIface;
//import org.dacss.projectinitai.security.SecurityApiTokenUtil;
//import com.vaadin.hilla.BrowserCallable;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//
///**
// * <h1>{@link SecurityService}</h1>
// * Backend hilla endpoint service for security operations.
// */
//@Service
//@Endpoint
//@BrowserCallable
//@AnonymousAllowed
//public class SecurityService implements SecurityIface {
//
//    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    public SecurityService() {}
//
//    @Override
//    public Flux<Object> secure(SecurityActions action) {
//        Flux<Object> flux;
//        try {
//            flux = switch (action) {
//                case API_TOKEN -> SecurityApiTokenUtil.getApiToken();
//                case PROJECT_SECURITY, CYBER_SECURITY -> null;
//            };
//        } catch (Exception securityServiceExc) {
//            log.error(RED + "Error from SecurityService performing action: {}" + RESET, action, securityServiceExc);
//            return Flux.empty();
//        } finally {
//            log.info(GREEN + "SecurityService action completed: {}" + RESET, action);
//        }
//        assert flux != null;
//        return flux;
//    }
//}
