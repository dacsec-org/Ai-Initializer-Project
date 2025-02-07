import client_1 from '../connect-client.default';
/**
 * <h1>{@link ChatClient}</h1>
 */
export class ChatClient {
    static async getMessages(action, init) {
        return client_1.call('org.dacss.projectinitai.services.ChatService', 'processMessages', { action }, init);
    }
}
//# sourceMappingURL=ChatClient.js.map