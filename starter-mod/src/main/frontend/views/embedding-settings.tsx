import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Details, VerticalLayout, Notification } from '@vaadin/react-components';
import { EmbeddingService } from 'Frontend/generated/endpoints';
import { NavigateFunction, useNavigate } from 'react-router';

const anchorStyle = {
  textDecoration: 'none',
  color: 'var(--lumo-primary-text-color)',
};

export const config: ViewConfig = {
  menu: { order: 11, icon: 'line-awesome/svg/cogs.svg' },
  title: 'Embedding',
};

interface EmbeddingSettingsViewState {
  EMBEDDINGS: string[];
  conversations: string[];
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

/**
 * <h1>{@link EmbeddingSettingsView}</h1>
 */
class EmbeddingSettingsView extends Component<{}, EmbeddingSettingsViewState> {
  navigate: NavigateFunction;

  constructor(props: {}) {
    super(props);
    this.state = {
      EMBEDDINGS: [],
      conversations: [],
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {},
    };
    this.navigate = useNavigate();
  }

  openDialog = (message: string, action: () => void) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true,
    });
  };

  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };

  word2VecEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding('word2vec', '');
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  gloveEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding('glove', '');
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  fastTextEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding('fasttext', '');
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  bertEmbeddings = async () => {
    const response = await EmbeddingService.processEmbedding('bert', '');
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  gptEmbeddings = async () => {
    const response = await EmbeddingService.processEmbedding('gpt', '');
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  transformEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding('transform', '');
    Notification.show('Embedding transformed successfully' + response);
    this.setState({ dialogOpened: false });
  };

  render() {
    const { EMBEDDINGS, conversations, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?', this.word2VecEmbedding)}
                  style={{ backgroundColor: 'green' }}>Create Word2Vec</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?', this.gloveEmbedding)}
                  style={{ backgroundColor: 'blue' }}>Create GloVe</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?', this.fastTextEmbedding)}
                  style={{ backgroundColor: 'yellow' }}>Create FastText</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?', this.bertEmbeddings)}
                  style={{ backgroundColor: 'purple' }}>Create BERT</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?', this.gptEmbeddings)}
                  style={{ backgroundColor: 'orange' }}>Create GPT</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to transform this embedding?', this.transformEmbedding)}
                  style={{ backgroundColor: 'red' }}>Transform</Button>
        </section>
        <ul>
          {EMBEDDINGS.map((embedding) => (
            <li key={embedding}>
              {embedding}
            </li>
          ))}
        </ul>
        <Details summary="Conversation Histories" opened>
          <VerticalLayout>
            {conversations.map((conversation, index) => (
              <a key={index} href="#" style={anchorStyle}>
                {conversation}
              </a>
            ))}
          </VerticalLayout>
        </Details>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>{dialogMessage}</p>
            <div className="flex gap-s">
              <Button theme="primary" onClick={() => { dialogAction(); this.handleDialogClose(); }}>
                Yes
              </Button>
              <Button theme="secondary" onClick={this.handleDialogClose}>
                No
              </Button>
            </div>
          </div>
        </Dialog>
      </>
    );
  }
}

export default EmbeddingSettingsView;
