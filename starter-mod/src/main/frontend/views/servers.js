import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Dialog, Notification } from '@vaadin/react-components';
import { ServersService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 13, icon: 'line-awesome/svg/solid/server.svg' },
  title: 'Servers'
};

class ManageServersView extends Component {
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

  startServer = async () => {
    const result = await ServersService.manageServer('start');
    Notification.show("Started server" + result);
  };

  stopServer = async () => {
    const result = await ServersService.manageServer('stop');
    Notification.show("Server stopped" + result);
  };

  restartServer = async () => {
    const result = await ServersService.manageServer('restart');
    Notification.show("Restarted server" + result);
  };

  render() {
    const { dialogOpened, dialogMessage, dialogAction } = this.state;

    return (
      _jsxs(React.Fragment, {
        children: [
          _jsxs("section", {
            className: "flex p-m gap-m items-end",
            children: [
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to start the server?', this.startServer),
                style: { backgroundColor: 'green' },
                children: "Start Server"
              }),
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to stop the server?', this.stopServer),
                style: { backgroundColor: 'red' },
                children: "Stop Server"
              }),
              _jsx(Button, {
                onClick: () => this.openDialog('Are you sure you want to restart the server?', this.restartServer),
                style: { backgroundColor: 'blue' },
                children: "Restart Server"
              })
            ]
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

export default ManageServersView;
//# sourceMappingURL=servers.js.map
