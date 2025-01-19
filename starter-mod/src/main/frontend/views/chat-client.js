import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Icon, MessageList } from '@vaadin/react-components';
import MainLayout from './@layout';
import MainMessageInput from './main-message-input';
export const config = {
    menu: { order: 1, icon: 'line-awesome/svg/rocket-chat' },
    title: 'Chat Client',
};
/**
 * {@link ChatClient}
 * <p>
 *   This is the main chat client component that renders a chat client
 * </p>
 */
class ChatClient extends Component {
    constructor(props) {
        super(props);
        /**
         * {@link #handleSubmit}
         * <p>
         *   This method handles the submit event
         * </p>
         * @param event
         */
        this.handleSubmit = (event) => {
            const userRequest = event.detail.value;
            const aiResponse = this.sendMessage(userRequest);
            this.addUserMessage(userRequest);
            this.addAiMessage(aiResponse);
        };
        this.state = {
            messages: [],
        };
    }
    /**
     * {@link #addAiMessage}
     * <p>
     *   This method adds an AI message to the chat client
     * </p>
     * @param aiResponse
     */
    addAiMessage(aiResponse) {
        const aiMessage = {
            text: aiResponse,
            time: new Date().toISOString(),
            userName: 'AI',
            userColorIndex: 2,
            options: this.renderMessageOptions(),
        };
        this.setState((prevState) => ({
            messages: [...prevState.messages, aiMessage],
        }));
    }
    /**
     * {@link #addUserMessage}
     * <p>
     *   This method adds a user message to the chat client
     * </p>
     * @param userRequest
     */
    addUserMessage(userRequest) {
        const userMessage = {
            text: userRequest,
            time: new Date().toISOString(),
            userName: 'User',
            userColorIndex: 1,
            options: this.renderMessageOptions(),
        };
        this.setState((prevState) => ({
            messages: [...prevState.messages, userMessage],
        }));
    }
    /**
     * {@link #renderMessageOptions}
     * <p>
     *   This method renders message options
     * </p>
     */
    renderMessageOptions() {
        return (_jsxs("div", { className: "message-options", children: [_jsx(Icon, { icon: "vaadin:thumbs-up" }), _jsx(Icon, { icon: "vaadin:thumbs-down" }), _jsx(Icon, { icon: "vaadin:trash" })] }));
    }
    /**
     * {@link #sendMessage}
     * <p>
     *   This method sends a message
     * </p>
     * @param message
     */
    sendMessage(message) {
        // Placeholder for message processing logic
        return `Processed: ${message}`;
    }
    render() {
        return (_jsxs(MainLayout, { children: [_jsx(MessageList, { items: this.state.messages }), _jsx(MainMessageInput, { onSubmit: this.handleSubmit })] }));
    }
}
export default ChatClient;
//# sourceMappingURL=chat-client.js.map