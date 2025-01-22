import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Dialog } from '@vaadin/react-components';
import { SnapShotService } from 'Frontend/generated/endpoints.js';
export const config = {
    menu: { order: 11, icon: 'line-awesome/svg/solid/camera.svg' },
    title: 'Snapshots',
};
class Snapshots extends Component {
    constructor(props) {
        super(props);
        this.openDialog = (message, action) => {
            this.setState({
                dialogMessage: message,
                dialogAction: action,
                dialogOpened: true,
            });
        };
        this.handleDialogClose = () => {
            this.setState({ dialogOpened: false });
        };
        this.listSnapshots = async () => {
            const snapshots = await SnapShotService.listSnapshots();
            this.setState({ snapshots });
        };
        this.copySnapshot = async () => {
            const snapshots = await SnapShotService.updateSnapshot();
            this.setState({ snapshots });
        };
        this.createSnapshot = async () => {
            const snapshots = await SnapShotService.createSnapshot();
            this.setState({ snapshots });
        };
        this.deleteSnapshot = async () => {
            const snapshots = await SnapShotService.deleteSnapshot();
            this.setState({ snapshots });
        };
        this.state = {
            snapshots: [],
            dialogOpened: false,
            dialogMessage: '',
            dialogAction: () => { },
        };
    }
    render() {
        const { snapshots, dialogOpened, dialogMessage, dialogAction } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(Button, { onClick: () => this.openDialog('Are you sure you want to create a snapshot?', this.createSnapshot), style: { backgroundColor: 'green' }, children: "Create Snapshot" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to list snapshots?', this.listSnapshots), style: { backgroundColor: 'blue' }, children: "List Snapshots" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to update the snapshot?', this.copySnapshot), style: { backgroundColor: 'yellow' }, children: "Update Snapshot" }), _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to delete the snapshot?', this.deleteSnapshot), style: { backgroundColor: 'red' }, children: "Delete Snapshot" })] }), _jsx("ul", { children: snapshots.map((snapshot) => (_jsxs("li", { children: [snapshot, _jsx(Button, { onClick: () => this.openDialog('Are you sure you want to delete this snapshot?', () => this.deleteSnapshot()), style: { backgroundColor: 'red' }, children: "Delete" })] }, snapshot))) }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsx("p", { children: dialogMessage }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: () => { dialogAction(); this.handleDialogClose(); }, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.handleDialogClose, children: "No" })] })] }) })] }));
    }
}
export default SnapshotsView;
//# sourceMappingURL=snapshots-view.js.map
