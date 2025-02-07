import { EndpointRequestInit } from '@vaadin/hilla-frontend';
import client from '../connect-client.default';
import { SystemSettingsOptions } from 'Frontend/enums/SystemSettingsOptions';

/**
 * <h1>{@link SystemSettings}</h1>
 */
export class SystemSettings {
  static async processSettings(option: SystemSettingsOptions, init?: EndpointRequestInit): Promise<any> {
    return client.call(
      'org.dacss.projectinitai.services.SystemSettingsService',
      'processSettings',
      { option },
      init
    );
  }
}
