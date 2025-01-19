import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { CloneLocalModelService } from 'Frontend/generated/endpoints.js';
export const config = {
    menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
    title: 'Clone Model',
};
class CloneModelView extends Component {
    constructor(props) {
        super(props);
        this.handleClone = async () => {
            const { sourcePath, snapshotPath } = this.state;
            const response = await CloneLocalModelService.cloneModel(sourcePath, snapshotPath);
            Notification.show(response);
        };
        this.handleInputChange = (e) => {
            this.setState({ [e.target.name]: e.target.value });
        };
        this.state = {
            sourcePath: '',
            snapshotPath: ''
        };
    }
    render() {
        const { sourcePath, snapshotPath } = this.state;
        return (_jsx(_Fragment, { children: _jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Source Path", name: "sourcePath", value: sourcePath, onValueChanged: (e) => this.handleInputChange(e) }), _jsx(TextField, { label: "Snapshot Path", name: "snapshotPath", value: snapshotPath, onValueChanged: (e) => this.handleInputChange(e) }), _jsx(Button, { onClick: this.handleClone, children: "Clone Model" })] }) }));
    }
}
export default CloneModelView;
//# sourceMappingURL=clone-model.js.map