import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Notification } from '@vaadin/react-components';
import { SnapShotsService } from 'Frontend/generated/endpoints';

/**
 * <h3>{@link ViewConfig}</h3>
 */
export const config: ViewConfig = {
  menu: { order: 14, icon: 'line-awesome/svg/solid/camera.svg' },
  title: 'SnapshotsView'
};

/**
 * <h3>{@link SnapshotsViewState}</h3>
 */
interface SnapshotsViewState {
  snapshots: string[];
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

/**
 * <h1>{@link SnapshotsView}</h1>
 */
class SnapshotsView extends Component<{}, SnapshotsViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      snapshots: [],
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {}
    };
  }

  /**
   * <h3>{@link openDialog}</h3>
   * @param message the message to display in the dialog
   * @param action the action to execute when the user confirms the dialog
   */
  openDialog = (message: string, action: () => void) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true
    });
  };

  /**
   * <h3>{@link handleDialogClose}</h3>
   */
  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };

  /**
   * <h3>{@link listSnapshots}</h3>
   */
  listSnapshots = async () => {
    const response = await SnapShotsService.manageSnapshots('list', '', '');
    // this.setState({ snapshots: response });
    Notification.show("SnapshotsView listed successfully" + response);
  };

  /**
   * <h3>{@link copySnapshot}</h3>
   */
  copySnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('copy', '', '');
    // this.setState({ snapshots: response });
    Notification.show('Snapshot copied successfully' + response);
  };

  /**
   * <h3>{@link createSnapshot}</h3>
   */
  createSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('create', '', '');
    // this.setState({ snapshots: response });
    Notification.show('Snapshot created successfully' + response);
  };

  /**
   * <h3>{@link deleteSnapshot}</h3>
   */
  deleteSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('delete', '', '');
    // this.setState({ snapshots: response });
    Notification.show('Snapshot deleted successfully' + response);
  };

  /**
   * <h3>{@link render}</h3>
   */
  render() {
    const { snapshots, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button
            onClick={() => this.openDialog('Are you sure you want to create a snapshot?', this.createSnapshot)}
            style={{ backgroundColor: 'green' }}>Create Snapshot</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to list snapshots?', this.listSnapshots)}
            style={{ backgroundColor: 'blue' }}>List SnapshotsView</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to update the snapshot?', this.copySnapshot)}
            style={{ backgroundColor: 'yellow' }}>Update Snapshot</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to delete the snapshot?', this.deleteSnapshot)}
            style={{ backgroundColor: 'red' }}>Delete Snapshot</Button>
        </section>
        <ul>
          {snapshots.map((snapshot) => (
            <li key={snapshot}>
              {snapshot}
              <Button
                onClick={() => this.openDialog('Are you sure you want to delete this snapshot?', () => this.deleteSnapshot())}
                style={{ backgroundColor: 'red' }}>Delete</Button>
            </li>
          ))}
        </ul>
        <Dialog opened={dialogOpened}
                onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>{dialogMessage}</p>
            <div className="flex gap-s">
              <Button theme="primary" onClick={() => {
                dialogAction();
                this.handleDialogClose();
              }}>
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
