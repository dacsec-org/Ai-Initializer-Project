import React, { Component } from 'react';
import { Notification } from '@vaadin/notification';
import { LoadUnloadService } from 'Frontend/generated/endpoints';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

export const config: ViewConfig = {
    menu: { order: 5, icon: 'line-awesome/svg/upload-solid.svg' },
    title: 'Load ~ Unload',
};

interface LoadUnloadComponentState {
    modelData: Uint8Array | null;
}

/**
 * <h1>{@link LoadUnloadView}</h1>
 */
class LoadUnloadView extends Component<{}, LoadUnloadComponentState> {
    constructor(props: {}) {
        super(props);
        this.state = {
            modelData: null,
        };
    }

    loadModel = async (modelPath: string) => {
        const modelData = new Uint8Array();
        await LoadUnloadService.loadUnloadLLM("load", modelPath, Array.from(modelData));
        Notification.show('Model loaded successfully');
        this.setState({ modelData });
    };

    unloadModel = async () => {
        const modelData = new Uint8Array();
        await LoadUnloadService.loadUnloadLLM("unload", "", Array.from(modelData));
        Notification.show('Model unloaded successfully');
        this.setState({ modelData: null });
    };

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

export default LoadUnloadView;
