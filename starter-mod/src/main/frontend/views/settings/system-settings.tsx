import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Notification, Select } from '@vaadin/react-components';
import { SystemSettings } from 'Frontend/views/settings/SystemSettings';
import { SystemSettingsOptions } from 'Frontend/enums/SystemSettingsOptions';

export const config: ViewConfig = {
  menu: { order: 15, icon: 'line-awesome/svg/cog-solid.svg' },
  title: 'System Settings'
};

const SystemSettingsView: React.FC = () => {
  const [selectedOption, setSelectedOption] = useState<SystemSettingsOptions | undefined>(undefined);

  const handleOptionChange = (e: CustomEvent) => {
    setSelectedOption(e.detail.value as SystemSettingsOptions);
  };

  const handleButtonClick = async () => {
    if (selectedOption === undefined) {
      Notification.show('Please select an option');
      return;
    }

    try {
      const serverResponse = await SystemSettings.processSettings(selectedOption);
      Notification.show(serverResponse);
    } catch (error) {
      Notification.show('Error processing settings');
    }
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <Select
          label="Select Option"
          items={Object.values(SystemSettingsOptions).map(option => ({ label: option.toString(), value: option.toString() }))}
          onValueChanged={handleOptionChange}
        />
        <Button onClick={handleButtonClick}>
          Process Settings
        </Button>
      </section>
    </>
  );
};

export default SystemSettingsView;
