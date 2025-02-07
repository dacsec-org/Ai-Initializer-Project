//package org.dacss.projectinitai.security;
//
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//import java.io.IOException;
//
///**
// * <h1>{@link SecurityModuleTest}</h1>
// * Test suite for SecurityModule.
// * Methods under test:
// * <ul>
// *     <li>{@link SecurityApiTokenUtil#getApiToken()}</li>
// *     <li>{@link SecurityIface#processSecureAction(SecurityActions)}</li>
// *     <li>{@link SecurityIface#secure(SecurityActions)}</li>
// * </ul>
// */
//public class SecurityModuleTest {
//
//    @Test
//    public void testGetApiToken() {
//        Flux<Object> flux = SecurityApiTokenUtil.getApiToken();
//
//        StepVerifier.create(flux)
//                .expectNextMatches(token -> token instanceof String && !((String) token).isEmpty())
//                .expectComplete()
//                .verify();
//        System.out.println("\033[32m Test 'getApiToken()' passed!\033[0m");
//    }
//
//    @Test
//    public void testProcessSecureAction() {
//        Flux<Object> flux = SecurityIface.processSecureAction(SecurityActions.API_TOKEN);
//
//        StepVerifier.create(flux)
//                .expectNextMatches(token -> token instanceof String && !((String) token).isEmpty())
//                .expectComplete()
//                .verify();
//        System.out.println("\033[32m Test 'processSecureAction()' passed!\033[0m");
//    }
//
//    @Test
//    public void testSecure() throws IOException {
//        SecurityIface securityIface = action -> SecurityApiTokenUtil.getApiToken();
//
//        Flux<Object> flux = securityIface.secure(SecurityActions.API_TOKEN);
//
//        StepVerifier.create(flux)
//                .expectNextMatches(token -> token instanceof String && !((String) token).isEmpty())
//                .expectComplete()
//                .verify();
//        System.out.println("\033[32m Test 'secure()' passed!\033[0m");
//    }
//}
