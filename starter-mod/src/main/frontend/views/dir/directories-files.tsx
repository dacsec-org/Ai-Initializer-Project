import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, Dialog } from '@vaadin/react-components';
// @ts-ignore
import { DirFileService } from 'Frontend/generated/endpoints';
import CustomTextFields from '../components/custom-textfields';

export const config: ViewConfig = {
  menu: { order: 4, icon: 'line-awesome/svg/folder-open-solid.svg' }, title: 'Directories ~ Files' };

const DirFileView: React.FC = () => {
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

  const createDirectory = async () => {
    const response = await DirFileService.processDirFileAction('create_directory', '', '');
    Notification.show("Directory created successfully: " + response);
    setDialogOpened(false);
  };

  const createFile = async () => {
    const response = await DirFileService.processDirFileAction('create_file', '', '');
    Notification.show("File created successfully: " + response);
    setDialogOpened(false);
  };

  const deleteDirectory = async () => {
    const response = await DirFileService.processDirFileAction('delete_directory', '', '');
    Notification.show("Directory deleted successfully: " + response);
    setDialogOpened(false);
  };

  const deleteFile = async () => {
    const response = await DirFileService.processDirFileAction('delete_file', '', '');
    Notification.show("File deleted successfully: " + response);
    setDialogOpened(false);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <CustomTextFields renderTextField={3} />
        <CustomTextFields renderTextField={4} />
        <Button
          onClick={() => openDialog('Are you sure you want to create a directory?', createDirectory)}
          style={{ backgroundColor: 'green' }}>Create Directory</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to create a file?', createFile)}
          style={{ backgroundColor: 'blue' }}>Create File</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to delete the directory?', deleteDirectory)}
          style={{ backgroundColor: 'red' }}>Delete Directory</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to delete the file?', deleteFile)}
          style={{ backgroundColor: 'orange' }}>Delete File</Button>
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

export default DirFileView;
