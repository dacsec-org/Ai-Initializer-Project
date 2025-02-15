import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { Models } from 'Frontend/bridges/Models';
import { ModelActions } from 'Frontend/enums/ModelActions';

export const config: ViewConfig = {
  menu: { order: 10, icon: 'line-awesome/svg/robot-solid.svg' },
  title: 'Model Settings',
};

const ModelSettingsView: React.FC = () => {
  const [name, setName] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  const handleButtonClick = async () => {
    const serverResponse = await Models.getModels(ModelActions.SETTINGS, name, '');
    Notification.show(serverResponse);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Your name"
          value={name}
          onValueChanged={(e) => handleInputChange(e as any)}
        />
        <Button onClick={handleButtonClick}>
          Say hello
        </Button>
      </section>
    </>
  );
};

export default ModelSettingsView;
