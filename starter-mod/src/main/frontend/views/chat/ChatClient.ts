import { from, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import client_1 from '../connect-client.default';
import { MessageAction } from 'Frontend/enums/MessageAction';

export class ChatClient {
  static getMessages(action: MessageAction): Observable<any> {
    return from(client_1.call('org.dacss.projectinitai.services.ChatService', 'processMessages', { action }))
      .pipe(map(response => response));
  }
}
