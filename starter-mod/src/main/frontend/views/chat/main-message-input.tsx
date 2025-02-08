import React, { useState } from 'react';
import { MessageInput, type MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';
import { ChatClient } from './ChatClient';
import { MessageAction } from '../../enums/MessageAction';
import { lastValueFrom } from 'rxjs';

interface MainMessageInputProps {
  onMessageSent: (userRequest: string) => void;
  onError: (error: string) => void;
  onLoading: (loading: boolean) => void;
}

const MainMessageInput: React.FC<MainMessageInputProps> = ({ onMessageSent, onError, onLoading }) => {
  const [userRequest, setUserRequest] = useState('');

  const handleSubmit = async (event: MessageInputSubmitEvent) => {
    event.preventDefault();
    const request = event.detail.value;
    setUserRequest(request);
    onLoading(true);
    onError('');

    try {
      await lastValueFrom(ChatClient.getMessages(MessageAction.REQUEST));
      onMessageSent(request);
    } catch {
      onError('Failed to send message');
    } finally {
      onLoading(false);
    }
  };

  return <MessageInput value={userRequest} onSubmit={handleSubmit} />;
};

export default MainMessageInput;
