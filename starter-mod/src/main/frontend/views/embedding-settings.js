import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { Component } from 'react';
import { Button, Dialog, Details, VerticalLayout, Notification } from '@vaadin/react-components';
import { EmbeddingService } from 'Frontend/generated/endpoints';
import { useNavigate } from 'react-router';
const anchorStyle = {
    textDecoration: 'none',
    color: 'var(--lumo-primary-text-color)',
};
export const config = {
    menu: { order: 11, icon: 'line-awesome/svg/cogs.svg' },
    title: 'Embedding',
};
class EmbeddingSettingsView extends Component {
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
        this.word2VecEmbedding = async () => {
            const response = await EmbeddingService.processEmbedding('word2vec');
            Notification.show('Embedding created successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.gloveEmbedding = async () => {
            const response = await EmbeddingService.processEmbedding('glove');
            Notification.show('Embedding created successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.fastTextEmbedding = async () => {
            const response = await EmbeddingService.processEmbedding('fasttext');
            Notification.show('Embedding created successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.bertEmbeddings = async () => {
            const response = await EmbeddingService.processEmbedding('bert');
            Notification.show('Embedding created successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.gptEmbeddings = async () => {
            const response = await EmbeddingService.processEmbedding('gpt');
            Notification.show('Embedding created successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.transformEmbedding = async () => {
            const response = await EmbeddingService.processEmbedding('transform');
            Notification.show('Embedding transformed successfully' + response);
            this.setState({ dialogOpened: false });
        };
        this.state = {
            EMBEDDINGS: [],
            conversations: [],
            dialogOpened: false,
            dialogMessage: '',
            dialogAction: () => { },
        };
        this.navigate = useNavigate();
    }
    render() {
        const { EMBEDDINGS, conversations, dialogOpened, dialogMessage, dialogAction } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a new embedding model?', this.word2VecEmbedding), style: { backgroundColor: 'green' }, children: "Create Word2Vec" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a new embedding model?', this.gloveEmbedding), style: { backgroundColor: 'blue' }, children: "Create GloVe" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a new embedding model?', this.fastTextEmbedding), style: { backgroundColor: 'yellow' }, children: "Create FastText" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a new embedding model?', this.bertEmbeddings), style: { backgroundColor: 'purple' }, children: "Create BERT" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a new embedding model?', this.gptEmbeddings), style: { backgroundColor: 'orange' }, children: "Create GPT" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to transform this embedding?', this.transformEmbedding), style: { backgroundColor: 'red' }, children: "Transform" })] }), _jsx("ul", { children: EMBEDDINGS.map((embedding) => (_jsx("li", { children: embedding }, embedding))) }), _jsx(Details, { summary: "Conversation Histories", opened: true, children: _jsx(VerticalLayout, { children: conversations.map((conversation, index) => (_jsx("a", { href: "#", style: anchorStyle, children: conversation }, index))) }) }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => { dialogAction(); this.handleDialogClose(); }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.handleDialogClose, children: "No" })] })] }) })] }));
    }
}
export default EmbeddingSettingsView;
//# sourceMappingURL=embedding-settings.js.map