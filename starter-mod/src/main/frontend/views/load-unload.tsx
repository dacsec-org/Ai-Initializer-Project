import React, { Component } from 'react';
import { Notification } from '@vaadin/notification';
import { LoadUnloadService } from 'Frontend/generated/endpoints';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

/**
 * <h3>{@link config}</h3>
 * <p>
 *   Order and icon for the side navigation menu.
 * </p>
 */
export const config: ViewConfig = {
    menu: { order: 5, icon: 'line-awesome/svg/upload-solid.svg' },
    title: 'Load ~ Unload',
};

/**
 * <h3>{@link LoadUnloadComponentState}</h3>
 * <p>
 *   State interface for the load-unload component
 * </p>
 */
interface LoadUnloadComponentState {
    modelData: Uint8Array | null;
}

/**
 * <h1>{@link LoadUnloadComponent}</h1>
 */
class LoadUnloadComponent extends Component<{}, LoadUnloadComponentState> {
    constructor(props: {}) {
        super(props);
        this.state = {
            modelData: null,
        };
    }

    /**
     * <h3>{@link loadModel}</h3>
     * @param modelPath
     */
    loadModel = async (modelPath: string) => {
        const modelData = await LoadUnloadService.loadUnloadLLM("load", modelPath, new Uint8Array());
        Notification.show('Model loaded successfully: ' + modelData);
        this.setState({ modelData: new Uint8Array() });
    };

    /**
     * <h3>{@link unloadModel}</h3>
     */
    unloadModel = async () => {
        await LoadUnloadService.loadUnloadLLM("unload", "", new Uint8Array());
        Notification.show('Model unloaded successfully');
        this.setState({ modelData: null });
    };

    /**
     * <h3>{@link render}</h3>
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
