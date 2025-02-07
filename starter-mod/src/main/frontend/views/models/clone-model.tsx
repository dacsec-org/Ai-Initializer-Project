import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {
  Button,
  Dialog,
  Notification,
  TextField
} from '@vaadin/react-components';
import { Models } from './Models';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';
import { ModelActions } from 'Frontend/enums/ModelActions';

export const config: ViewConfig = {
  menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone',
};

const CloneModelView: React.FC = () => {
  const [sourcePath, setSourcePath] = useState('');
  const [dialogOpened, setDialogOpened] = useState(false);
  const [dialogMessage, setDialogMessage] = useState('');
  const [dialogAction, setDialogAction] = useState<() => void>(() => {});

  const openDialog = (message: string, action: () => void) => {
    setDialogMessage(message);
    setDialogAction(() => action);
    setDialogOpened(true);
  };

  const handleDialogClose = () => {
    setDialogOpened(false);
  };

  const handleClone = async () => {
    const response = await Models.getModels(ModelActions.CLONE, sourcePath, '');
    Notification.show("Cloning successful: " + response);
    setDialogOpened(false);
  };

  const handleInputChange = (e: TextFieldValueChangedEvent) => {
    setSourcePath(e.detail.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Source Path"
          value={sourcePath}
          onValueChanged={handleInputChange}
        />
        <Button
          onClick={() => openDialog('Are you sure you want to clone this model?', handleClone)}
          style={{ backgroundColor: 'blue' }}>Clone Model</Button>
      </section>
      <Dialog opened={dialogOpened} onOpenedChanged={(e) => setDialogOpened(e.detail.value)}>
        <div>
          <p>{dialogMessage}</p>
          <div className="flex gap-s">
            <Button theme="primary" onClick={() => {
              dialogAction();
              handleDialogClose();
            }}>
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
};

export default CloneModelView;
