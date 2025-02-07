import { EndpointRequestInit as EndpointRequestInit_1 } from '@vaadin/hilla-frontend';
import client_1 from '../connect-client.default';
import { MessageAction } from 'Frontend/enums/MessageAction';

/**
 * <h1>{@link ChatClient}</h1>
 */
export class ChatClient {
  static async getMessages(action: MessageAction, init?: EndpointRequestInit_1): Promise<any> {
    return client_1.call('org.dacss.projectinitai.services.ChatService', 'processMessages', { action }, init);
  }
}
