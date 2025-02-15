import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Notification } from '@vaadin/react-components';
import { Snapshots } from 'Frontend/bridges/Snapshots';
import { SnapShotsActions } from 'Frontend/enums/SnapShotsActions';
import { from } from 'rxjs';
import { catchError } from 'rxjs/operators';

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

  const handleSnapshotAction = (action: SnapShotsActions, successMessage: string) => {
    from(Snapshots.getSnapshots(action))
      .pipe(
        catchError((error) => {
          Notification.show('Error performing snapshot action');
          throw error;
        })
      )
      .subscribe((response) => {
        Notification.show(successMessage + response);
      });
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button
          onClick={() => openDialog('Are you sure you want to create a snapshot?', () => handleSnapshotAction(SnapShotsActions.CREATE, 'Snapshot created successfully'))}
          style={{ backgroundColor: 'green' }}>Create Snapshot</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to list snapshots?', () => handleSnapshotAction(SnapShotsActions.LIST, 'Snapshots listed successfully'))}
          style={{ backgroundColor: 'blue' }}>List Snapshots</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to update the snapshot?', () => handleSnapshotAction(SnapShotsActions.COPY, 'Snapshot copied successfully'))}
          style={{ backgroundColor: 'yellow' }}>Update Snapshot</Button>
        <Button
          onClick={() => openDialog('Are you sure you want to delete the snapshot?', () => handleSnapshotAction(SnapShotsActions.DELETE, 'Snapshot deleted successfully'))}
          style={{ backgroundColor: 'red' }}>Delete Snapshot</Button>
      </section>
      <ul>
        {snapshots.map((snapshot) => (
          <li key={snapshot}>
            {snapshot}
            <Button
              onClick={() => openDialog('Are you sure you want to delete this snapshot?', () => handleSnapshotAction(SnapShotsActions.DELETE, 'Snapshot deleted successfully'))}
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
