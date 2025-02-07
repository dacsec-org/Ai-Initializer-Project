import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Notification, Dialog } from '@vaadin/react-components';
// @ts-ignore
import { DirFileService } from 'Frontend/generated/endpoints';
import CustomTextFields from '../components/custom-textfields';
export const config = {
    menu: { order: 4, icon: 'line-awesome/svg/folder-open-solid.svg' }, title: 'Directories ~ Files'
};
const DirFileView = () => {
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
    const createDirectory = async () => {
        const response = await DirFileService.processDirFileAction('create_directory', '', '');
        Notification.show("Directory created successfully: " + response);
        setDialogOpened(false);
    };
    const createFile = async () => {
        const response = await DirFileService.processDirFileAction('create_file', '', '');
        Notification.show("File created successfully: " + response);
        setDialogOpened(false);
    };
    const deleteDirectory = async () => {
        const response = await DirFileService.processDirFileAction('delete_directory', '', '');
        Notification.show("Directory deleted successfully: " + response);
        setDialogOpened(false);
    };
    const deleteFile = async () => {
        const response = await DirFileService.processDirFileAction('delete_file', '', '');
        Notification.show("File deleted successfully: " + response);
        setDialogOpened(false);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(CustomTextFields, { renderTextField: 3 }), _jsx(CustomTextFields, { renderTextField: 4 }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a directory?', createDirectory), style: { backgroundColor: 'green' }, children: "Create Directory" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to create a file?', createFile), style: { backgroundColor: 'blue' }, children: "Create File" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to delete the directory?', deleteDirectory), style: { backgroundColor: 'red' }, children: "Delete Directory" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to delete the file?', deleteFile), style: { backgroundColor: 'orange' }, children: "Delete File" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => {
                                        dialogAction();
                                        handleDialogClose();
                                    }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: handleDialogClose, children: "No" })] })] }) })] }));
};
export default DirFileView;
//# sourceMappingURL=directories-files.js.map