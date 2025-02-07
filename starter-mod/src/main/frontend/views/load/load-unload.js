import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useState } from 'react';
import { Notification } from '@vaadin/notification';
// @ts-ignore
import { LoadUnloadService } from 'Frontend/generated/endpoints';
export const config = {
    menu: { order: 5, icon: 'line-awesome/svg/upload-solid.svg' },
    title: 'Load ~ Unload',
};
const LoadUnloadView = () => {
    const [modelData, setModelData] = useState(null);
    const loadModel = async (modelPath) => {
        const modelData = new Uint8Array();
        await LoadUnloadService.loadUnloadLLM("load", modelPath, Array.from(modelData));
        Notification.show('Model loaded successfully');
        setModelData(modelData);
    };
    const unloadModel = async () => {
        const modelData = new Uint8Array();
        await LoadUnloadService.loadUnloadLLM("unload", "", Array.from(modelData));
        Notification.show('Model unloaded successfully');
        setModelData(null);
    };
    return (_jsxs("div", { children: [_jsx("button", { onClick: () => loadModel('path/to/model'), children: "Load Model" }), _jsx("button", { onClick: unloadModel, disabled: !modelData, children: "Unload Model" })] }));
};
export default LoadUnloadView;
//# sourceMappingURL=load-unload.js.map