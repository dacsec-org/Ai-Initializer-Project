import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { MessageList } from '@vaadin/react-components/MessageList.js';
import ResponseArea from '../chat/response-area';
// @ts-ignore
import { MessagesIface } from 'Frontend/generated/endpoints';
const MainMessageListArea = () => {
    const [messageSets, setMessageSets] = useState([]);
    const [loading, setLoading] = useState(false);
    const [userRequest, setUserRequest] = useState('');
    const addMessageSet = (userRequest, aiResponse) => {
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
        const messageSet = { userMessage, aiMessage };
        setMessageSets((prevMessageSets) => [...prevMessageSets, messageSet]);
    };
    const handleUserRequest = (userRequest) => {
        setUserRequest(userRequest);
    };
    const handleResponseReceived = (aiResponse) => {
        addMessageSet(userRequest, aiResponse);
    };
    const renderMessageOptions = (index) => (_jsxs("div", { className: "message-options", children: [_jsx("span", { role: "img", "aria-label": "thumbs up", onClick: () => handleIconClick(index), children: "\uD83D\uDC4D" }), _jsx("span", { role: "img", "aria-label": "thumbs down", onClick: () => handleIconClick(index), children: "\uD83D\uDC4E" }), _jsx("span", { role: "img", "aria-label": "trash", onClick: () => handleIconClick(index), children: "\uD83D\uDDD1\uFE0F" }), _jsx("span", { role: "img", "aria-label": "retry", onClick: () => handleIconClick(index), children: "\uD83D\uDD04" })] }));
    const handleIconClick = (index) => {
        MessagesIface.processMessages({})
            .then(() => {
            // Handle the action if needed
        });
    };
    return (_jsxs(_Fragment, { children: [_jsx(MessageList, { items: messageSets.map(set => [set.userMessage, set.aiMessage]).flat() }), _jsx(ResponseArea, { request: userRequest, onResponseReceived: handleResponseReceived, onLoading: setLoading }), loading && _jsx("div", { children: "Loading..." })] }));
};
export default MainMessageListArea;
//# sourceMappingURL=main-message-list.js.map