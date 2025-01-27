import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
// @ts-ignore
import { ModelsService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';

export const config: ViewConfig = {
  menu: { order: 8, icon: 'line-awesome/svg/trash-alt-solid.svg' },
  title: 'Delete ~ Model',
};

interface DeleteModelViewState {
  modelPath: string;
  dialogOpened: boolean;
}

/**
 * <h1>{@link DestroyModelView}</h1>
 */
class DestroyModelView extends Component<{}, DeleteModelViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      modelPath: '',
      dialogOpened: false
    };
  }

  handleDelete = async () => {
    const { modelPath } = this.state;
    const response = await ModelsService.processModel('destroy', modelPath, '');
    Notification.show("Model destroyed" + response);
    this.setState({ dialogOpened: false });
  }

  openDialog = () => {
    this.setState({ dialogOpened: true });
  };

  closeDialog = () => {
    this.setState({ dialogOpened: false });
  };

  handleInputChange = (e: TextFieldValueChangedEvent) => {
    this.setState({ modelPath: e.detail.value });
  };

  render() {
    const { modelPath, dialogOpened } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Model Path"
            value={modelPath}
            onValueChanged={(e) => this.handleInputChange(e)}
          />
          <Button onClick={this.openDialog}>
            Delete Model
          </Button>
        </section>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>Are you sure you want to delete this model?
              Any work associated with this model will be deleted as well!</p>
            <div className="flex gap-s">
              <Button theme="primary error" onClick={this.handleDelete}>
                Yes
              </Button>
              <Button theme="secondary" onClick={this.closeDialog}>
                No
              </Button>
            </div>
          </div>
        </Dialog>
      </>
    );
  }
}

export default DestroyModelView;
