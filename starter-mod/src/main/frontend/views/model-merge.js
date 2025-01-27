import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 9, icon: 'line-awesome/svg/merge-solid.svg' },
  title: 'Merge Model',
};

class MergeModelView extends Component {
  constructor(props) {
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

  handleInputChange1 = (e) => {
    this.setState({ modelPath1: e.detail.value });
  };

  handleInputChange2 = (e) => {
    this.setState({ modelPath2: e.detail.value });
  };

  render() {
    const { modelPath1, modelPath2, dialogOpened } = this.state;

    return (
      _jsxs(React.Fragment, {
        children: [
          _jsxs("section", {
            className: "flex p-m gap-m items-end",
            children: [
              _jsx(TextField, {
                label: "Model Path 1",
                value: modelPath1,
                onValueChanged: this.handleInputChange1
              }),
              _jsx(TextField, {
                label: "Model Path 2",
                value: modelPath2,
                onValueChanged: this.handleInputChange2
              }),
              _jsx(Button, {
                onClick: this.openDialog,
                children: "Merge Models"
              })
            ]
          }),
          _jsx(Dialog, {
            opened: dialogOpened,
            onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }),
            children: _jsxs("div", {
              children: [
                _jsx("p", {
                  children: "Are you sure you want to merge these models?"
                }),
                _jsxs("div", {
                  className: "flex gap-s",
                  children: [
                    _jsx(Button, {
                      theme: "primary",
                      onClick: this.handleMerge,
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

export default MergeModelView;
