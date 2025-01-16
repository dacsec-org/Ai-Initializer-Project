import React, { Component } from 'react';
import { Icon, MessageList } from '@vaadin/react-components';
import MainLayout from './@layout';
import MainMessageInput from './main-message-input';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone Model',
};

/**
 * {@link ChatClientProps}
 * <p>
 *   This is the props interface for the chat client component
 * </p>
 */
interface ChatClientProps {}

/**
 * {@link ChatClientState}
 * <p>
 *   This is the state interface for the chat client component
 * </p>
 */
interface ChatClientState {
  messages: {
    text: string;
    time: string;
    userName: string;
    userColorIndex: number;
    options: React.ReactNode;
  }[];
}

/**
 * {@link ChatClient}
 * <p>
 *   This is the main chat client component that renders a chat client
 * </p>
 */
class ChatClient extends Component<ChatClientProps, ChatClientState> {
  constructor(props: ChatClientProps) {
    super(props);
    this.state = {
      messages: [],
    };
  }

  /**
   * {@link #addAiMessage}
   * <p>
   *   This method adds an AI message to the chat client
   * </p>
   * @param aiResponse
   */
  addAiMessage(aiResponse: string) {
    const aiMessage = {
      text: aiResponse,
      time: new Date().toISOString(),
      userName: 'AI',
      userColorIndex: 2,
      options: this.renderMessageOptions(),
    };
    this.setState((prevState) => ({
      messages: [...prevState.messages, aiMessage],
    }));
  }

  /**
   * {@link #addUserMessage}
   * <p>
   *   This method adds a user message to the chat client
   * </p>
   * @param userRequest
   */
  addUserMessage(userRequest: string) {
    const userMessage = {
      text: userRequest,
      time: new Date().toISOString(),
      userName: 'User',
      userColorIndex: 1,
      options: this.renderMessageOptions(),
    };
    this.setState((prevState) => ({
      messages: [...prevState.messages, userMessage],
    }));
  }

  /**
   * {@link #renderMessageOptions}
   * <p>
   *   This method renders message options
   * </p>
   */
  renderMessageOptions() {
    return (
      <div className="message-options">
        <Icon icon="vaadin:thumbs-up" />
        <Icon icon="vaadin:thumbs-down" />
        <Icon icon="vaadin:trash" />
      </div>
    );
  }

  /**
   * {@link #handleSubmit}
   * <p>
   *   This method handles the submit event
   * </p>
   * @param event
   */
  handleSubmit = (event: any) => {
    const userRequest = event.detail.value;
    const aiResponse = this.sendMessage(userRequest);
    this.addUserMessage(userRequest);
    this.addAiMessage(aiResponse);
  };

  /**
   * {@link #sendMessage}
   * <p>
   *   This method sends a message
   * </p>
   * @param message
   */
  sendMessage(message: string) {
    // Placeholder for message processing logic
    return `Processed: ${message}`;
  }

  render() {
    return (
      <MainLayout>
        <MessageList items={this.state.messages} />
        <MainMessageInput onSubmit={this.handleSubmit} />
      </MainLayout>
    );
  }
}

export default ChatClient;
