import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Dialog, Details, VerticalLayout } from '@vaadin/react-components';
import { RagService } from 'Frontend/generated/endpoints.js';
import { useNavigate } from 'react-router';
const anchorStyle = {
    textDecoration: 'none',
    color: 'var(--lumo-primary-text-color)',
};
export const config = {
    menu: { order: 9, icon: 'line-awesome/svg/cogs.svg' },
    title: 'RAG Settings',
};
class RagSettingsView extends Component {
    constructor(props) {
        super(props);
        this.openDialog = (message, action) => {
            this.setState({
                dialogMessage: message,
                dialogAction: action,
                dialogOpened: true,
            });
        };
        this.handleDialogClose = () => {
            this.setState({ dialogOpened: false });
        };
        this.listRags = async () => {
            const rags = await RagService.listRags();
            this.setState({ rags });
        };
        this.createRag = async () => {
            const rags = await RagService.createRag();
            this.setState({ rags });
        };
        this.updateRag = async () => {
            const rags = await RagService.updateRag();
            this.setState({ rags });
        };
        this.deleteRag = async () => {
            const rags = await RagService.deleteRag();
            this.setState({ rags });
        };
        this.listConversations = async () => {
            const conversations = await RagService.listConversations();
            this.setState({ conversations });
        };
        this.state = {
            rags: [],
            conversations: [],
            dialogOpened: false,
            dialogMessage: '',
            dialogAction: () => { },
        };
        this.navigate = useNavigate();
    }
    componentDidMount() {
        this.listConversations();
    }
    render() {
        const { rags, conversations, dialogOpened, dialogMessage, dialogAction } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a RAG?', this.createRag), style: { backgroundColor: 'green' }, children: "Create RAG" }), _jsx(Button, { onClick: this.listRags, style: { backgroundColor: 'blue' }, children: "List RAGs" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to update the RAG?', this.updateRag), style: { backgroundColor: 'yellow' }, children: "Update RAG" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to delete the RAG?', this.deleteRag), style: { backgroundColor: 'red' }, children: "Delete RAG" })] }), _jsx("ul", { children: rags.map((rag) => (_jsxs("li", { children: [rag, _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to delete this RAG?', this.deleteRag), style: { backgroundColor: 'red' }, children: "Delete" })] }, rag))) }), _jsx(Details, { summary: "Conversation Histories", opened: true, children: _jsx(VerticalLayout, { children: conversations.map((conversation, index) => (_jsx("a", { href: "#", style: anchorStyle, children: conversation }, index))) }) }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => { dialogAction(); this.handleDialogClose(); }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.handleDialogClose, children: "No" })] })] }) })] }));
    }
}
export default RagSettingsView;
//# sourceMappingURL=rag.js.map