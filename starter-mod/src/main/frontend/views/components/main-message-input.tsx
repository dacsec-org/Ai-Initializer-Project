import React, { useState } from 'react';
import { MessageInput, type MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';
import { MessagesService } from 'Frontend/generated/endpoints';

interface MainMessageInputProps {
  onMessageSent: (userRequest: string) => void;
  onError: (error: string) => void;
  onLoading: (loading: boolean) => void;
}

/**
 * <h1>{@link MainMessageInput}</h1>
 * @param onMessageSent Callback to handle the user request
 * @param onError Callback to handle errors
 * @param onLoading Callback to handle loading state
 * @constructor Generates the main message input area for the user, and sends the user request to the AI via the {@link MessagesService}.
 */
const MainMessageInput: React.FC<MainMessageInputProps> = ({ onMessageSent, onError, onLoading }) => {
  const [message, setMessage] = useState('');

  const handleSubmit = (event: MessageInputSubmitEvent) => {
    event.preventDefault();
    const userRequest = event.detail.value;
    setMessage(userRequest);
    onLoading(true);
    onError(null);

    MessagesService.processMessages('request')
      .then(() => {
        onMessageSent(userRequest);
        onLoading(false);
      })
      .catch((error) => {
        onError(error.message);
        onLoading(false);
      });
  };

  return <MessageInput onSubmit={handleSubmit} />;
};

export default MainMessageInput;
