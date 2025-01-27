import React, { Component } from 'react';
import { MessageList, Notification } from '@vaadin/react-components';
import MainLayout from './@layout';
import MainMessageInput from './components/main-message-input';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { MessagesService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/rocket-chat' }, title: 'Chat',
};

interface ChatClientProps {}

/**
 * <h3>{@link MessageSet}</h3>
 * Represents a set of messages exchanged between the user and the AI.
 */
interface MessageSet {
  userMessage: {
    text: string;
    time: string;
    userName: string;
    userColorIndex: number;
    options: React.ReactNode;
  };
  aiMessage: {
    text: string;
    time: string;
    userName: string;
    userColorIndex: number;
    options: React.ReactNode;
  };
}

interface ChatClientState {
  messageSets: MessageSet[];
  loading: boolean;
  error: string | null;
}

/**
 * <h1>{@link ChatClientView}</h1>
 */
class ChatClientView extends Component<ChatClientProps, ChatClientState> {
  private readonly messageEndRef: React.RefObject<HTMLDivElement>;

  constructor(props: ChatClientProps) {
    super(props);
    this.state = {
      messageSets: [],
      loading: false,
      error: null,
    };
    this.messageEndRef = React.createRef();
  }

  addMessageSet(userRequest: string, aiResponse: string) {
    const userMessage = {
      text: userRequest,
      time: new Date().toLocaleTimeString(),
      userName: 'User',
      userColorIndex: 1,
      options: this.renderMessageOptions(),
    };

    const aiMessage = {
      text: aiResponse,
      time: new Date().toLocaleTimeString(),
      userName: 'AI',
      userColorIndex: 2,
      options: this.renderMessageOptions(),
    };

    const messageSet: MessageSet = { userMessage, aiMessage };

    this.setState((prevState) => ({
      messageSets: [...prevState.messageSets, messageSet],
      loading: false,
      error: null,
    }), this.scrollToBottom);
  }

  renderMessageOptions() {
    return (
      <div className="message-options">
        <span role="img" aria-label="thumbs-up" onClick={() => alert('Thumbs up clicked!')}>👍</span>
        <span role="img" aria-label="thumbs-down" onClick={() => alert('Thumbs down clicked!')}>👎</span>
        <span role="img" aria-label="trash" onClick={() => alert('Trash clicked!')}>🗑️</span>
        <span role="img" aria-label="retry" onClick={() => alert('Retry clicked!')}>🔄</span>
      </div>
    );
  }

  handleRequest = async (message: string) => {
    const response = await MessagesService.processMessages('request', '');
    Notification.show('Request processed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleResponse = async (message: string) => {
    const response = await MessagesService.processMessages('response', '');
    Notification.show('Response processed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleThumbsUp = async (message: string) => {
    const response = await MessagesService.processMessages('thumbs_up', '');
    Notification.show('Thumbs up processed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleThumbsDown = async (message: string) => {
    const response = await MessagesService.processMessages('thumbs_down', '');
    Notification.show('Thumbs down processed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleTrash = async (message: string) => {
    const response = await MessagesService.processMessages('trash', '');
    Notification.show('Message trashed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleRetry = async (message: string) => {
    const response = await MessagesService.processMessages('retry', '');
    Notification.show('Retry processed' + message + response);
    this.setState({ loading: true, error: null });
  };

  handleSubmit = async (event: any) => {
    const userRequest = event.detail.value;
    this.setState({ loading: true, error: null });
    const aiResponse = event.detail.value;
    this.addMessageSet(userRequest, aiResponse);
  };

  scrollToBottom = () => {
    if (this.messageEndRef.current) {
      this.messageEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  render() {
    return (
      <MainLayout>
        {this.state.messageSets.map((set, index) => (
          <div key={index} className="message-set">
            <MessageList items={[set.userMessage]} />
            <MessageList items={[set.aiMessage]} />
            {set.userMessage.options}
          </div>
        ))}
        {this.state.loading && <div>Loading...</div>}
        {this.state.error && <div className="error">{this.state.error}</div>}
        <MainMessageInput onSubmit={this.handleSubmit} />
        <div ref={this.messageEndRef} />
      </MainLayout>
    );
  }
}

export default ChatClientView;
