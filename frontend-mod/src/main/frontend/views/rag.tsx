import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Details, VerticalLayout } from '@vaadin/react-components';
import { RagService } from 'Frontend/generated/endpoints.js';
import { NavigateFunction, useNavigate } from 'react-router';

const anchorStyle = {
  textDecoration: 'none',
  color: 'var(--lumo-primary-text-color)',
};

export const config: ViewConfig = {
  menu: { order: 9, icon: 'line-awesome/svg/cogs.svg' },
  title: 'RAG Settings',
};

interface RagSettingsViewState {
  rags: string[];
  conversations: string[];
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

class RagSettingsView extends Component<{}, RagSettingsViewState> {
  navigate: NavigateFunction;

  constructor(props: {}) {
    super(props);
    this.state = {
      rags: [],
      conversations: [],
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {},
    };
    this.navigate = useNavigate();
  }

  componentDidMount() {
    this.listConversations();
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

  listRags = async () => {
    const rags = await RagService.listRags();
    this.setState({ rags });
  };

  createRag = async () => {
    const rags = await RagService.createRag();
    this.setState({ rags });
  };

  updateRag = async () => {
    const rags = await RagService.updateRag();
    this.setState({ rags });
  };

  deleteRag = async () => {
    const rags = await RagService.deleteRag();
    this.setState({ rags });
  };

  listConversations = async () => {
    const conversations = await RagService.listConversations();
    this.setState({ conversations });
  };

  render() {
    const { rags, conversations, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button onClick={() => this.openDialog('Are you sure you want to create a RAG?', this.createRag)} style={{ backgroundColor: 'green' }}>Create RAG</Button>
          <Button onClick={this.listRags} style={{ backgroundColor: 'blue' }}>List RAGs</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to update the RAG?', this.updateRag)} style={{ backgroundColor: 'yellow' }}>Update RAG</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to delete the RAG?', this.deleteRag)} style={{ backgroundColor: 'red' }}>Delete RAG</Button>
        </section>
        <ul>
          {rags.map((rag) => (
            <li key={rag}>
              {rag}
              <Button onClick={() => this.openDialog('Are you sure you want to delete this RAG?', this.deleteRag)} style={{ backgroundColor: 'red' }}>Delete</Button>
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

export default RagSettingsView;
