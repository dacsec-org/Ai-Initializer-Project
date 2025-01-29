import React, { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { MessagesService } from 'Frontend/generated/endpoints';

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

      MessagesService.processMessages('RESPONSE')
        .then(() => {
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
