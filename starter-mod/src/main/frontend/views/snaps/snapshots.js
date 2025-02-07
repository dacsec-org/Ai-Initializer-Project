import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Notification } from '@vaadin/react-components';
// @ts-ignore
import { SnapShotsService } from 'Frontend/generated/endpoints';
export const config = {
    menu: { order: 14, icon: 'line-awesome/svg/camera-solid.svg' },
    title: 'Snapshots'
};
const SnapshotsView = () => {
    const [snapshots, setSnapshots] = useState([]);
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
    const listSnapshots = async () => {
        const response = await SnapShotsService.manageSnapshots('list', '', '');
        Notification.show("SnapshotsView listed successfully" + response);
    };
    const copySnapshot = async () => {
        const response = await SnapShotsService.manageSnapshots('copy', '', '');
        Notification.show('Snapshot copied successfully' + response);
    };
    const createSnapshot = async () => {
        const response = await SnapShotsService.manageSnapshots('create', '', '');
        Notification.show('Snapshot created successfully' + response);
    };
    const deleteSnapshot = async () => {
        const response = await SnapShotsService.manageSnapshots('delete', '', '');
        Notification.show('Snapshot deleted successfully' + response);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => openDialog('Are you sure you want to create a snapshot?', createSnapshot), style: { backgroundColor: 'green' }, children: "Create Snapshot" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to list snapshots?', listSnapshots), style: { backgroundColor: 'blue' }, children: "List SnapshotsView" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to update the snapshot?', copySnapshot), style: { backgroundColor: 'yellow' }, children: "Update Snapshot" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to delete the snapshot?', deleteSnapshot), style: { backgroundColor: 'red' }, children: "Delete Snapshot" })] }), _jsx("ul", { children: snapshots.map((snapshot) => (_jsxs("li", { children: [snapshot, _jsx(Button, { onClick: () => openDialog('Are you sure you want to delete this snapshot?', () => deleteSnapshot()), style: { backgroundColor: 'red' }, children: "Delete" })] }, snapshot))) }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => {
                                        dialogAction();
                                        handleDialogClose();
                                    }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: handleDialogClose, children: "No" })] })] }) })] }));
};
export default SnapshotsView;
//# sourceMappingURL=snapshots.js.map