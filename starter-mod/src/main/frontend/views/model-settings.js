import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints.js';

export const config = {
  menu: { order: 10, icon: 'line-awesome/svg/robot-solid.svg' },
  title: 'Model Settings',
};

class ModelSettingsView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  handleInputChange = (e) => {
    this.setState({ name: e.target.value });
  };

  handleButtonClick = async () => {
    const { name } = this.state;
    const serverResponse = await HelloWorldService.sayHello(name);
    Notification.show(serverResponse);
  };

  render() {
    const { name } = this.state;

    return (
      _jsxs("section", {
        className: "flex p-m gap-m items-end",
        children: [
          _jsx(TextField, {
            label: "Your name",
            value: name,
            onValueChanged: (e) => this.handleInputChange(e)
          }),
          _jsx(Button, {
            onClick: this.handleButtonClick,
            children: "Say hello"
          })
        ]
      })
    );
  }
}

export default ModelSettingsView;
//# sourceMappingURL=model-settings.js.map
