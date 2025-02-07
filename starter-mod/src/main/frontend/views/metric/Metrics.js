import client_1 from '../connect-client.default';
/**
 * <h1>{@link Metrics}</h1>
 */
export class Metrics {
    static async getMetrics(type, init) {
        return client_1.call('org.dacss.projectinitai.services.MetricsService', 'measure', { type }, init);
    }
}
//# sourceMappingURL=Metrics.js.map