import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useState } from 'react';
import { Button, Dialog, Notification } from '@vaadin/react-components';
// @ts-ignore
import { ServersService } from 'Frontend/generated/endpoints';
export const config = {
    menu: { order: 13, icon: 'line-awesome/svg/server-solid.svg', title: 'Servers' }
};
const ManageServersView = () => {
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
    const startServer = async () => {
        const result = await ServersService.manageServer('start');
        Notification.show("Started server" + result);
    };
    const stopServer = async () => {
        const result = await ServersService.manageServer('stop');
        Notification.show("Server stopped" + result);
    };
    const restartServer = async () => {
        const result = await ServersService.manageServer('restart');
        Notification.show("Restarted server" + result);
    };
    return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => openDialog('Are you sure you want to start the server?', startServer), style: { backgroundColor: 'green' }, children: "Start Server" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to stop the server?', stopServer), style: { backgroundColor: 'red' }, children: "Stop Server" }), _jsx(Button, { onClick: () => openDialog('Are you sure you want to restart the server?', restartServer), style: { backgroundColor: 'blue' }, children: "Restart Server" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => setDialogOpened(e.detail.value), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => {
                                        dialogAction();
                                        handleDialogClose();
                                    }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: handleDialogClose, children: "No" })] })] }) })] }));
};
export default ManageServersView;
//# sourceMappingURL=servers.js.map