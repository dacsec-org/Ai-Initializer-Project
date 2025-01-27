import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { Component, createRef } from 'react';
import { MessageList, Notification } from '@vaadin/react-components';
import MainLayout from './@layout';
import MainMessageInput from './components/main-message-input';
import { MessagesService } from 'Frontend/generated/endpoints';

export const config = {
    menu: { order: 1, icon: 'line-awesome/svg/rocket-chat' }, title: 'Chat',
};

class ChatClientView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            messageSets: [],
            loading: false,
            error: null,
        };
        this.messageEndRef = createRef();
    }

    addMessageSet(userRequest, aiResponse) {
        const userMessage = {
            text: userRequest,
            time: new Date().toLocaleTimeString(),
            userName: 'User',
            userColorIndex: 1,
            options: this.renderMessageOptions(),
        };

        const aiMessage = {
            text: aiResponse,
            time: new Date().toLocaleTimeString(),
            userName: 'AI',
            userColorIndex: 2,
            options: this.renderMessageOptions(),
        };

        const messageSet = { userMessage, aiMessage };

        this.setState((prevState) => ({
            messageSets: [...prevState.messageSets, messageSet],
            loading: false,
            error: null,
        }), this.scrollToBottom);
    }

    renderMessageOptions() {
        return (
            _jsxs("div", { className: "message-options", children: [
                _jsx("span", { role: "img", "aria-label": "thumbs-up", onClick: () => alert('Thumbs up clicked!'), children: "👍" }),
                _jsx("span", { role: "img", "aria-label": "thumbs-down", onClick: () => alert('Thumbs down clicked!'), children: "👎" }),
                _jsx("span", { role: "img", "aria-label": "trash", onClick: () => alert('Trash clicked!'), children: "🗑️" }),
                _jsx("span", { role: "img", "aria-label": "retry", onClick: () => alert('Retry clicked!'), children: "🔄" })
            ]})
        );
    }

    handleRequest = async (message) => {
        const response = await MessagesService.processMessages('request');
        Notification.show('Request processed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleResponse = async (message) => {
        const response = await MessagesService.processMessages('response');
        Notification.show('Response processed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleThumbsUp = async (message) => {
        const response = await MessagesService.processMessages('thumbs_up');
        Notification.show('Thumbs up processed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleThumbsDown = async (message) => {
        const response = await MessagesService.processMessages('thumbs_down');
        Notification.show('Thumbs down processed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleTrash = async (message) => {
        const response = await MessagesService.processMessages('trash');
        Notification.show('Message trashed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleRetry = async (message) => {
        const response = await MessagesService.processMessages('retry');
        Notification.show('Retry processed' + message + response);
        this.setState({ loading: true, error: null });
    };

    handleSubmit = async (event) => {
        const userRequest = event.detail.value;
        this.setState({ loading: true, error: null });
        const aiResponse = event.detail.value;
        this.addMessageSet(userRequest, aiResponse);
    };

    scrollToBottom = () => {
        if (this.messageEndRef.current) {
            this.messageEndRef.current.scrollIntoView({ behavior: 'smooth' });
        }
    };

    render() {
        return (
            _jsxs(MainLayout, { children: [
                this.state.messageSets.map((set, index) => (
                    _jsxs("div", { className: "message-set", children: [
                        _jsx(MessageList, { items: [set.userMessage] }),
                        _jsx(MessageList, { items: [set.aiMessage] }),
                        set.userMessage.options
                    ]}, index)
                )),
                this.state.loading && _jsx("div", { children: "Loading..." }),
                this.state.error && _jsx("div", { className: "error", children: this.state.error }),
                _jsx(MainMessageInput, { onSubmit: this.handleSubmit }),
                _jsx("div", { ref: this.messageEndRef })
            ]})
        );
    }
}

export default ChatClientView;
//# sourceMappingURL=chat-client.js.map
