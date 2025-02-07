import React, { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { ChatClient } from './ChatClient';
import { MessageAction } from '../../enums/MessageAction';

interface ResponseAreaProps {
  request: string;
  onResponseReceived: (aiResponse: string) => void;
  onLoading: (loading: boolean) => void;
}

const ResponseArea: React.FC<ResponseAreaProps> = ({ request, onResponseReceived, onLoading }) => {
  const [response, setResponse] = useState('');

  useEffect(() => {
    if (request) {
      onLoading(true);

      ChatClient.getMessages(MessageAction.RESPONSE)
        .then((aiResponse) => {
          setResponse(aiResponse);
          onResponseReceived(aiResponse);
        })
        .catch(() => {
          onLoading(false);
        });
    }
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
