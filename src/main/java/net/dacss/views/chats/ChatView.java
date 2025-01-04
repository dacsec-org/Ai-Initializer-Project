package net.dacss.views.chats;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.dacss.views.MainView;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.Objects;
//    @RestController
    @PageTitle("Chat")
    @Route(value = "chat", layout = MainView.class)
    @Menu(order = 2, icon = LineAwesomeIconUrl.LIST_SOLID)
    public class ChatView extends VerticalLayout {
        private VerticalLayout      roundedContainer;
        private TextField           promptInput;
        private TextArea            aiResponse;

        public ChatView() {
            addClassName("chat-view");
            setSizeFull();
            add(addChatContainer(), addFooterPrompt());
        }

        private VerticalLayout addChatContainer() {
            VerticalLayout chatContainer = new VerticalLayout();
            chatContainer.addClassName("chat-containers");
            chatContainer.add(createRoundedContainer());
            chatContainer.setSizeFull();
            return chatContainer;
        }

        private HorizontalLayout addFooterPrompt() {
            HorizontalLayout footerPrompt = new HorizontalLayout();
            footerPrompt.addClassName("footer-bar");
            footerPrompt.setWidthFull();
            Button sendButton = new Button("Send");
            sendButton.addClickListener(e -> sendPrompt());
            footerPrompt.add(sendButton, promptInputArea());
            return footerPrompt;
        }

        private VerticalLayout createRoundedContainer() {
            roundedContainer = new VerticalLayout();
            roundedContainer.addClassName("rounded-containers");
            H3 header = new H3("Chat");
            roundedContainer.add(header);
            return roundedContainer;
        }

        private String sendPrompt() {
            String promptText = promptInput.getValue();
            if (Objects.nonNull(promptText) && ! promptText.isEmpty()) {
                addUserPrompt(promptText);
                promptInput.clear();
            } else
                Notification.show("Please enter a prompt to send");
            return promptText;
        }

        private TextField promptInputArea() {
            promptInput = new TextField();
            promptInput.addClassName("prompt-input");
            promptInput.setWidthFull();
            promptInput.setPlaceholder("Enter your prompt here");
            return promptInput;
        }

        private void addUserPrompt(String promptText) {
            Div promptDiv = new Div();
            promptDiv.setText("you: " + promptText);
            promptDiv.addClassName("prompt-message");
            roundedContainer.add(promptDiv);

        }

        private Div addAiResponse() {
            String responseText = "";
            Div    responseDiv  = new Div();
            responseDiv.setText("ai: " + responseText);
            responseDiv.addClassName("response-message");
            roundedContainer.add(responseDiv);

            return responseDiv;
        }
    }
