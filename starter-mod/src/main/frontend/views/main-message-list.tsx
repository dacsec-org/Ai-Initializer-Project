import React, { useState } from 'react';
import { MessageList } from '@vaadin/react-components/MessageList.js';
import MainMessageInput from './main-message-input';
import ResponseArea from './response-area';
import { MessagesService } from 'Frontend/generated/endpoints';

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

/**
 * <h1>{@link MainMessageListArea}</h1>
 * @constructor Generates the message list area for the User and AI conversation.
 */
const MainMessageListArea: React.FC = () => {
  const [messageSets, setMessageSets] = useState<MessageSet[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
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
      <span role="img" aria-label="thumbs up" onClick={() => handleIconClick('thumbs_up', index)}>👍</span>
      <span role="img" aria-label="thumbs down" onClick={() => handleIconClick('thumbs_down', index)}>👎</span>
      <span role="img" aria-label="trash" onClick={() => handleIconClick('trash', index)}>🗑️</span>
      <span role="img" aria-label="retry" onClick={() => handleIconClick('retry', index)}>🔄</span>
    </div>
  );

  const handleIconClick = (action: string, index: number) => {
    MessagesService.processMessages(action)
      .then(() => {
        // Handle the action if needed
      })
      .catch((error) => {
        setError(error.message);
      });
  };

  return (
    <>
      <MessageList items={messageSets.map(set => [set.userMessage, set.aiMessage]).flat()} />
      <ResponseArea
        userRequest={userRequest}
        onResponseReceived={handleResponseReceived}
        onError={setError}
        onLoading={setLoading}
      />
      {loading && <div>Loading...</div>}
      {error && <div className="error">{error}</div>}
    </>
  );
};

export default MainMessageListArea;
