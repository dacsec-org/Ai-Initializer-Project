import client_1 from '../connect-client.default';
/**
 * <h1>{@link Models}</h1>
 */
export class Models {
    static async getModels(action, modelPath1, modelPath2, init) {
        return client_1.call('org.dacss.projectinitai.services.ModelsService', 'processModel', { action, modelPath1, modelPath2 }, init);
    }
}
//# sourceMappingURL=Models.js.map