import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';

export const config: ViewConfig = {
  menu: { order: 8, icon: 'line-awesome/svg/trash-alt-solid.svg' },
  title: 'Delete ~ Model',
};

const DestroyModelView: React.FC = () => {
  const [modelPath, setModelPath] = useState('');
  const [dialogOpened, setDialogOpened] = useState(false);

  const handleDelete = async () => {
    const response = await ModelsService.processModel('destroy', modelPath, '');
    Notification.show("Model destroyed" + response);
    setDialogOpened(false);
  };

  const openDialog = () => {
    setDialogOpened(true);
  };

  const closeDialog = () => {
    setDialogOpened(false);
  };

  const handleInputChange = (e: TextFieldValueChangedEvent) => {
    setModelPath(e.detail.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Model Path"
          value={modelPath}
          onValueChanged={handleInputChange}
        />
        <Button onClick={openDialog}>
          Delete Model
        </Button>
      </section>
      <Dialog opened={dialogOpened} onOpenedChanged={(e) => setDialogOpened(e.detail.value)}>
        <div>
          <p>Are you sure you want to delete this model?
            Any work associated with this model will be deleted as well!</p>
          <div className="flex gap-s">
            <Button theme="primary error" onClick={handleDelete}>
              Yes
            </Button>
            <Button theme="secondary" onClick={closeDialog}>
              No
            </Button>
          </div>
        </div>
      </Dialog>
    </>
  );
};

export default DestroyModelView;
