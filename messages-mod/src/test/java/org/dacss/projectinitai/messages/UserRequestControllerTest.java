//package org.dacss.projectinitai.messages;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.messaging.rsocket.RSocketRequester;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
///**
// * <h1>{@link UserRequestControllerTest}</h1>
// * Run this while the application is running to test the UserRequestController.
// */
//@SpringBootTest
//public class UserRequestControllerTest {
//
//    private RSocketRequester.Builder requesterBuilder;
//
//    /**
//     * <h3>{@link #testSendUserRequestToLLM()}</h3>
//     */
//    @Test
//    public void testSendUserRequestToLLM() {
//        RSocketRequester requester = requesterBuilder.tcp("localhost", 30320);
//
//        Flux<Object> response = requester.route("user.request")
//                .data(Flux.just(new Object()))
//                .retrieveFlux(Object.class);
//
//        StepVerifier.create(response)
//                .expectNextCount(1)
//                .verifyComplete();
//    }
//}
