import React, { Component } from 'react';
import { Icon, MessageList } from '@vaadin/react-components';
import MainLayout from './@layout';
import MainMessageInput from './main-message-input';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { MessagesService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/rocket-chat' }, title: 'Chat Client',
};

interface ChatClientProps {}

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
 * {@link ChatClient}
 */
class ChatClient extends Component<ChatClientProps, ChatClientState> {
  private messageEndRef: React.RefObject<HTMLDivElement>;

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

  handleSubmit = async (event: any) => {
    const userRequest = event.detail.value;
    this.setState({ loading: true, error: null });
    try {
      const aiResponse = await MessagesService.getAiResponse(userRequest);
      this.addMessageSet(userRequest, aiResponse);
    } catch (error) {
      this.setState({ loading: false, error: 'Failed to get AI response' });
    }
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

export default ChatClient;
