import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Notification } from '@vaadin/notification';
import { LoadUnloadService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 5, icon: 'line-awesome/svg/upload-solid.svg' },
  title: 'Load ~ Unload',
};

class LoadUnloadView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      modelData: null,
    };
  }

  loadModel = async (modelPath) => {
    const modelData = new Uint8Array();
    await LoadUnloadService.loadUnloadLLM("load", modelPath, Array.from(modelData));
    Notification.show('Model loaded successfully');
    this.setState({ modelData });
  };

  unloadModel = async () => {
    const modelData = new Uint8Array();
    await LoadUnloadService.loadUnloadLLM("unload", "", Array.from(modelData));
    Notification.show('Model unloaded successfully');
    this.setState({ modelData: null });
  };

  render() {
    const { modelData } = this.state;

    return (
      _jsxs("div", {
        children: [
          _jsx("button", {
            onClick: () => this.loadModel('path/to/model'),
            children: "Load Model"
          }),
          _jsx("button", {
            onClick: this.unloadModel,
            disabled: !modelData,
            children: "Unload Model"
          })
        ]
      })
    );
  }
}

export default LoadUnloadView;
