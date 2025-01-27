import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 15, icon: 'line-awesome/svg/cog-solid.svg' },
  title: 'System Settings'
};

interface SystemSettingsViewProps {}

interface SystemSettingsViewState {
  name: string;
}

/**
 * <h1>{@link SystemSettingsView}</h1>
 */
class SystemSettingsView extends Component<SystemSettingsViewProps, SystemSettingsViewState> {
  constructor(props: SystemSettingsViewProps) {
    super(props);
    this.state = {
      name: ''
    };
  }

  handleNameChange = (e: CustomEvent) => {
    this.setState({ name: e.detail.value });
  };

  handleButtonClick = async () => {
    const serverResponse = await HelloWorldService.sayHello(this.state.name);
    Notification.show(serverResponse);
  };

  render() {
    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Your name"
            onValueChanged={this.handleNameChange}
          />
          <Button onClick={this.handleButtonClick}>
            Say hello
          </Button>
        </section>
      </>
    );
  }
}

export default SystemSettingsView;
