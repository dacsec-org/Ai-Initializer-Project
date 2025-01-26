import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog, Notification } from '@vaadin/react-components';
import { ServersService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 13, icon: 'line-awesome/svg/solid/server.svg' },
  title: 'Servers'
};

interface ManageViewState {
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

/**
 * <h1>{@link ManageServersView}</h1>
 */
class ManageServersView extends Component<{}, ManageViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {}
    };
  }

  openDialog = (message: string, action: () => void) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true
    });
  };

  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };

  startServer = async () => {
    const result = await ServersService.manageServer('start');
    Notification.show("Started server" + result);
  };

  stopServer = async () => {
    const result = await ServersService.manageServer('stop');
    Notification.show("Server stopped" + result);
  };

  restartServer = async () => {
    const result = await ServersService.manageServer('restart');
    Notification.show("Restarted server" + result);
  };

  render() {
    const { dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button
            onClick={() => this.openDialog('Are you sure you want to start the server?', this.startServer)}
            style={{ backgroundColor: 'green' }}>Start Server</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to stop the server?', this.stopServer)}
            style={{ backgroundColor: 'red' }}>Stop Server</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to restart the server?', this.restartServer)}
            style={{ backgroundColor: 'blue' }}>Restart Server</Button>
        </section>
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

export default ManageServersView;
