import { EndpointRequestInit } from '@vaadin/hilla-frontend';
import client from '../connect-client.default';
import { DownloadAction } from 'Frontend/enums/DownloadAction';

/**
 * <h1>{@link SearchModels}</h1>
 */
export class SearchModels {
  static async getModels(action: DownloadAction, llmName: string, init?: EndpointRequestInit): Promise<any> {
    return client.call(
      'org.dacss.projectinitai.services.ModelsService',
      'download',
      { action, llmName },
      init
    );
  }
}
