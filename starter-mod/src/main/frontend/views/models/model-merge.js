import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Notification, TextField } from '@vaadin/react-components';
import { Models } from './Models';
import { ModelActions } from 'Frontend/enums/ModelActions';
export const config = {
    menu: { order: 9, icon: 'line-awesome/svg/arrows-alt-h-solid.svg' },
    title: 'Merge Model'
};
const MergeModelView = () => {
    const [modelPath1, setModelPath1] = useState('');
    const [modelPath2, setModelPath2] = useState('');
    const [dialogOpened, setDialogOpened] = useState(false);
    const handleMerge = async () => {
        const response = await Models.getModels(ModelActions.MERGE, modelPath1, modelPath2);
        Notification.show("Merged models: " + response);
        setDialogOpened(false);
    };
    const openDialog = () => {
        setDialogOpened(true);
    };
    const closeDialog = () => {
        setDialogOpened(false);
    };
    const handleInputChange1 = (e) => {
        setModelPath1(e.detail.value);
    };
    const handleInputChange2 = (e) => {
        setModelPath2(e.detail.value);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Model Path 1", value: modelPath1, onValueChanged: handleInputChange1 }), _jsx(TextField, { label: "Model Path 2", value: modelPath2, onValueChanged: handleInputChange2 }), _jsx(Button, { onClick: openDialog, children: "Merge Models" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: "Are you sure you want to merge these models?" }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: handleMerge, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: closeDialog, children: "No" })] })] }) })] }));
};
export default MergeModelView;
//# sourceMappingURL=model-merge.js.map