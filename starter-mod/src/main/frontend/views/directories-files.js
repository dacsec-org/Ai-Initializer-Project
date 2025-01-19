import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, Dialog } from '@vaadin/react-components';
import { DirFileService } from 'Frontend/generated/endpoints.js';
import CustomTextFields from './custom-textfields';
/**
 * {@link #config}
 * <p>
 *   Order and icon for the side navigation menu.
 * </p>
 */
export const config = {
    menu: { order: 5, icon: 'line-awesome/svg/folder-open-solid.svg' },
    title: 'Directories and Files',
};
/**
 * {@link DirFileView}
 * <p>
 *   This class handles the UI operations for directories and files.
 * </p>
 */
class DirFileView extends Component {
    constructor(props) {
        super(props);
        /**
         * {@link handleOperation}
         * <p>
         *   This method handles the operation for the directories and files.
         *   It calls the handleOperation method from the DirFileService with the operation, path, and fileName.
         *   It then displays the response as a notification.
         * </p>
         */
        this.handleOperation = async () => {
            const { path, fileName, operation } = this.state;
            const response = await DirFileService.handleOperation(operation, path, fileName);
            Notification.show(response);
            this.setState({ dialogOpened: false });
        };
        /**
         * {@link openDialog}
         * <p>
         *   This method opens the dialog for the operation.
         * </p>
         */
        this.openDialog = (operation) => {
            this.setState({ dialogOpened: true, operation });
        };
        /**
         * {@link closeDialog}
         * <p>
         *   This method closes the dialog.
         * </p>
         */
        this.closeDialog = () => {
            this.setState({ dialogOpened: false });
        };
        /**
         * {@link handleInputChange}
         * <p>
         *   This method handles the input change for the path and fileName.
         * </p>
         */
        this.handleInputChange = (e, field) => {
            this.setState({ [field]: e.target.value });
        };
        this.state = {
            path: '',
            fileName: '',
            dialogOpened: false,
            operation: '',
        };
    }
    /**
     * {@link render}
     * <p>
     *   This method renders the directories and files view.
     * </p>
     */
    render() {
        const { path, fileName, dialogOpened, operation } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(CustomTextFields, { renderTextField: 3 }), _jsx(CustomTextFields, { renderTextField: 4 }), _jsx(Button, { onClick: () => this.openDialog('CREATE_DIRECTORY'), children: "Create Directory" }), _jsx(Button, { onClick: () => this.openDialog('CREATE_FILE'), children: "Create File" }), _jsx(Button, { onClick: () => this.openDialog('DELETE_DIRECTORY'), children: "Delete Directory" }), _jsx(Button, { onClick: () => this.openDialog('DELETE_FILE'), children: "Delete File" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsxs("p", { children: ["Are you sure you want to ", operation.replace('_', ' ').toLowerCase(), "?"] }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary error", onClick: this.handleOperation, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.closeDialog, children: "No" })] })] }) })] }));
    }
}
export default DirFileView;
//# sourceMappingURL=directories-files.js.map