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
  menu: { order: 9, icon: 'line-awesome/svg/arrows-alt-h-solid.svg' },
  title: 'Merge Model' };

const MergeModelView: React.FC = () => {
  const [modelPath1, setModelPath1] = useState('');
  const [modelPath2, setModelPath2] = useState('');
  const [dialogOpened, setDialogOpened] = useState(false);

  const handleMerge = async () => {
    const response = await Models.getModels(ModelActions.MERGE, modelPath1, modelPath2);
    Notification.show("Merged models: " + response);
    setDialogOpened(false);
  };

  const openDialog = () => {
    setDialogOpened(true);
  };

  const closeDialog = () => {
    setDialogOpened(false);
  };

  const handleInputChange1 = (e: TextFieldValueChangedEvent) => {
    setModelPath1(e.detail.value);
  };

  const handleInputChange2 = (e: TextFieldValueChangedEvent) => {
    setModelPath2(e.detail.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Model Path 1"
          value={modelPath1}
          onValueChanged={handleInputChange1}
        />
        <TextField
          label="Model Path 2"
          value={modelPath2}
          onValueChanged={handleInputChange2}
        />
        <Button onClick={openDialog}>
          Merge Models
        </Button>
      </section>
      <Dialog opened={dialogOpened} onOpenedChanged={(e) => setDialogOpened(e.detail.value)}>
        <div>
          <p>Are you sure you want to merge these models?</p>
          <div className="flex gap-s">
            <Button theme="primary" onClick={handleMerge}>
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

export default MergeModelView;
