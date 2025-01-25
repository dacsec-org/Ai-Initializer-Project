package org.dacss.projectinitai.metrics.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link MetricsService}</h1>
 * <p>
 *     This class is used to handle the metrics of the server.
 * </p>
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class MetricsService implements MetricsIface {

    private static final Logger log = LoggerFactory.getLogger(MetricsService.class);

    public MetricsService() {
    }
//
//    public Object getMetricDescription(MetricsTypes metricType) {
//        return switch (metricType.name().toLowerCase()) {
//            case "accuracy" -> handler.getAccuracy();
//            case "precision" -> handler.getPrecision();
//            case "recall" -> handler.getRecall();
//            case "f1_score" -> handler.getF1Score();
//            case "roc_auc" -> handler.getRocAuc();
//            case "log_loss" -> handler.getLogLoss();
//            case "mean_squared_error" -> handler.getMeanSquaredError();
//            case "mean_absolute_error" -> handler.getMeanAbsoluteError();
//            case "r2_score" -> handler.getR2Score();
//            case "confusion_matrix" -> handler.getConfusionMatrix();
//            case "precision_at_k" -> handler.getPrecisionAtK();
//            case "recall_at_k" -> handler.getRecallAtK();
//            case "mean_reciprocal_rank" -> handler.getMeanReciprocalRank();
//            case "average_precision" -> handler.getAveragePrecision();
//            case "hits_at_k" -> handler.getHitsAtK();
//            case "bleu_score" -> handler.getBleuScore();
//            case "rouge_score" -> handler.getRougeScore();
//            case "perplexity" -> handler.getPerplexity();
//            case "cross_entropy" -> handler.getCrossEntropy();
//            case "token_accuracy" -> handler.getTokenAccuracy();
//            case "cpu" -> handler.getCpu();
//            case "gpu" -> handler.getGpu();
//            default -> throw new IllegalArgumentException("Unknown metric type: " + metricType);
//        };
//    }

    /**
     * <h2>{@link MetricsIface#measure()}</h2>
     * measure the data.
     */
    @Override
    public void measure() {
        log.info("Measuring data...");

    }
}
