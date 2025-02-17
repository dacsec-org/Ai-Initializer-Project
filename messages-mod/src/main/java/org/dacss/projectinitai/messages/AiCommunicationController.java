package org.dacss.projectinitai.messages;

import org.dacss.projectinitai.messages.AiResponse;
import org.dacss.projectinitai.messages.UserRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class AICommunicationController {

    /**
     * Handles user requests and streams AI responses back using RSocket.
     *
     * @MessageMapping("user.request")
     * Request/Stream interaction: Sends a user request and streams responses.
     */
    @MessageMapping("user.request")
    public Flux<Object> handleUserRequest(Flux<Object> userRequests) {
        System.out.println("Received a user request stream.");

        // Send user requests to the UserRequest handler and process the response as Flux<Object>
        return UserRequest.sendUserRequestToLLM(userRequests)
                .flatMap(AiResponse::receiveAiResponseFromLLM);
    }

    /**
     * Handles a chat session with bidirectional streaming:
     * Channels allow continuously sending requests and receiving responses.
     *
     * @MessageMapping("chat")
     */
    @MessageMapping("chat")
    public Flux<Object> chat(Flux<Object> userMessages) {
        System.out.println("Starting a chat session.");

        // Send user messages and receive a stream of AI responses back.
        return UserRequest.sendUserRequestToLLM(userMessages)
                .flatMap(AiResponse::receiveAiResponseFromLLM);
    }
}
