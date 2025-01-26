import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Details, VerticalLayout, Notification } from '@vaadin/react-components';
import { EmbeddingService } from 'Frontend/generated/endpoints';
import { NavigateFunction, useNavigate } from 'react-router';

const anchorStyle = {
  textDecoration: 'none',
  color: 'var(--lumo-primary-text-color)',
};

/**
 * <h3>{@link ViewConfig}</h3>
 */
export const config: ViewConfig = {
  menu: { order: 11, icon: 'line-awesome/svg/cogs.svg' },
  title: 'Embedding',
};

/**
 * <h3>{@link EmbeddingSettingsViewState}</h3>
 */
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

  /**
   * <h3>{@link openDialog}</h3>
   * @param message the message to display in the dialog
   * @param action the action to execute when the user confirms the dialog
   */
  openDialog = (message: string, action: () => void) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true,
    });
  };

  /**
   * <h3>{@link handleDialogClose}</h3>
   */
  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };


  /**
   * <h3>{@link word2VecEmbedding}</h3>
   */
  word2VecEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding("word2vec");
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  /**
   * <h3>{@link gloveEmbedding}</h3>
   */
  gloveEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding("glove");
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  /**
   * <h3>{@link fastTextEmbedding}</h3>
   */
  fastTextEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding("fasttext");
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  /**
   * <h3>{@link bertEmbeddings}</h3>
   */
  bertEmbeddings = async () => {
    const response = await EmbeddingService.processEmbedding("bert");
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  /**
   * <h3>{@link gptEmbeddings}</h3>
   */
  gptEmbeddings = async () => {
    const response = await EmbeddingService.processEmbedding("gpt");
    Notification.show('Embedding created successfully' + response);
    this.setState({ dialogOpened: false });
  }

  /**
   * <h3>{@link transformEmbedding}</h3>
   */
  transformEmbedding = async () => {
    const response = await EmbeddingService.processEmbedding("transform");
    Notification.show('Embedding transformed successfully' + response);
    this.setState({ dialogOpened: false });

  };

  /**
   * <h3>{@link render}</h3>
   */
  render() {
    const { EMBEDDINGS, conversations, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?'
            , this.word2VecEmbedding)} style={{ backgroundColor: 'green' }}>Create Word2Vec</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?'
            , this.gloveEmbedding)} style={{ backgroundColor: 'blue' }}>Create GloVe</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?'
            , this.fastTextEmbedding)} style={{ backgroundColor: 'yellow' }}>Create FastText</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?'
            , this.bertEmbeddings)} style={{ backgroundColor: 'purple' }}>Create BERT</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to create a new embedding model?'
            , this.gptEmbeddings)} style={{ backgroundColor: 'orange' }}>Create GPT</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to transform this embedding?'
            , this.transformEmbedding)} style={{ backgroundColor: 'red' }}>Transform</Button>
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
