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
    menu: { order: 5, icon: 'line-awesome/svg/merge-solid.svg' },
    title: 'Merge Models',
};
/**
 * {@link MergeModelView}
 * <p>
 *   This component allows users to merge two models by providing their paths.
 *   It includes input fields for the model paths and a button to trigger the merge.
 *   A dialog is shown to confirm the merge action.
 * </p>
 */
class MergeModelView extends Component {
    constructor(props) {
        super(props);
        /**
         * {@link handleMerge}
         * <p>
         *   This function handles the merge action by calling the MergeDestroyModelService.
         *   It shows a notification with the response and closes the dialog.
         * </p>
         */
        this.handleMerge = async () => {
            const { modelPath1, modelPath2 } = this.state;
            const response = await MergeDestroyModelService.mergeModels(modelPath1, modelPath2);
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
         * {@link handleInputChange1}
         * <p>
         *   This function updates the state with the value of the first model path input field.
         * </p>
         */
        this.handleInputChange1 = (e) => {
            this.setState({ modelPath1: e.target.value });
        };
        /**
         * {@link handleInputChange2}
         * <p>
         *   This function updates the state with the value of the second model path input field.
         * </p>
         */
        this.handleInputChange2 = (e) => {
            this.setState({ modelPath2: e.target.value });
        };
        this.state = {
            modelPath1: '',
            modelPath2: '',
            dialogOpened: false
        };
    }
    /**
     * {@link render}
     * <p>
     *   This function renders the component, including input fields for model paths,
     *   a button to trigger the merge, and a confirmation dialog.
     * </p>
     */
    render() {
        const { modelPath1, modelPath2, dialogOpened } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Model Path 1", value: modelPath1, onValueChanged: (e) => this.handleInputChange1(e) }), _jsx(TextField, { label: "Model Path 2", value: modelPath2, onValueChanged: (e) => this.handleInputChange2(e) }), _jsx(Button, { onClick: this.openDialog, children: "Merge Models" })] }), _jsx(Dialog, { opened: dialogOpened, onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }), children: _jsxs("div", { children: [_jsx("p", { children: "Are you sure you want to merge these models?" }), _jsxs("div", { className: "flex gap-s", children: [_jsx(Button, { theme: "primary", onClick: this.handleMerge, children: "Yes" }), _jsx(Button, { theme: "secondary", onClick: this.closeDialog, children: "No" })] })] }) })] }));
    }
}
export default MergeModelView;
//# sourceMappingURL=model-merge.js.map