import React, { useState } from 'react';
import Button from '../components/button';
import Dialog from '../components/dialog';
import { NotificationService } from '../components/notifications';
import InputArea from '../components/input-area';
import { ModelsBridge } from '../bridges/models-bridge';
import { ModelActions } from '../enums/ModelActions';
import { firstValueFrom } from 'rxjs';

const DestroyModelView: React.FC = () => {
  const [modelPath, setModelPath] = useState('');
  const [dialogOpened, setDialogOpened] = useState(false);

  const handleDelete = async () => {
    try {
      const response = await firstValueFrom(ModelsBridge(ModelActions.DESTROY));
      NotificationService.show("Model destroyed: " + response);
    } catch (error) {
      console.error('Error destroying model:', error);
      NotificationService.show('Error destroying model. Please try again.', 'error');
    } finally {
      setDialogOpened(false);
    }
  };

  const openDialog = () => {
    setDialogOpened(true);
  };

  const closeDialog = () => {
    setDialogOpened(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setModelPath(e.target.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <InputArea
          label="Model Path"
          value={modelPath}
          // onValueChanged={handleInputChange}
        />
        <Button onClick={openDialog}>
          Delete Model
        </Button>
      </section>
      <Dialog opened={dialogOpened}
              onOpenedChanged={(e) => setDialogOpened(e.detail.value)}
              isOpen={false} message={''} onClose={function(): void {
        throw new Error('Function not implemented.');
      }}>
        <div>
          <p>Are you sure you want to delete this model?
            Any work associated with this model will be deleted as well!</p>
          <div className="flex gap-s">
            <Button theme="primary" onClick={handleDelete}>
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
