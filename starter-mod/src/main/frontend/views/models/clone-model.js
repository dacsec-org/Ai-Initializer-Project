import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Notification, TextField } from '@vaadin/react-components';
import { Models } from './Models';
import { ModelActions } from 'Frontend/enums/ModelActions';
export const config = {
    menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
    title: 'Clone',
};
const CloneModelView = () => {
    const [sourcePath, setSourcePath] = useState('');
    const [dialogOpened, setDialogOpened] = useState(false);
    const [dialogMessage, setDialogMessage] = useState('');
    const [dialogAction, setDialogAction] = useState(() => { });
    const openDialog = (message, action) => {
        setDialogMessage(message);
        setDialogAction(() => action);
        setDialogOpened(true);
    };
    const handleDialogClose = () => {
        setDialogOpened(false);
    };
    const handleClone = async () => {
        const response = await Models.getModels(ModelActions.CLONE, sourcePath, '');
        Notification.show("Cloning successful: " + response);
        setDialogOpened(false);
    };
    const handleInputChange = (e) => {
        setSourcePath(e.detail.value);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Source Path", value: sourcePath, onValueChanged: handleInputChange }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to clone this model?', handleClone), style: { backgroundColor: 'blue' }, children: "Clone Model" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => {
                                        dialogAction();
                                        handleDialogClose();
                                    }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: handleDialogClose, children: "No" })] })] }) })] }));
};
export default CloneModelView;
//# sourceMappingURL=clone-model.js.map