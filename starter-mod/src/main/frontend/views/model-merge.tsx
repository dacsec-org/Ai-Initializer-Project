import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';

export const config: ViewConfig = {
  menu: { order: 9, icon: 'line-awesome/svg/merge-solid.svg' },
  title: 'Merge Model',
};

interface MergeModelViewState {
  modelPath1: string;
  modelPath2: string;
  dialogOpened: boolean;
}

/**
 * <h1>{@link MergeModelView}</h1>
 */
class MergeModelView extends Component<{}, MergeModelViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      modelPath1: '',
      modelPath2: '',
      dialogOpened: false
    };
  }

  handleMerge = async () => {
    const { modelPath1, modelPath2 } = this.state;
    const response = await ModelsService.processModel("merge", modelPath1, modelPath2, {});
    Notification.show("Merged models" + response);
    this.setState({ dialogOpened: false });
  };

  openDialog = () => {
    this.setState({ dialogOpened: true });
  };

  closeDialog = () => {
    this.setState({ dialogOpened: false });
  };

  handleInputChange1 = (e: TextFieldValueChangedEvent) => {
    this.setState({ modelPath1: e.detail.value });
  };

  handleInputChange2 = (e: TextFieldValueChangedEvent) => {
    this.setState({ modelPath2: e.detail.value });
  };

  render() {
    const { modelPath1, modelPath2, dialogOpened } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Model Path 1"
            value={modelPath1}
            onValueChanged={(e) => this.handleInputChange1(e)}
          />
          <TextField
            label="Model Path 2"
            value={modelPath2}
            onValueChanged={(e) => this.handleInputChange2(e)}
          />
          <Button onClick={this.openDialog}>
            Merge Models
          </Button>
        </section>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>Are you sure you want to merge these models?</p>
            <div className="flex gap-s">
              <Button theme="primary" onClick={this.handleMerge}>
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

export default MergeModelView;
