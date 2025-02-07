import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Notification, Select } from '@vaadin/react-components';
import { SystemSettings } from 'Frontend/views/settings/SystemSettings';
import { SystemSettingsOptions } from 'Frontend/enums/SystemSettingsOptions';
export const config = {
    menu: { order: 15, icon: 'line-awesome/svg/cog-solid.svg' },
    title: 'System Settings'
};
const SystemSettingsView = () => {
    const [selectedOption, setSelectedOption] = useState(undefined);
    const handleOptionChange = (e) => {
        setSelectedOption(e.detail.value);
    };
    const handleButtonClick = async () => {
        if (selectedOption === undefined) {
            Notification.show('Please select an option');
            return;
        }
        try {
            const serverResponse = await SystemSettings.processSettings(selectedOption);
            Notification.show(serverResponse);
        }
        catch (error) {
            Notification.show('Error processing settings');
        }
    };
    return (_jsx(_Fragment, { children: _jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Select, { label: "Select Option", items: Object.values(SystemSettingsOptions).map(option => ({ label: option.toString(), value: option.toString() })), onValueChanged: handleOptionChange }), _jsx(Button, { onClick: handleButtonClick, children: "Process Settings" })] }) }));
};
export default SystemSettingsView;
//# sourceMappingURL=system-settings.js.map