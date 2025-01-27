import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/smile-solid.svg' },
  title: 'Hello World'
};

interface HelloWorldViewProps {}

interface HelloWorldViewState {
  name: string;
}

/**
 * <h1>{@link HelloWorldView}</h1>
 */
class HelloWorldView extends Component<HelloWorldViewProps, HelloWorldViewState> {
  constructor(props: HelloWorldViewProps) {
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

export default HelloWorldView;
