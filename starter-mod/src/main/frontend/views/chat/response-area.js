import { jsx as _jsx } from "react/jsx-runtime";
import { useState, useEffect } from 'react';
import { TextArea } from '@vaadin/react-components/TextArea.js';
import { ChatClient } from './ChatClient';
import { MessageAction } from '../../enums/MessageAction';
const ResponseArea = ({ request, onResponseReceived, onLoading }) => {
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
    return (_jsx(TextArea, { label: "AI Response", value: response, readonly: true, style: { width: '100%' } }));
};
export default ResponseArea;
//# sourceMappingURL=response-area.js.map