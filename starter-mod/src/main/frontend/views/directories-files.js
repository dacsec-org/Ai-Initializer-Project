import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, Dialog } from '@vaadin/react-components';
import { DirFileService } from 'Frontend/generated/endpoints';
import CustomTextFields from '../views/components/custom-textfields';

export const config = {
  menu: { order: 4, icon: 'line-awesome/svg/folder-open-solid.svg' },
  title: 'Directories ~ Files',
};

class DirFileView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      dialogOpened: false,
      dialogMessage: '',
      dialogAction: () => {}
    };
  }

  openDialog = (message, action) => {
    this.setState({
      dialogMessage: message,
      dialogAction: action,
      dialogOpened: true
    });
  };

  handleDialogClose = () => {
    this.setState({ dialogOpened: false });
  };

  createDirectory = async () => {
    const response = await DirFileService.processDirFileAction('create_directory', '', '');
    Notification.show("Directory created successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  createFile = async () => {
    const response = await DirFileService.processDirFileAction('create_file', '', '');
    Notification.show("File created successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  deleteDirectory = async () => {
    const response = await DirFileService.processDirFileAction('delete_directory', '', '');
    Notification.show("Directory deleted successfully: " + response);
    this.setState({ dialogOpened: false });
  };

  deleteFile = async () => {
    const response = await DirFileService.processDirFileAction('delete_file', '', '');
    Notification.show("File deleted successfully: " + response);
    this.setState({ dialogOpened: false });
  };

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
