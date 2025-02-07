import { EndpointRequestInit as EndpointRequestInit_1 } from '@vaadin/hilla-frontend';
import client_1 from '../connect-client.default';
import { MetricsTypes } from 'Frontend/enums/MetricsTypes';

/**
 * <h1>{@link Metrics}</h1>
 */
export class Metrics {
  static async getMetrics(type: MetricsTypes, init?: EndpointRequestInit_1): Promise<any> {
    return client_1.call('org.dacss.projectinitai.services.MetricsService', 'measure', { type }, init);
  }
}
