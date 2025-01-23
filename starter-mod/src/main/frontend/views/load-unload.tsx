import React, { Component } from 'react';
import { Notification } from '@vaadin/notification';
import { ModelLoadUnloadService } from 'Frontend/generated/endpoints';

/**
 * {@link LoadUnloadComponentState}
 * <p>
 *   This is the state interface for the load-unload component
 * </p>
 */
interface LoadUnloadComponentState {
    modelData: Uint8Array | null;
}

/**
 * {@link LoadUnloadComponent}
 * <p>
 *   This is the main load-unload component that handles loading and unloading models
 * </p>
 */
class LoadUnloadComponent extends Component<{}, LoadUnloadComponentState> {
    constructor(props: {}) {
        super(props);
        this.state = {
            modelData: null,
        };
    }

    /**
     * {@link #loadModel}
     * <p>
     *   This method loads a model from the specified path
     * </p>
     * @param modelPath
     */
    loadModel = async (modelPath: string) => {
        try {
            const data = await ModelLoadUnloadService.loadModel(modelPath);
            this.setState({ modelData: new Uint8Array(data) });
            Notification.show('Model loaded successfully');
        } catch (error: any) {
            Notification.show('Error loading model: ' + error.message, { theme: 'error' });
        }
    };

    /**
     * {@link #unloadModel}
     * <p>
     *   This method unloads the currently loaded model
     * </p>
     */
    unloadModel = async () => {
        const { modelData } = this.state;
        if (modelData) {
            try {
                const success = await ModelLoadUnloadService.unloadModel(Array.from(modelData));
                if (success) {
                    this.setState({ modelData: null });
                    Notification.show('Model unloaded successfully');
                } else {
                    Notification.show('Error unloading model', { theme: 'error' });
                }
            } catch (error: any) {
                Notification.show('Error unloading model: ' + error.message, { theme: 'error' });
            }
        }
    };

    /**
     * {@link #render}
     * <p>
     *   This function renders the load and unload buttons
     * </p>
     */
    render() {
        const { modelData } = this.state;

        return (
            <div>
                <button onClick={() => this.loadModel('path/to/model')}>Load Model</button>
                <button onClick={this.unloadModel} disabled={!modelData}>Unload Model</button>
            </div>
        );
    }
}

export default LoadUnloadComponent;
