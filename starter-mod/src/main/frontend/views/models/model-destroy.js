import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Notification, TextField } from '@vaadin/react-components';
import { Models } from './Models';
import { ModelActions } from 'Frontend/enums/ModelActions';
export const config = {
    menu: { order: 8, icon: 'line-awesome/svg/trash-alt-solid.svg' },
    title: 'Delete ~ Model',
};
const DestroyModelView = () => {
    const [modelPath, setModelPath] = useState('');
    const [dialogOpened, setDialogOpened] = useState(false);
    const handleDelete = async () => {
        const response = await Models.getModels(ModelActions.DESTROY, modelPath, '');
        Notification.show("Model destroyed: " + response);
        setDialogOpened(false);
    };
    const openDialog = () => {
        setDialogOpened(true);
    };
    const closeDialog = () => {
        setDialogOpened(false);
    };
    const handleInputChange = (e) => {
        setModelPath(e.detail.value);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Model Path", value: modelPath, onValueChanged: handleInputChange }), _jsx(Button, { onClick: openDialog, children: "Delete Model" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: "Are you sure you want to delete this model? Any work associated with this model will be deleted as well!" }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary error", onClick: handleDelete, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: closeDialog, children: "No" })] })] }) })] }));
};
export default DestroyModelView;
//# sourceMappingURL=model-destroy.js.map