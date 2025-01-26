import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, Dialog } from '@vaadin/react-components';
import { DirFileService } from 'Frontend/generated/endpoints';
import CustomTextFields from '../views/components/custom-textfields';

/**
 * <h3>{@link ViewConfig}</h3>
 */
export const config: ViewConfig = {
  menu: { order: 4, icon: 'line-awesome/svg/folder-open-solid.svg' },
  title: 'Directories ~ Files',
};

/**
 * <h3>{@link DirFileViewState}</h3>
 */
interface DirFileViewState {
  dialogOpened: boolean;
  dialogMessage: string;
  dialogAction: () => void;
}

/**
 * <h1>{@link DirFileView}</h1>
 */
class DirFileView extends Component<{}, DirFileViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
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
   * <h3>{@link createDirectory}</h3>
   */
  createDirectory = async () => {
    const response = await DirFileService.processDirFileAction('create_directory', '', '');
    Notification.show("Directory created successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  /**
   * <h3>{@link createFile}</h3>
   */
  createFile = async () => {
    const response = await DirFileService.processDirFileAction('create_file', '', '');
    Notification.show("File created successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  /**
   * <h3>{@link deleteDirectory}</h3>
   */
  deleteDirectory = async () => {
    const response = await DirFileService.processDirFileAction('delete_directory', '', '');
    Notification.show("Directory deleted successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  /**
   * <h3>{@link deleteFile}</h3>
   */
  deleteFile = async () => {
    const response = await DirFileService.processDirFileAction('delete_file', '', '');
    Notification.show("File deleted successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  /**
   * <h3>{@link render}</h3>
   */
  render() {
    const { dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <CustomTextFields renderTextField={3} />
          <CustomTextFields renderTextField={4} />
          <Button
            onClick={() => this.openDialog('Are you sure you want to create a directory?', this.createDirectory)}
            style={{ backgroundColor: 'green' }}>Create Directory</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to create a file?', this.createFile)}
            style={{ backgroundColor: 'blue' }}>Create File</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to delete the directory?', this.deleteDirectory)}
            style={{ backgroundColor: 'red' }}>Delete Directory</Button>
          <Button
            onClick={() => this.openDialog('Are you sure you want to delete the file?', this.deleteFile)}
            style={{ backgroundColor: 'orange' }}>Delete File</Button>
        </section>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
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

export default DirFileView;
