import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { Models } from './Models';
import { ModelActions } from 'Frontend/enums/ModelActions';
export const config = {
    menu: { order: 10, icon: 'line-awesome/svg/robot-solid.svg' },
    title: 'Model Settings',
};
const ModelSettingsView = () => {
    const [name, setName] = useState('');
    const handleInputChange = (e) => {
        setName(e.target.value);
    };
    const handleButtonClick = async () => {
        const serverResponse = await Models.getModels(ModelActions.SETTINGS, name, '');
        Notification.show(serverResponse);
    };
    return (_jsx(_Fragment, { children: _jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Your name", value: name, onValueChanged: (e) => handleInputChange(e) }), _jsx(Button, { onClick: handleButtonClick, children: "Say hello" })] }) }));
};
export default ModelSettingsView;
//# sourceMappingURL=model-settings.js.map