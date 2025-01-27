import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 15, icon: 'line-awesome/svg/cog-solid.svg' },
  title: 'System Settings'
};

class SystemSettingsView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  handleNameChange = (e) => {
    this.setState({ name: e.detail.value });
  };

  handleButtonClick = async () => {
    const serverResponse = await HelloWorldService.sayHello(this.state.name);
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
            onValueChanged: this.handleNameChange
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

export default SystemSettingsView;
//# sourceMappingURL=system-settings.js.map
