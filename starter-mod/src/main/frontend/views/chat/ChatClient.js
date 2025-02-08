import { from } from 'rxjs';
import { map } from 'rxjs/operators';
import client_1 from '../connect-client.default';
export class ChatClient {
    static getMessages(action) {
        return from(client_1.call('org.dacss.projectinitai.services.ChatService', 'processMessages', { action }))
            .pipe(map(response => response));
    }
}
//# sourceMappingURL=ChatClient.js.map