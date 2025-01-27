import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Dialog, Notification } from '@vaadin/react-components';
import { SnapShotsService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 14, icon: 'line-awesome/svg/solid/camera.svg' },
  title: 'Snapshots'
};

class SnapshotsView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      snapshots: [],
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

  listSnapshots = async () => {
    const response = await SnapShotsService.manageSnapshots('list', '', '');
    Notification.show("SnapshotsView listed successfully" + response);
  };

  copySnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('copy', '', '');
    Notification.show('Snapshot copied successfully' + response);
  };

  createSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('create', '', '');
    Notification.show('Snapshot created successfully' + response);
  };

  deleteSnapshot = async () => {
    const response = await SnapShotsService.manageSnapshots('delete', '', '');
    Notification.show('Snapshot deleted successfully' + response);
  };

  render() {
    const { snapshots, dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      _jsxs(React.Fragment, {
        children: [
          _jsxs("section", {
            className: "flex p-m gap-m items-end",
            children: [
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to create a snapshot?', this.createSnapshot),
                style: { backgroundColor: 'green' },
                children: "Create Snapshot"
              }),
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to list snapshots?', this.listSnapshots),
                style: { backgroundColor: 'blue' },
                children: "List SnapshotsView"
              }),
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to update the snapshot?', this.copySnapshot),
                style: { backgroundColor: 'yellow' },
                children: "Update Snapshot"
              }),
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to delete the snapshot?', this.deleteSnapshot),
                style: { backgroundColor: 'red' },
                children: "Delete Snapshot"
              })
            ]
          }),
          _jsx("ul", {
            children: snapshots.map((snapshot) => (
              _jsxs("li", {
                children: [
                  snapshot,
                  _jsx(Button, {
                    onClick: () => this.openDialog('Are you sure you want to delete this snapshot?', () => this.deleteSnapshot()),
                    style: { backgroundColor: 'red' },
                    children: "Delete"
                  })
                ]
              }, snapshot)
            ))
          }),
          _jsx(Dialog, {
            opened: dialogOpened,
            onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }),
            children: _jsxs("div", {
              children: [
                _jsx("p", { children: dialogMessage }),
                _jsxs("div", {
                  className: "flex gap-s",
                  children: [
                    _jsx(Button, {
                      theme: "primary",
                      onClick: () => {
                        dialogAction();
                        this.handleDialogClose();
                      },
                      children: "Yes"
                    }),
                    _jsx(Button, {
                      theme: "secondary",
                      onClick: this.handleDialogClose,
                      children: "No"
                    })
                  ]
                })
              ]
            })
          })
        ]
      })
    );
  }
}

export default SnapshotsView;
//# sourceMappingURL=snapshots.js.map
