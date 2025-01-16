import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog } from '@vaadin/react-components';
import { SnapShotService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/snapshot-solid.svg' },
  title: 'SnapshotsView',
};

interface SnapshotsViewState {
  snapshots: string[];
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

class SnapshotsView extends Component<{}, SnapshotsViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      snapshots: [],
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {},
    };
  }

  openDialog = (message: string, action: () => void) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true,
    });
  };

  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };

  listSnapshots = async () => {
    const snapshots = await SnapShotService.listSnapshots();
    this.setState({ snapshots });
  };

  copySnapshot = async () => {
    const snapshots = await SnapShotService.updateSnapshot();
    this.setState({ snapshots });
  };

  createSnapshot = async () => {
    const snapshots = await SnapShotService.createSnapshot();
    this.setState({ snapshots });
  };

  deleteSnapshot = async () => {
    const snapshots = await SnapShotService.deleteSnapshot();
    this.setState({ snapshots });
  };

  render() {
    const { snapshots, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button onClick={() => this.openDialog('Are you sure you want to create a snapshot?', this.createSnapshot)} style={{ backgroundColor: 'green' }}>Create Snapshot</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to list snapshots?', this.listSnapshots)} style={{ backgroundColor: 'blue' }}>List Snapshots</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to update the snapshot?', this.copySnapshot)} style={{ backgroundColor: 'yellow' }}>Update Snapshot</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to delete the snapshot?', this.deleteSnapshot)} style={{ backgroundColor: 'red' }}>Delete Snapshot</Button>
        </section>
        <ul>
          {snapshots.map((snapshot) => (
            <li key={snapshot}>
              {snapshot}
              <Button onClick={() => this.openDialog('Are you sure you want to delete this snapshot?', () => this.deleteSnapshot())} style={{ backgroundColor: 'red' }}>Delete</Button>
            </li>
          ))}
        </ul>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>{dialogMessage}</p>
            <div className="flex gap-s">
              <Button theme="primary" onClick={() => { dialogAction(); this.handleDialogClose(); }}>
                Yes
              </Button>
              <Button theme="secondary" onClick={this.handleDialogClose}>
                No
              </Button>
            </div>
          </div>
        </Dialog>
      </>
    );
  }
}

export default SnapshotsView;
