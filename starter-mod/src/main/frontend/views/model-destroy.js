import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { MergeDestroyModelService } from 'Frontend/generated/endpoints.js';
/**
 * {@link config}
 * <p>
 *   Configuration for the view, including menu order, icon, and title.
 * </p>
 */
export const config = {
    menu: { order: 4, icon: 'line-awesome/svg/trash-alt-solid.svg' },
    title: 'Delete Model',
};
/**
 * {@link DeleteModelView}
 * <p>
 *   This component allows users to delete a model by providing its path.
 *   It includes an input field for the model path and a button to trigger the deletion.
 *   A dialog is shown to confirm the deletion action.
 * </p>
 */
class DeleteModelView extends Component {
    constructor(props) {
        super(props);
        /**
         * {@link handleDelete}
         * <p>
         *   This function handles the delete action by calling the MergeDestroyModelService.
         *   It shows a notification with the response and closes the dialog.
         * </p>
         */
        this.handleDelete = async () => {
            const { modelPath } = this.state;
            const response = await MergeDestroyModelService.deleteModel(modelPath);
            Notification.show(response);
            this.setState({ dialogOpened: false });
        };
        /**
         * {@link openDialog}
         * <p>
         *   This function opens the confirmation dialog.
         * </p>
         */
        this.openDialog = () => {
            this.setState({ dialogOpened: true });
        };
        /**
         * {@link closeDialog}
         * <p>
         *   This function closes the confirmation dialog.
         * </p>
         */
        this.closeDialog = () => {
            this.setState({ dialogOpened: false });
        };
        /**
         * {@link handleInputChange}
         * <p>
         *   This function updates the state with the value of the model path input field.
         * </p>
         */
        this.handleInputChange = (e) => {
            this.setState({ modelPath: e.target.value });
        };
        this.state = {
            modelPath: '',
            dialogOpened: false
        };
    }
    /**
     * {@link render}
     * <p>
     *   This function renders the component, including an input field for the model path,
     *   a button to trigger the deletion, and a confirmation dialog.
     * </p>
     */
    render() {
        const { modelPath, dialogOpened } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Model Path", value: modelPath, onValueChanged: (e) => this.handleInputChange(e) }), _jsx(Button, { onClick: this.openDialog, children: "Delete Model" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsx("p", { children: "Are you sure you want to delete this model? Any work associated with this model will be deleted as well!" }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary error", onClick: this.handleDelete, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.closeDialog, children: "No" })] })] }) })] }));
    }
}
export default DeleteModelView;
//# sourceMappingURL=model-destroy.js.map