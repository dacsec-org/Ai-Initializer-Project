import React, { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { MessagesService } from 'Frontend/generated/endpoints';

interface ResponseAreaProps {
  userRequest: string;
  onResponseReceived: (aiResponse: string) => void;
  onError: (error: string) => void;
  onLoading: (loading: boolean) => void;
}

/**
 * <h1>{@link ResponseArea}</h1>
 * @param userRequest The user request
 * @param onResponseReceived Callback function to handle the AI response
 * @param onError Callback function to handle errors
 * @param onLoading Callback function to handle loading state
 * @constructor Generates a response area for the AI LLM.
 */
const ResponseArea: React.FC<ResponseAreaProps> = ({ userRequest, onResponseReceived, onError, onLoading }) => {
  const [response, setResponse] = useState('');

  useEffect(() => {
    if (userRequest) {
      onLoading(true);
      onError(null);

      MessagesService.processMessages('response')
        .then((response) => {
          response.subscribe({
            next: (value: string) => {
              setResponse(value);
              onResponseReceived(value);
              onLoading(false);
            },
            error: (err: any) => {
              onError(err.message);
              onLoading(false);
            },
            complete: () => onLoading(false),
          });
        })
        .catch((error) => {
          onError(error.message);
          onLoading(false);
        });
    }
  }, [userRequest]);

  return (
    <TextArea
      label="AI Response"
      value={response}
      readOnly
      style={{ width: '100%' }}
    />
  );
};

export default ResponseArea;
