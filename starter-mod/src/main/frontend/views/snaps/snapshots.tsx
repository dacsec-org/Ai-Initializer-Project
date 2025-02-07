import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Notification } from '@vaadin/react-components';
// @ts-ignore
import { SnapShotsService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 14, icon: 'line-awesome/svg/camera-solid.svg' },
  title: 'Snapshots'
};

const SnapshotsView: React.FC = () => {
  const [snapshots, setSnapshots] = useState<string[]>([]);
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

  const listSnapshots = async () => {
    const response = await SnapShotsService.manageSnapshots('list', '', '');
    Notification.show("SnapshotsView listed successfully" + response);
  };

  const copySnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('copy', '', '');
    Notification.show('Snapshot copied successfully' + response);
  };

  const createSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('create', '', '');
    Notification.show('Snapshot created successfully' + response);
  };

  const deleteSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('delete', '', '');
    Notification.show('Snapshot deleted successfully' + response);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button
          onClick={() => openDialog('Are you sure you want to create a snapshot?', createSnapshot)}
          style={{ backgroundColor: 'green' }}>Create Snapshot</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to list snapshots?', listSnapshots)}
          style={{ backgroundColor: 'blue' }}>List SnapshotsView</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to update the snapshot?', copySnapshot)}
          style={{ backgroundColor: 'yellow' }}>Update Snapshot</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to delete the snapshot?', deleteSnapshot)}
          style={{ backgroundColor: 'red' }}>Delete Snapshot</Button>
      </section>
      <ul>
        {snapshots.map((snapshot) => (
          <li key={snapshot}>
            {snapshot}
            <Button
              onClick={() => openDialog('Are you sure you want to delete this snapshot?', () => deleteSnapshot())}
              style={{ backgroundColor: 'red' }}>Delete</Button>
          </li>
        ))}
      </ul>
      <Dialog opened={dialogOpened}
              onOpenedChanged={(e) => setDialogOpened(e.detail.value)}>
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

export default SnapshotsView;
