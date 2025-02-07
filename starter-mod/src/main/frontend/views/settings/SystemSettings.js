import client from '../connect-client.default';
/**
 * <h1>{@link SystemSettings}</h1>
 */
export class SystemSettings {
    static async processSettings(option, init) {
        return client.call('org.dacss.projectinitai.services.SystemSettingsService', 'processSettings', { option }, init);
    }
}
//# sourceMappingURL=SystemSettings.js.map