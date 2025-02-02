import React, { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { MessagesIface } from 'Frontend/generated/endpoints';
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

      MessagesIface.processMessages({ action: MessageAction.RESPONSE, data: request })
        .then((aiResponse) => {
          const value = "Processed message: " + request;
          setResponse(value);
          onResponseReceived(value);
          onLoading(false);
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
