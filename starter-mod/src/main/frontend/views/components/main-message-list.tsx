import React, { useState } from 'react';
import { MessageList } from '@vaadin/react-components/MessageList.js';
import ResponseArea from './response-area';
import { MessagesIface } from 'Frontend/generated/endpoints';

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

const MainMessageListArea: React.FC = () => {
  const [messageSets, setMessageSets] = useState<MessageSet[]>([]);
  const [loading, setLoading] = useState(false);
  const [userRequest, setUserRequest] = useState('');

  const addMessageSet = (userRequest: string, aiResponse: string) => {
    const userMessage = {
      text: userRequest,
      time: new Date().toLocaleTimeString(),
      userName: 'User',
      userColorIndex: 1,
      options: renderMessageOptions(messageSets.length),
    };

    const aiMessage = {
      text: aiResponse,
      time: new Date().toLocaleTimeString(),
      userName: 'AI',
      userColorIndex: 2,
      options: renderMessageOptions(messageSets.length),
    };

    const messageSet: MessageSet = { userMessage, aiMessage };

    setMessageSets((prevMessageSets) => [...prevMessageSets, messageSet]);
  };

  const handleUserRequest = (userRequest: string) => {
    setUserRequest(userRequest);
  };

  const handleResponseReceived = (aiResponse: string) => {
    addMessageSet(userRequest, aiResponse);
  };

  const renderMessageOptions = (index: number) => (
    <div className="message-options">
      <span role="img" aria-label="thumbs up" onClick={() => handleIconClick(index)}>👍</span>
      <span role="img" aria-label="thumbs down" onClick={() => handleIconClick(index)}>👎</span>
      <span role="img" aria-label="trash" onClick={() => handleIconClick(index)}>🗑️</span>
      <span role="img" aria-label="retry" onClick={() => handleIconClick(index)}>🔄</span>
    </div>
  );

  const handleIconClick = (index: number) => {
    MessagesIface.processMessages({})
      .then(() => {
        // Handle the action if needed
      });
  };

  return (
    <>
      <MessageList items={messageSets.map(set => [set.userMessage, set.aiMessage]).flat()} />
      <ResponseArea
        request={userRequest}
        onResponseReceived={handleResponseReceived}
        onLoading={setLoading}
      />
      {loading && <div>Loading...</div>}
    </>
  );
};

export default MainMessageListArea;
