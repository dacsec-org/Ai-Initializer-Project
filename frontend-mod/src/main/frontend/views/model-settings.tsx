import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 7, icon: 'line-awesome/svg/robot-solid.svg' },
  title: 'Model Settings',
};

interface ModelSettingsState {
  name: string;
}

class ModelSettings extends Component<{}, ModelSettingsState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      name: ''
    };
  }

  handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
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
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Your name"
            value={name}
            onValueChanged={(e) => this.handleInputChange(e)}
          />
          <Button onClick={this.handleButtonClick}>
            Say hello
          </Button>
        </section>
      </>
    );
  }
}

export default ModelSettings;
