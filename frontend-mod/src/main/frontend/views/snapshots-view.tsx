import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button, Dialog } from '@vaadin/react-components';
import { SnapShotService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 13, icon: 'line-awesome/svg/snapshot-solid.svg' },
  title: 'SnapshotsView',
};

export default function SnapshotsView() {
  const snapshots = useSignal<string[]>([]);
  const dialogOpened = useSignal(false);
  const dialogMessage = useSignal('');
  const dialogAction = useSignal<() => void>(() => {});

  const openDialog = (message: string, action: () => void) => {
    dialogMessage.value = message;
    dialogAction.value = action;
    dialogOpened.value = true;
  };

  const handleDialogClose = () => {
    dialogOpened.value = false;
  };

  const listSnapshots = async () => {
    snapshots.value = await SnapShotService.listSnapshots();
  };

  const copySnapshot = async () => {
    snapshots.value = await SnapShotService.updateSnapshot();
  };

  const createSnapshot = async () => {
    snapshots.value = await SnapShotService.createSnapshot();
  };

  const deleteSnapshot = async () => {
    snapshots.value = await SnapShotService.deleteSnapshot();
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Button onClick={() => openDialog('Are you sure you want to create a snapshot?', createSnapshot)} style={{ backgroundColor: 'green' }}>Create Snapshot</Button>
        <Button onClick={() => openDialog('Are you sure you want to list snapshots?', listSnapshots)} style={{ backgroundColor: 'blue' }}>List Snapshots</Button>
        <Button onClick={() => openDialog('Are you sure you want to update the snapshot?', copySnapshot)} style={{ backgroundColor: 'yellow' }}>Update Snapshot</Button>
        <Button onClick={() => openDialog('Are you sure you want to delete the snapshot?', deleteSnapshot)} style={{ backgroundColor: 'red' }}>Delete Snapshot</Button>
      </section>
      <ul>
        {snapshots.value.map((snapshot) => (
          <li key={snapshot}>
            {snapshot}
            <Button onClick={() => openDialog('Are you sure you want to delete this snapshot?', () => deleteSnapshot())} style={{ backgroundColor: 'red' }}>Delete</Button>
          </li>
        ))}
      </ul>
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
