import React, { useEffect, useState } from 'react';
import { MessageAction } from '../enums/MessageAction';
import { MessageBridge } from '../bridges/message-bridge';
import { Subscription } from 'rxjs';
import InputArea from '../components/input-area';

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

function MessageList(props: {
  items: FlatArray<{
    text: string;
    time: string;
    userName: string;
    userColorIndex: number;
    options: React.ReactNode
  }[][], 1>[]
}) {
  return null;
}

const ChatClientView: React.FC = () => {
  const [request, setRequest] = useState<string>('');
  const [messageSets, setMessageSets] = useState<MessageSet[]>([]);
  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState('');
  let subscription: Subscription;

  const handleMessageSent = (userRequest: string) => {
    setRequest(userRequest);
    subscription = MessageBridge(MessageAction.REQUEST).subscribe({
      next: (aiResponse) => {
        console.log('Received AI response:', aiResponse);
        handleResponseReceived(aiResponse);
      },
      error: (error) => {
        console.error('Failed to send message', error);
      }
    });
  };

  const handleResponseReceived = (aiResponse: string) => {
    const userMessage = {
      text: request,
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

  const renderMessageOptions = (index: number) => (
    <div className="message-options">
      <span role="img" aria-label="thumbs up" onClick={() => handleIconClick(index, MessageAction.THUMBS_UP)}>👍</span>
      <span role="img" aria-label="thumbs down" onClick={() => handleIconClick(index, MessageAction.THUMBS_DOWN)}>👎</span>
      <span role="img" aria-label="trash" onClick={() => handleIconClick(index, MessageAction.TRASH)}>🗑️</span>
      <span role="img" aria-label="retry" onClick={() => handleIconClick(index, MessageAction.RETRY)}>🔄</span>
    </div>
  );

  const handleIconClick = (index: number, action: MessageAction) => {
    // Handle the action if needed
  };

  useEffect(() => {
    if (request) {
      setLoading(true);
      subscription = MessageBridge(MessageAction.RESPONSE).subscribe({
        next: (aiResponse) => {
          setResponse(aiResponse);
          handleResponseReceived(aiResponse);
          setLoading(false);
        },
        error: () => {
          setLoading(false);
        }
      });
    }
    return () => subscription?.unsubscribe();
  }, [request]);

  return (
    <div>
      <MessageList items={messageSets.map(set => [set.userMessage, set.aiMessage]).flat()} />
      <InputArea
        label="AI Response"
        value={response}
        readonly
        style={{ width: '100%' }}
      />
      {loading && <div>Loading...</div>}
      <footer>
        <input
          type="text"
          placeholder="Type your message..."
          onKeyDown={(e) => {
            if (e.key === 'Enter') {
              handleMessageSent((e.target as HTMLInputElement).value);
              (e.target as HTMLInputElement).value = '';
            }
          }}
        />
      </footer>
    </div>
  );
};

export default ChatClientView;
