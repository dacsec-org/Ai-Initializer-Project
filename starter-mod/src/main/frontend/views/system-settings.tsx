import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: { order: 15, icon: 'line-awesome/svg/cog-solid.svg' },
  title: 'System Settings'
};

const SystemSettingsView: React.FC = () => {
  const [name, setName] = useState('');

  const handleNameChange = (e: CustomEvent) => {
    setName(e.detail.value);
  };

  const handleButtonClick = async () => {
    const serverResponse = await HelloWorldService.sayHello(name);
    Notification.show(serverResponse);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Your name"
          onValueChanged={handleNameChange}
        />
        <Button onClick={handleButtonClick}>
          Say hello
        </Button>
      </section>
    </>
  );
};

export default SystemSettingsView;
