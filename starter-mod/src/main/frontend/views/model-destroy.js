import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 8, icon: 'line-awesome/svg/trash-alt-solid.svg' },
  title: 'Delete ~ Model',
};

class DestroyModelView extends Component {
  constructor(props) {
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

  handleInputChange = (e) => {
    this.setState({ modelPath: e.detail.value });
  };

  render() {
    const { modelPath, dialogOpened } = this.state;

    return (
      _jsxs(React.Fragment, {
        children: [
          _jsxs("section", {
            className: "flex p-m gap-m items-end",
            children: [
              _jsx(TextField, {
                label: "Model Path",
                value: modelPath,
                onValueChanged: this.handleInputChange
              }),
              _jsx(Button, {
                onClick: this.openDialog,
                children: "Delete Model"
              })
            ]
          }),
          _jsx(Dialog, {
            opened: dialogOpened,
            onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }),
            children: _jsxs("div", {
              children: [
                _jsx("p", {
                  children: "Are you sure you want to delete this model? Any work associated with this model will be deleted as well!"
                }),
                _jsxs("div", {
                  className: "flex gap-s",
                  children: [
                    _jsx(Button, {
                      theme: "primary error",
                      onClick: this.handleDelete,
                      children: "Yes"
                    }),
                    _jsx(Button, {
                      theme: "secondary",
                      onClick: this.closeDialog,
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

export default DestroyModelView;
