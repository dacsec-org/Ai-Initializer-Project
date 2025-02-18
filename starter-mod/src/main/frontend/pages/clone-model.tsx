import React, { useState } from 'react';
import { NotificationService as Notification } from '../components/notifications';
import Button from '../components/button';
import { ModelsBridge } from '../bridges/models-bridge';
import { ModelActions } from '../enums/ModelActions';
import Dialog from '../components/dialog';
import InputArea from '../components/input-area';

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

  const handleClone = () => {
    ModelsBridge(ModelActions.CLONE).subscribe({
      next: (response) => {
        Notification.show("Cloning successful: " + response);
        setDialogOpened(false);
      },
      error: (error) => {
        Notification.show("Error cloning model: " + error);
        setDialogOpened(false);
      }
    });
  };

  const handleInputChange = (e: any) => {
    setSourcePath(e.target.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <InputArea
          label="Source Path"
          value={sourcePath}
          onValueChanged={handleInputChange}
        />
        <Button
          onClick={() => openDialog('Are you sure you want to clone this model?', handleClone)}
          style={{ backgroundColor: 'blue' }}>Clone Model</Button>
      </section>
      <Dialog
        isOpen={dialogOpened}
        message={dialogMessage}
        onClose={handleDialogClose}
      >
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
