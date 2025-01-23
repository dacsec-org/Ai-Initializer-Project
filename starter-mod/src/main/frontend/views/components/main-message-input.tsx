import React, { Component } from 'react';
import { MessageInput, type MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';

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
  handleSubmit(event: MessageInputSubmitEvent) {
    event.preventDefault();
    //fixme: asap! handle the submit action here
  }

  render() {
    return <MessageInput onSubmit={this.handleSubmit} />;
  }
}

export default MainMessageInput;
