package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import org.dacss.projectinitai.metrics.utilities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link MetricsService}</h1>
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class MetricsService implements MetricsIface {

    private static final Logger log = LoggerFactory.getLogger(MetricsService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    @Override
    public Flux<Object> measure(MetricsTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case GPU -> GpuStatsUtil.fetchGpuStats();
                case ACCURACY -> null;
                case PRECISION -> null;
                case RECALL -> null;
                case F1_SCORE -> null;
                case ROC_AUC -> null;
                case LOG_LOSS -> null;
                case MEAN_SQUARED_ERROR -> null;
                case MEAN_ABSOLUTE_ERROR -> null;
                case R2_SCORE -> null;
                case CONFUSION_MATRIX -> null;
                case PRECISION_AT_K -> null;
                case RECALL_AT_K -> null;
                case MEAN_RECIPROCAL_RANK -> null;
                case AVERAGE_PRECISION -> null;
                case HITS_AT_K -> null;
                case BLEU_SCORE -> null;
                case ROUGE_SCORE -> null;
                case PERPLEXITY -> null;
                case CROSS_ENTROPY -> null;
                case TOKEN_ACCURACY -> null;
                case CPU -> CpuStatsUtil.fetchCpuStats();
            };
        } catch (Exception metricsExc) {
            log.error(RED + "Error handling operation: {}" + RESET, type, metricsExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "Metrics operation completed: {}" + RESET, type);
        }
        assert flux != null;
        return flux;
    }
}
