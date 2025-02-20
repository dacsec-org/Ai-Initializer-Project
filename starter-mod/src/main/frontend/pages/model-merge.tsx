import React, { useState } from 'react';
import Button from '../components/button';
import Dialog from '../components/dialog';
import { NotificationService } from '../components/notifications';
import InputArea from '../components/input-area';
import { ModelsBridge } from '../bridges/models-bridge';
import { ModelActions } from '../enums/model-actions';
import { firstValueFrom } from 'rxjs';

const MergeModelView: React.FC = () => {
  const [modelPath1, setModelPath1] = useState('');
  const [modelPath2, setModelPath2] = useState('');
  const [dialogOpened, setDialogOpened] = useState(false);

  const handleMerge = async () => {
    try {
      const response = await firstValueFrom(ModelsBridge(ModelActions.MERGE));
      NotificationService.show("Merged models: " + response);
    } catch (error) {
      console.error('Error merging models:', error);
      NotificationService.show('Error merging models. Please try again.', 'error');
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

  const handleInputChange1 = (e: React.ChangeEvent<HTMLInputElement>) => {
    setModelPath1(e.target.value);
  };

  const handleInputChange2 = (e: React.ChangeEvent<HTMLInputElement>) => {
    setModelPath2(e.target.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <InputArea
          label="Model Path 1"
          value={modelPath1}
        />
        <InputArea
          label="Model Path 2"
          value={modelPath2}
        />
        <Button onClick={openDialog}>
          Merge Models
        </Button>
      </section>
      <Dialog opened={dialogOpened}
              onOpenedChanged={(e) => setDialogOpened(e.detail.value)}
              isOpen={false} message={''} onClose={function(): void {
        throw new Error('Function not implemented.');
      }}>
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
