import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { Component } from 'react';
import { Button, Notification, TextField, Dialog } from '@vaadin/react-components';
import { ModelsService } from 'Frontend/generated/endpoints';
import { useNavigate } from 'react-router';

export const config = {
    menu: { order: 2, icon: 'line-awesome/svg/clone-solid.svg' },
    title: 'Clone',
};

class CloneModelView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sourcePath: '',
            dialogOpened: false,
            dialogMessage: '',
            dialogAction: () => { }
        };
    }

    openDialog = (message, action) => {
        this.setState({
            dialogMessage: message,
            dialogAction: action,
            dialogOpened: true
        });
    };

    handleDialogClose = () => {
        this.setState({ dialogOpened: false });
    };

    handleClone = async () => {
        const { sourcePath } = this.state;
        const response = await ModelsService.processModel('clone', sourcePath, '');
        Notification.show("Cloning successful" + response);
        this.setState({ dialogOpened: false });
    };

    handleInputChange = (e) => {
        this.setState({ sourcePath: e.detail.value });
    };

    render() {
        const { sourcePath, dialogOpened, dialogMessage, dialogAction } = this.state;

        return (
            _jsxs(_Fragment, { children: [
                _jsxs("section", { className: "flex p-m gap-m items-end", children: [
                    _jsx(TextField, {
                        label: "Source Path",
                        value: sourcePath,
                        onValueChanged: this.handleInputChange
                    }),
                    _jsx(Button, {
                        onClick: () => this.openDialog('Are you sure you want to clone this model?', this.handleClone),
                        style: { backgroundColor: 'blue' },
                        children: "Clone Model"
                    })
                ]}),
                _jsx(Dialog, {
                    opened: dialogOpened,
                    onOpenedChanged: (e) => this.setState({ dialogOpened: e.detail.value }),
                    children: _jsxs("div", { children: [
                        _jsx("p", { children: dialogMessage }),
                        _jsxs("div", { className: "flex gap-s", children: [
                            _jsx(Button, {
                                theme: "primary",
                                onClick: () => { dialogAction(); this.handleDialogClose(); },
                                children: "Yes"
                            }),
                            _jsx(Button, {
                                theme: "secondary",
                                onClick: this.handleDialogClose,
                                children: "No"
                            })
                        ]})
                    ]})
                })
            ]})
        );
    }
}

export default CloneModelView;
//# sourceMappingURL=clone-model.js.map
