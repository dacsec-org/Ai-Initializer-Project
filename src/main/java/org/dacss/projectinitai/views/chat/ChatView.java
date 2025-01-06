package org.dacss.projectinitai.views.chat;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.avatar.AvatarGroup.AvatarGroupItem;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Chat")
@Route("chat")
@Menu(order = 8, icon = LineAwesomeIconUrl.ROCKETCHAT)
public class ChatView extends Composite<VerticalLayout> {

    public ChatView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        AvatarGroup avatarGroup = new AvatarGroup();
        MessageList messageList = new MessageList();
        MessageInput messageInput = new MessageInput();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutColumn2.addClassName(Gap.LARGE);
        layoutColumn2.addClassName(Padding.LARGE);
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setHeight("100%");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.END, avatarGroup);
        avatarGroup.setWidth("min-content");
        avatarGroup.setMinWidth("150px");
        setAvatarGroupSampleData(avatarGroup);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.START, messageList);
        messageList.setWidth("100%");
        messageList.getStyle().set("flex-grow", "1");
        setMessageListSampleData(messageList);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, messageInput);
        messageInput.setWidth("100%");
        getContent().add(layoutRow);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(avatarGroup);
        layoutColumn2.add(messageList);
        layoutColumn2.add(messageInput);
    }

    private void setAvatarGroupSampleData(AvatarGroup avatarGroup) {
        avatarGroup.add(new AvatarGroupItem("A B"));
        avatarGroup.add(new AvatarGroupItem("C D"));
        avatarGroup.add(new AvatarGroupItem("E F"));
    }

    private void setMessageListSampleData(MessageList messageList) {
        MessageListItem message1 = new MessageListItem("Nature does not hurry, yet everything gets accomplished.",
                LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC), "Matt Mambo");
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem(
                "Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.",
                LocalDateTime.now().minusMinutes(55).toInstant(ZoneOffset.UTC), "Linsey Listy");
        message2.setUserColorIndex(2);
        messageList.setItems(message1, message2);
    }
}
