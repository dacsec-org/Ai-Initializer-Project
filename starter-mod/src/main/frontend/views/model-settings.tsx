import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/robot-solid.svg' },
  title: 'Model Settings',
};

interface ModelSettingsState {
  name: string;
}

/**
 * <h1>{@link ModelSettingsView}</h1>
 */
class ModelSettingsView extends Component<{}, ModelSettingsState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      name: ''
    };
  }

  handleInputChange = (T: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ name: T.target.value });
  };

  handleButtonClick = async () => {
    const { name } = this.state;
    const serverResponse = await HelloWorldService.sayHello(name);
    Notification.show(serverResponse);
  };

  render() {
    const { name } = this.state;

    return <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Your name"
          value={name}
          onValueChanged={(T) => {
            // @ts-ignore
            this.handleInputChange(T);
            //todo: fix above line
          }}
        />
        <Button onClick={this.handleButtonClick}>
          Say hello
        </Button>
      </section>
    </>;
  }
}

export default ModelSettingsView;
