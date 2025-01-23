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
  menu: { order: 4, icon: 'line-awesome/svg/trash-alt-solid.svg' },
  title: 'Delete Model',
};

/**
 * {@link DeleteModelViewState}
 * <p>
 *   Interface for the state of the DeleteModelView component.
 * </p>
 */
interface DeleteModelViewState {
  modelPath: string;
  dialogOpened: boolean;
}

/**
 * {@link DeleteModelView}
 * <p>
 *   This component allows users to delete a model by providing its path.
 *   It includes an input field for the model path and a button to trigger the deletion.
 *   A dialog is shown to confirm the deletion action.
 * </p>
 */
class DeleteModelView extends Component<{}, DeleteModelViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      modelPath: '',
      dialogOpened: false
    };
  }

  /**
   * {@link handleDelete}
   * <p>
   *   This function handles the delete action by calling the MergeDestroyModelService.
   *   It shows a notification with the response and closes the dialog.
   * </p>
   */
  handleDelete = async () => {
    const { modelPath } = this.state;
    const response = await MergeDestroyModelService.deleteModel(modelPath);
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
   * {@link handleInputChange}
   * <p>
   *   This function updates the state with the value of the model path input field.
   * </p>
   */
  handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ modelPath: e.target.value });
  };

  /**
   * {@link render}
   * <p>
   *   This function renders the component, including an input field for the model path,
   *   a button to trigger the deletion, and a confirmation dialog.
   * </p>
   */
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

export default DeleteModelView;
