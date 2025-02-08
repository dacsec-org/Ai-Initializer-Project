import React, { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { ChatClient } from './ChatClient';
import { MessageAction } from '../../enums/MessageAction';
import { Subscription } from 'rxjs';

interface ResponseAreaProps {
  request: string;
  onResponseReceived: (aiResponse: string) => void;
  onLoading: (loading: boolean) => void;
}

const ResponseArea: React.FC<ResponseAreaProps> = ({ request, onResponseReceived, onLoading }) => {
  const [response, setResponse] = useState('');
  let subscription: Subscription;

  useEffect(() => {
    if (request) {
      onLoading(true);
      subscription = ChatClient.getMessages(MessageAction.RESPONSE).subscribe({
        next: (aiResponse) => {
          setResponse(aiResponse);
          onResponseReceived(aiResponse);
          onLoading(false);
        },
        error: () => {
          onLoading(false);
        }
      });
    }
    return () => subscription?.unsubscribe();
  }, [request]);

  return (
    <TextArea
      label="AI Response"
      value={response}
      readonly
      style={{ width: '100%' }}
    />
  );
};

export default ResponseArea;
