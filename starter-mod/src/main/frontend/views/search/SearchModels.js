import client from '../connect-client.default';
/**
 * <h1>{@link SearchModels}</h1>
 */
export class SearchModels {
    static async getModels(action, llmName, init) {
        return client.call('org.dacss.projectinitai.services.ModelsService', 'download', { action, llmName }, init);
    }
}
//# sourceMappingURL=SearchModels.js.map