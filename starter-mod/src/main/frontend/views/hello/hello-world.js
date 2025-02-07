import { jsx as _jsx, Fragment as _Fragment, jsxs as _jsxs } from "react/jsx-runtime";
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';
export const config = {
    menu: { order: 10, icon: 'line-awesome/svg/smile-solid.svg' },
    title: 'Hello World'
};
export default function HelloWorldView() {
    const name = useSignal('');
    return (_jsxs(_Fragment, { children: [_jsx(TextField, { label: "Your name", onValueChanged: (e) => {
                    name.value = e.detail.value;
                } }), _jsx(Button, { onClick: async () => {
                    const serverResponse = await HelloWorldService.sayHello(name.value);
                    Notification.show(serverResponse);
                }, children: "Say hello" })] }));
}
//# sourceMappingURL=hello-world.js.map