import React, { useState, useEffect } from 'react';
import MainMessageListArea from '../components/main-message-list';
import MainMessageInput from './main-message-input';
import ResponseArea from './response-area';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { MessageAction } from '../../enums/MessageAction';
import { ChatClient } from './ChatClient';
import { Subscription } from 'rxjs';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/comment-alt-solid.svg', title: 'Chat' }
};

const ChatClientView: React.FC = () => {
  const [request, setRequest] = useState<string>('');
  let subscription: Subscription;

  const handleMessageSent = (userRequest: string) => {
    setRequest(userRequest);
    subscription = ChatClient.getMessages(MessageAction.REQUEST).subscribe({
      next: (aiResponse) => {
        console.log('Received AI response:', aiResponse);
      },
      error: (error) => {
        console.error('Failed to send message', error);
      }
    });
  };

  const handleResponseReceived = (aiResponse: string) => {
    console.log('AI response received:', aiResponse);
  };

  useEffect(() => {
    return () => subscription?.unsubscribe();
  }, []);

  return (
    <div>
      <MainMessageListArea />
      <ResponseArea
        request={request}
        onResponseReceived={handleResponseReceived}
        onLoading={() => {}}
      />
      <footer>
        <MainMessageInput
          onMessageSent={handleMessageSent}
          onError={() => {}}
          onLoading={() => {}}
        />
      </footer>
    </div>
  );
};

export default ChatClientView;
