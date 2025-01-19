import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useSignal } from '@vaadin/hilla-react-signals';
import { Button, Notification, TextField } from '@vaadin/react-components';
import { HelloWorldService } from 'Frontend/generated/endpoints.js';
export const config = {
    menu: { order: 12, icon: 'line-awesome/svg/cog-solid.svg' },
    title: 'System Settings',
};
export default function SystemSettingsView() {
    const name = useSignal('');
    return (_jsx(_Fragment, { children: _jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Your name", onValueChanged: (e) => {
                        name.value = e.detail.value;
                    } }), _jsx(Button, { onClick: async () => {
                        const serverResponse = await HelloWorldService.sayHello(name.value);
                        Notification.show(serverResponse);
                    }, children: "Say hello" })] }) }));
}
//# sourceMappingURL=system-settings.js.map