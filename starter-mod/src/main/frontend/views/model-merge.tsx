import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { MergeDestroyModelService } from 'Frontend/generated/endpoints';

/**
 * {@link config}
 * <p>
 *   Configuration for the view, including menu order, icon, and title.
 * </p>
 */
export const config: ViewConfig = {
  menu: { order: 5, icon: 'line-awesome/svg/merge-solid.svg' },
  title: 'Merge Models',
};

/**
 * {@link MergeModelViewState}
 * <p>
 *   Interface for the state of the MergeModelView component.
 * </p>
 */
interface MergeModelViewState {
  modelPath1: string;
  modelPath2: string;
  dialogOpened: boolean;
}

/**
 * {@link MergeModelView}
 * <p>
 *   This component allows users to merge two models by providing their paths.
 *   It includes input fields for the model paths and a button to trigger the merge.
 *   A dialog is shown to confirm the merge action.
 * </p>
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

  /**
   * {@link handleMerge}
   * <p>
   *   This function handles the merge action by calling the MergeDestroyModelService.
   *   It shows a notification with the response and closes the dialog.
   * </p>
   */
  handleMerge = async () => {
    const { modelPath1, modelPath2 } = this.state;
    const response = await MergeDestroyModelService.mergeModels(modelPath1, modelPath2);
    Notification.show(response);
    this.setState({ dialogOpened: false });
  };

  /**
   * {@link openDialog}
   * <p>
   *   This function opens the confirmation dialog.
   * </p>
   */
  openDialog = () => {
    this.setState({ dialogOpened: true });
  };

  /**
   * {@link closeDialog}
   * <p>
   *   This function closes the confirmation dialog.
   * </p>
   */
  closeDialog = () => {
    this.setState({ dialogOpened: false });
  };

  /**
   * {@link handleInputChange1}
   * <p>
   *   This function updates the state with the value of the first model path input field.
   * </p>
   */
  handleInputChange1 = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ modelPath1: e.target.value });
  };

  /**
   * {@link handleInputChange2}
   * <p>
   *   This function updates the state with the value of the second model path input field.
   * </p>
   */
  handleInputChange2 = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ modelPath2: e.target.value });
  };

  /**
   * {@link render}
   * <p>
   *   This function renders the component, including input fields for model paths,
   *   a button to trigger the merge, and a confirmation dialog.
   * </p>
   */
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
