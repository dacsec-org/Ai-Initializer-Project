import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useState, useEffect } from 'react';
import MainMessageListArea from '../components/main-message-list';
import MainMessageInput from './main-message-input';
import ResponseArea from './response-area';
import { MessageAction } from '../../enums/MessageAction';
import { ChatClient } from './ChatClient';
export const config = {
    menu: { order: 1, icon: 'line-awesome/svg/comment-alt-solid.svg', title: 'Chat' }
};
const ChatClientView = () => {
    const [request, setRequest] = useState('');
    let subscription;
    const handleMessageSent = (userRequest) => {
        setRequest(userRequest);
        subscription = ChatClient.getMessages(MessageAction.REQUEST).subscribe({
            next: (aiResponse) => {
                console.log('Received AI response:', aiResponse);
            },
            error: (error) => {
                console.error('Failed to send message', error);
            }
        });
    };
    const handleResponseReceived = (aiResponse) => {
        console.log('AI response received:', aiResponse);
    };
    useEffect(() => {
        return () => subscription?.unsubscribe();
    }, []);
    return (_jsxs("div", { children: [_jsx(MainMessageListArea, {}), _jsx(ResponseArea, { request: request, onResponseReceived: handleResponseReceived, onLoading: () => { } }), _jsx("footer", { children: _jsx(MainMessageInput, { onMessageSent: handleMessageSent, onError: () => { }, onLoading: () => { } }) })] }));
};
export default ChatClientView;
//# sourceMappingURL=chat-client.js.map