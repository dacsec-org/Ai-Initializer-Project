import React, { useState } from 'react';
import { MessageInput, type MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';
import { MessagesIface } from 'Frontend/generated/endpoints';
import { MessageAction } from '../../enums/MessageAction';

interface MainMessageInputProps {
  onMessageSent: (userRequest: string) => void;
  onError: (error: string) => void;
  onLoading: (loading: boolean) => void;
}

const MainMessageInput: React.FC<MainMessageInputProps> = ({ onMessageSent, onError, onLoading }) => {
  const [message, setMessage] = useState('');

  const handleSubmit = (event: MessageInputSubmitEvent) => {
    event.preventDefault();
    const userRequest = event.detail.value;
    setMessage(userRequest);
    onLoading(true);
    onError('');

    MessagesIface.processMessages({ action: MessageAction.REQUEST, data: userRequest })
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
