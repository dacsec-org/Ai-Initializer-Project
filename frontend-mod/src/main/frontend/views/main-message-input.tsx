import React, { Component } from 'react';
import { MessageInput, type MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';
import { Notification } from '@vaadin/react-components/Notification.js';

interface MainMessageInputProps {
  onSubmit: (event: MessageInputSubmitEvent) => void;
}

/**
 * {@link MainMessageInput}
 * <p>
 *   This is the main message input component that renders a message input field
 *   to be used in chats.
 * </p>
 */
class MainMessageInput extends Component<MainMessageInputProps> {
  render() {
    return <MessageInput onSubmit={this.props.onSubmit} />;
  }
}

export default MainMessageInput;
