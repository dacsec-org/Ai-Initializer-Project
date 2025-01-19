package org.dacss.projectinitai.messages.handlers;
/**/
import org.dacss.projectinitai.messages.utillities.*;
/**/
import org.springframework.stereotype.Component;

/**
 * <h1>{@link MessagesHandler}</h1>
 * Handler clas for the messages-mod.
 */
public class MessagesHandler {

    public String handleRequest(String message) {
        return new UserRequestUtil().processUserRequest(message);
    }

    public String handleResponse(String message) {
        return new AiResponseUtil().getAiResponse(message);
    }

    public String handleThumbsUp(String message) {
        return new ThumbsUpUtil().saveSetWithPositiveReinforcement(message);
    }

    public String handleThumbsDown(String message) {
        return new ThumbsDownUtil().saveSetWithNegativeReinforcement(message);
    }

    public String handleTrash(String message) {
        return new TrashMessageSetUtil().destroyMessageSet(message);
    }

    public String handleRetry(String message) {
        return new RetryMessageUtil().retry(message);
    }
}
