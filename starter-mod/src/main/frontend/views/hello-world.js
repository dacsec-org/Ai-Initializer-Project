import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { Component } from 'react';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints';
export const config = {
    menu: { order: 10, icon: 'line-awesome/svg/smile-solid.svg' },
    title: 'Hello World'
};
class HelloWorldView extends Component {
    constructor(props) {
        super(props);
        this.handleNameChange = (e) => {
            this.setState({ name: e.detail.value });
        };
        this.handleButtonClick = async () => {
            const serverResponse = await HelloWorldService.sayHello(this.state.name);
            Notification.show(serverResponse);
        };
        this.state = {
            name: ''
        };
    }
    render() {
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Your name", onValueChanged: this.handleNameChange }), _jsx(Button, { onClick: this.handleButtonClick, children: "Say hello" })] })] }));
    }
}
export default HelloWorldView;
//# sourceMappingURL=hello-world.js.map
