import { from, Observable } from 'rxjs';
import client from '../connect-client.default';
import { SystemSettingsOptions } from 'Frontend/enums/SystemSettingsOptions';

export class SystemSettings {
  static processSettings(option: SystemSettingsOptions): Observable<any> {
    return from(client.call(
      'org.dacss.projectinitai.services.SystemSettingsService',
      'processSettings',
      { option }));
  }
}
