import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Dialog } from '@vaadin/react-components';
import { ServersService } from '../routes';

export const config: ViewConfig = {
  menu: { order: 12, icon: 'line-awesome/svg/solid/server.svg' },
  title: 'Manage',
};

interface ManageViewState {
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

class ManageView extends Component<{}, ManageViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
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

  startServer = async () => {
    const result = await ServersService.startServer();
    alert(result);
  };

  stopServer = async () => {
    const result = await ServersService.stopServer();
    alert(result);
  };

  restartServer = async () => {
    const result = await ServersService.restartServer();
    alert(result);
  };

  loadModel = async () => {
    const modelPath = prompt('Enter model path:');
    if (modelPath) {
      const result = await ServersService.loadModel(modelPath);
      alert('Model loaded successfully');
    }
  };

  unloadModel = async () => {
    const result = await ServersService.unloadModel(new Uint8Array());
    alert(result);
  };

  render() {
    const { dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <Button onClick={() => this.openDialog('Are you sure you want to start the server?', this.startServer)} style={{ backgroundColor: 'green' }}>Start Server</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to stop the server?', this.stopServer)} style={{ backgroundColor: 'red' }}>Stop Server</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to restart the server?', this.restartServer)} style={{ backgroundColor: 'blue' }}>Restart Server</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to load the model?', this.loadModel)} style={{ backgroundColor: 'yellow' }}>Load Model</Button>
          <Button onClick={() => this.openDialog('Are you sure you want to unload the model?', this.unloadModel)} style={{ backgroundColor: 'orange' }}>Unload Model</Button>
        </section>
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

export default ManageView;
