package org.dacss.projectinitai.views.chat;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.dacss.projectinitai.advisers.processors.StringProcessingAdviserIface;
import org.dacss.projectinitai.components.ContextualAdviserComp;
import org.dacss.projectinitai.components.ProcessorFactoryComp;
import org.dacss.projectinitai.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Chat")
@Route("chat")
@Menu(order = 8, icon = LineAwesomeIconUrl.ROCKETCHAT)
public class ChatView extends Composite<VerticalLayout> {

    private final MessageList messageList;
    private final List<MessageListItem> messages;
    private final ProcessorFactoryComp processorFactory;
    private final ContextualAdviserComp<String> contextualAdviser;

    @Autowired
    public ChatView(ProcessorFactoryComp processorFactory, ContextualAdviserComp<String> contextualAdviser) {
        this.processorFactory = processorFactory;
        this.contextualAdviser = contextualAdviser;
        this.messageList = new MessageList();
        this.messages = new ArrayList<>();

        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");

        AvatarGroup avatarGroup = new AvatarGroup();
        setAvatarGroupSampleData(avatarGroup);

        MessageInput messageInput = new MessageInput();
        messageInput.addSubmitListener(event -> {
            String userRequest = event.getValue();
            String aiResponse = sendMessage(userRequest);
            addUserMessage(userRequest);
            addAiMessage(aiResponse);
        });

        layout.add(avatarGroup, messageList, messageInput);
    }

    private void setAvatarGroupSampleData(AvatarGroup avatarGroup) {
        avatarGroup.add(new AvatarGroup.AvatarGroupItem("A B"));
        avatarGroup.add(new AvatarGroup.AvatarGroupItem("C D"));
        avatarGroup.add(new AvatarGroup.AvatarGroupItem("E F"));
    }

    private void addAiMessage(String aiResponse) {
        MessageListItem aiMessage = new MessageListItem(
                aiResponse,
                LocalDateTime.now().toInstant(ZoneOffset.UTC),
                "AI"
        );
        aiMessage.setUserColorIndex(2);
        messages.add(aiMessage);
        messageList.setItems(messages);
    }

    private void addUserMessage(String userRequest) {
        MessageListItem userMessage = new MessageListItem(
                userRequest,
                LocalDateTime.now().toInstant(ZoneOffset.UTC),
                "User"
        );
        userMessage.setUserColorIndex(1);
        messages.add(userMessage);
        messageList.setItems(messages);
    }

    private String sendMessage(String message) {
        StringProcessingAdviserIface preProcessingAdviser =
                processorFactory.getStringProcessor(MessageType.TEXT);
        String preProcessedMessage =
                preProcessingAdviser.processString(message);
        String postProcessedResponse =
                preProcessingAdviser.processString(preProcessedMessage);
        contextualAdviser.updateContext(preProcessedMessage, postProcessedResponse);
        return postProcessedResponse;
    }

    public String getContext() {
        return contextualAdviser.getContext();
    }
}
