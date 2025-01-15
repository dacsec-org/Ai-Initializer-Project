import React, { useEffect } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button, Dialog, Details, VerticalLayout } from '@vaadin/react-components';
import { RagService } from 'Frontend/generated/endpoints.js';
import { useNavigate } from 'react-router';

const anchorStyle = {
  textDecoration: 'none',
  color: 'var(--lumo-primary-text-color)',
};

export const config: ViewConfig = {
  menu: { order: 7, icon: 'line-awesome/svg/brain-solid.svg' },
  title: 'RAG Settings',
};

export default function RagSettingsView() {
  const rags = useSignal<string[]>([]);
  const conversations = useSignal<string[]>([]);
  const dialogOpened = useSignal(false);
  const dialogMessage = useSignal('');
  const dialogAction = useSignal<() => void>(() => {});
  const navigate = useNavigate();

  const openDialog = (message: string, action: () => void) => {
    dialogMessage.value = message;
    dialogAction.value = action;
    dialogOpened.value = true;
  };

  const handleDialogClose = () => {
    dialogOpened.value = false;
  };

  const listRags = async () => {
    rags.value = await RagService.listRags();
  };

  const createRag = async () => {
    rags.value = await RagService.createRag();
  };

  const updateRag = async () => {
    rags.value = await RagService.updateRag();
  };

  const deleteRag = async () => {
    rags.value = await RagService.deleteRag();
  };

  const listConversations = async () => {
    conversations.value = await RagService.listConversations();
  };

  useEffect(() => {
    listConversations();
  }, []);

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button onClick={() => openDialog('Are you sure you want to create a RAG?', createRag)} style={{ backgroundColor: 'green' }}>Create RAG</Button>
        <Button onClick={listRags} style={{ backgroundColor: 'blue' }}>List RAGs</Button>
        <Button onClick={() => openDialog('Are you sure you want to update the RAG?', updateRag)} style={{ backgroundColor: 'yellow' }}>Update RAG</Button>
        <Button onClick={() => openDialog('Are you sure you want to delete the RAG?', deleteRag)} style={{ backgroundColor: 'red' }}>Delete RAG</Button>
      </section>
      <ul>
        {rags.value.map((rag) => (
          <li key={rag}>
            {rag}
            <Button onClick={() => openDialog('Are you sure you want to delete this RAG?', () => deleteRag())} style={{ backgroundColor: 'red' }}>Delete</Button>
          </li>
        ))}
      </ul>
      <Details summary="Conversation Histories" opened>
        <VerticalLayout>
          {conversations.value.map((conversation, index) => (
            <a key={index} href="#" style={anchorStyle}>
              {conversation}
            </a>
          ))}
        </VerticalLayout>
      </Details>
      <Dialog opened={dialogOpened.value} onOpenedChanged={(e) => dialogOpened.value = e.detail.value}>
        <div>
          <p>{dialogMessage.value}</p>
          <div className="flex gap-s">
            <Button theme="primary" onClick={() => { dialogAction.value(); handleDialogClose(); }}>
              Yes
            </Button>
            <Button theme="secondary" onClick={handleDialogClose}>
              No
            </Button>
          </div>
        </div>
      </Dialog>
    </>
  );
}
