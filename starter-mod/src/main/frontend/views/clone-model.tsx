import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';

export const config: ViewConfig = {
  menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
  title: 'Clone',
};

interface CloneModelViewState {
  sourcePath: string;
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

class CloneModelView extends Component<{}, CloneModelViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      sourcePath: '',
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

  handleClone = async () => {
    const { sourcePath } = this.state;
    const response = await ModelsService.processModel('clone', sourcePath, '');
    Notification.show("Cloning successful" + response);
    this.setState({ dialogOpened: false });
  };

  handleInputChange = (e: TextFieldValueChangedEvent) => {
    this.setState({ sourcePath: e.detail.value });
  };

  render() {
    const { sourcePath, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Source Path"
            value={sourcePath}
            onValueChanged={(e: TextFieldValueChangedEvent) => this.handleInputChange(e)}
          />
          <Button
            onClick={() => this.openDialog('Are you sure you want to clone this model?', this.handleClone)}
            style={{ backgroundColor: 'blue' }}>Clone Model</Button>
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

export default CloneModelView;
