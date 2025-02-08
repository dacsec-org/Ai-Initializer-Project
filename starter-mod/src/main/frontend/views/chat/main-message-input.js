import { jsx as _jsx } from "react/jsx-runtime";
import { useState } from 'react';
import { MessageInput } from '@vaadin/react-components/MessageInput.js';
import { ChatClient } from './ChatClient';
import { MessageAction } from '../../enums/MessageAction';
import { lastValueFrom } from 'rxjs';
const MainMessageInput = ({ onMessageSent, onError, onLoading }) => {
    const [userRequest, setUserRequest] = useState('');
    const handleSubmit = async (event) => {
        event.preventDefault();
        const request = event.detail.value;
        setUserRequest(request);
        onLoading(true);
        onError('');
        try {
            await lastValueFrom(ChatClient.getMessages(MessageAction.REQUEST));
            onMessageSent(request);
        }
        catch {
            onError('Failed to send message');
        }
        finally {
            onLoading(false);
        }
    };
    return _jsx(MessageInput, { value: userRequest, onSubmit: handleSubmit });
};
export default MainMessageInput;
//# sourceMappingURL=main-message-input.js.map