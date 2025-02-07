import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Details, VerticalLayout, Notification } from '@vaadin/react-components';
// @ts-ignore
import { EmbeddingService } from 'Frontend/generated/endpoints';
import { useNavigate } from 'react-router';
export const config = {
    menu: { order: 11, icon: 'line-awesome/svg/layer-group-solid.svg' }, title: 'Embedding Settings'
};
const anchorStyle = {
    textDecoration: 'none',
    color: 'var(--lumo-primary-text-color)',
};
const EmbeddingSettingsView = () => {
    const [embeddings, setEmbeddings] = useState([]);
    const [conversations, setConversations] = useState([]);
    const [dialogOpened, setDialogOpened] = useState(false);
    const [dialogMessage, setDialogMessage] = useState('');
    const [dialogAction, setDialogAction] = useState(() => { });
    const navigate = useNavigate();
    const openDialog = (message, action) => {
        setDialogMessage(message);
        setDialogAction(() => action);
        setDialogOpened(true);
    };
    const handleDialogClose = () => {
        setDialogOpened(false);
    };
    const word2VecEmbedding = async () => {
        const response = await EmbeddingService.processEmbedding('word2vec', '');
        Notification.show('Embedding created successfully' + response);
        setDialogOpened(false);
    };
    const gloveEmbedding = async () => {
        const response = await EmbeddingService.processEmbedding('glove', '');
        Notification.show('Embedding created successfully' + response);
        setDialogOpened(false);
    };
    const fastTextEmbedding = async () => {
        const response = await EmbeddingService.processEmbedding('fasttext', '');
        Notification.show('Embedding created successfully' + response);
        setDialogOpened(false);
    };
    const bertEmbeddings = async () => {
        const response = await EmbeddingService.processEmbedding('bert', '');
        Notification.show('Embedding created successfully' + response);
        setDialogOpened(false);
    };
    const gptEmbeddings = async () => {
        const response = await EmbeddingService.processEmbedding('gpt', '');
        Notification.show('Embedding created successfully' + response);
        setDialogOpened(false);
    };
    const transformEmbedding = async () => {
        const response = await EmbeddingService.processEmbedding('transform', '');
        Notification.show('Embedding transformed successfully' + response);
        setDialogOpened(false);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => openDialog('Are you sure you want to create a new embedding model?', word2VecEmbedding), style: { backgroundColor: 'green' }, children: "Create Word2Vec" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a new embedding model?', gloveEmbedding), style: { backgroundColor: 'blue' }, children: "Create GloVe" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a new embedding model?', fastTextEmbedding), style: { backgroundColor: 'yellow' }, children: "Create FastText" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a new embedding model?', bertEmbeddings), style: { backgroundColor: 'purple' }, children: "Create BERT" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a new embedding model?', gptEmbeddings), style: { backgroundColor: 'orange' }, children: "Create GPT" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to transform this embedding?', transformEmbedding), style: { backgroundColor: 'red' }, children: "Transform" })] }), _jsx("ul", { children: embeddings.map((embedding) => (_jsx("li", { children: embedding }, embedding))) }), _jsx(Details, { summary: "Conversation Histories", opened: true, children: _jsx(VerticalLayout, { children: conversations.map((conversation, index) => (_jsx("a", { href: "#", style: anchorStyle, children: conversation }, index))) }) }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => { dialogAction(); handleDialogClose(); }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: handleDialogClose, children: "No" })] })] }) })] }));
};
export default EmbeddingSettingsView;
//# sourceMappingURL=embedding-settings.js.map