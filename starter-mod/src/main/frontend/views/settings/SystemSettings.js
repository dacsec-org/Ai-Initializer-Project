import { from } from 'rxjs';
import client from '../connect-client.default';
export class SystemSettings {
    static processSettings(option) {
        return from(client.call('org.dacss.projectinitai.services.SystemSettingsService', 'processSettings', { option }));
    }
}
//# sourceMappingURL=SystemSettings.js.map