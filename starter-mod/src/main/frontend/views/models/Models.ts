import { EndpointRequestInit as EndpointRequestInit_1 } from '@vaadin/hilla-frontend';
import client_1 from '../connect-client.default';
import { ModelActions } from 'Frontend/enums/ModelActions';

/**
 * <h1>{@link Models}</h1>
 */
export class Models {
  static async getModels(action: ModelActions, modelPath1: string, modelPath2: string, init?: EndpointRequestInit_1): Promise<any> {
    return client_1.call(
      'org.dacss.projectinitai.services.ModelsService',
      'processModel',
      { action, modelPath1, modelPath2 },
      init
    );
  }
}
