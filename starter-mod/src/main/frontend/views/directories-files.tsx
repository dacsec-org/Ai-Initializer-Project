import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, Dialog } from '@vaadin/react-components';
import { DirFileService } from 'Frontend/generated/endpoints';
import CustomTextFields from '../views/components/custom-textfields';

/**
 * {@link #config}
 * <p>
 *   Order and icon for the side navigation menu.
 * </p>
 */
export const config: ViewConfig = {
  menu: { order: 5, icon: 'line-awesome/svg/folder-open-solid.svg' },
  title: 'Directories and Files',
};

/**
 * {@link DirFileViewState}
 * <p>
 *   The state of the DirFileView component.
 *   The state includes the path, fileName, dialogOpened, and operation.
 * </p>
 */
interface DirFileViewState {
  path: string;
  fileName: string;
  dialogOpened: boolean;
  operation: string;
}

/**
 * {@link DirFileView}
 * <p>
 *   This class handles the UI operations for directories and files.
 * </p>
 */
class DirFileView extends Component<{}, DirFileViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      path: '',
      fileName: '',
      dialogOpened: false,
      operation: '',
    };
  }

  /**
   * {@link handleOperation}
   * <p>
   *   This method handles the operation for the directories and files.
   *   It calls the handleOperation method from the DirFileService with the operation, path, and fileName.
   *   It then displays the response as a notification.
   * </p>
   */
  handleOperation = async () => {
    const { path, fileName, operation } = this.state;
    const response = await DirFileService.handleOperation(operation, path, fileName);
    Notification.show(response);
    this.setState({ dialogOpened: false });
  };

  /**
   * {@link openDialog}
   * <p>
   *   This method opens the dialog for the operation.
   * </p>
   */
  openDialog = (operation: string) => {
    this.setState({ dialogOpened: true, operation });
  };

  /**
   * {@link closeDialog}
   * <p>
   *   This method closes the dialog.
   * </p>
   */
  closeDialog = () => {
    this.setState({ dialogOpened: false });
  };

  /**
   * {@link handleInputChange}
   * <p>
   *   This method handles the input change for the path and fileName.
   * </p>
   */
  handleInputChange = (e: React.ChangeEvent<HTMLInputElement>, field: string) => {
    this.setState({ [field]: e.target.value } as Pick<DirFileViewState, keyof DirFileViewState>);
  };

  /**
   * {@link render}
   * <p>
   *   This method renders the directories and files view.
   * </p>
   */
  render() {
    const { path, fileName, dialogOpened, operation } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <CustomTextFields renderTextField={3} />
          <CustomTextFields renderTextField={4} />
          <Button onClick={() => this.openDialog('CREATE_DIRECTORY')}>Create Directory</Button>
          <Button onClick={() => this.openDialog('CREATE_FILE')}>Create File</Button>
          <Button onClick={() => this.openDialog('DELETE_DIRECTORY')}>Delete Directory</Button>
          <Button onClick={() => this.openDialog('DELETE_FILE')}>Delete File</Button>
        </section>
        <Dialog opened={dialogOpened} onOpenedChanged={(e) => this.setState({ dialogOpened: e.detail.value })}>
          <div>
            <p>Are you sure you want to {operation.replace('_', ' ').toLowerCase()}?</p>
            <div className="flex gap-s">
              <Button theme="primary error" onClick={this.handleOperation}>
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

export default DirFileView;
