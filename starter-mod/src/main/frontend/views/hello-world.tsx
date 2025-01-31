import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/smile-solid.svg' },
  title: 'Hello World'
};

const HelloWorldView: React.FC = () => {
  const [name, setName] = useState('');

  const handleNameChange = (e: CustomEvent) => {
    setName(e.detail.value);
  };

  const handleButtonClick = async () => {
    try {
      const serverResponse = await HelloWorldService.sayHello(name);
      Notification.show(serverResponse);
    } catch (error: any) {
      Notification.show('Error: ' + (error as Error).message);
    }
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Your name"
          value={name}
          onValueChanged={handleNameChange}
        />
        <Button onClick={handleButtonClick}>
          Say hello
        </Button>
      </section>
    </>
  );
};

export default HelloWorldView;
