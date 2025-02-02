import React, { useState } from 'react';
import MainMessageListArea from './components/main-message-list';
import MainMessageInput from './components/main-message-input';
import ResponseArea from './components/response-area';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { MessageAction } from '../enums/MessageAction';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/comment-alt-solid.svg' }, title: 'Chat',
};

const ChatClientView: React.FC = () => {
  const [request, setRequest] = useState<string>('');
  const [response, setResponse] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>('');

  const handleMessageSent = (userRequest: string) => {
    setRequest(userRequest);
  };

  const handleResponseReceived = (aiResponse: string) => {
    setResponse(aiResponse);
  };

  return (
    <div>
      <MainMessageListArea />
      <ResponseArea
        request={request}
        onResponseReceived={handleResponseReceived}
        onLoading={setLoading}
      />
      <footer>
        <MainMessageInput
          onMessageSent={handleMessageSent}
          onError={setError}
          onLoading={setLoading}
        />
      </footer>
    </div>
  );
};

export default ChatClientView;
