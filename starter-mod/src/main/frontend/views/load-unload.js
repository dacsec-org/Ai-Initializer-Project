import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Notification } from '@vaadin/notification';
import { ModelLoadUnloadService } from 'Frontend/generated/endpoints.js';
/**
 * {@link LoadUnloadComponent}
 * <p>
 *   This is the main load-unload component that handles loading and unloading models
 * </p>
 */
class LoadUnloadComponent extends Component {
    constructor(props) {
        super(props);
        /**
         * {@link #loadModel}
         * <p>
         *   This method loads a model from the specified path
         * </p>
         * @param modelPath
         */
        this.loadModel = async (modelPath) => {
            try {
                const data = await ModelLoadUnloadService.loadModel(modelPath);
                this.setState({ modelData: new Uint8Array(data) });
                Notification.show('Model loaded successfully');
            }
            catch (error) {
                Notification.show('Error loading model: ' + error.message, { theme: 'error' });
            }
        };
        /**
         * {@link #unloadModel}
         * <p>
         *   This method unloads the currently loaded model
         * </p>
         */
        this.unloadModel = async () => {
            const { modelData } = this.state;
            if (modelData) {
                try {
                    const success = await ModelLoadUnloadService.unloadModel(Array.from(modelData));
                    if (success) {
                        this.setState({ modelData: null });
                        Notification.show('Model unloaded successfully');
                    }
                    else {
                        Notification.show('Error unloading model', { theme: 'error' });
                    }
                }
                catch (error) {
                    Notification.show('Error unloading model: ' + error.message, { theme: 'error' });
                }
            }
        };
        this.state = {
            modelData: null,
        };
    }
    /**
     * {@link #render}
     * <p>
     *   This function renders the load and unload buttons
     * </p>
     */
    render() {
        const { modelData } = this.state;
        return (_jsxs("div", { children: [_jsx("button", { onClick: () => this.loadModel('path/to/model'), children: "Load Model" }), _jsx("button", { onClick: this.unloadModel, disabled: !modelData, children: "Unload Model" })] }));
    }
}
export default LoadUnloadComponent;
//# sourceMappingURL=load-unload.js.map