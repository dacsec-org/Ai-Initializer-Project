package org.dacss.projectinitai.metrics.services;

import org.dacss.projectinitai.metrics.MetricsTypes;
import org.dacss.projectinitai.metrics.MetricsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link MetricsService}</h1>
 * <p>
 *     This class is used to handle the metrics of the server.
 * </p>
 */
@Service
public class MetricsService {

    private final MetricsHandler handler;

    @Autowired
    public MetricsService(MetricsHandler handler) {
        this.handler = handler;
    }

    public Object getMetricDescription(MetricsTypes metricType) {
        return switch (metricType.name().toLowerCase()) {
            case "accuracy" -> handler.getAccuracy();
            case "precision" -> handler.getPrecision();
            case "recall" -> handler.getRecall();
            case "f1_score" -> handler.getF1Score();
            case "roc_auc" -> handler.getRocAuc();
            case "log_loss" -> handler.getLogLoss();
            case "mean_squared_error" -> handler.getMeanSquaredError();
            case "mean_absolute_error" -> handler.getMeanAbsoluteError();
            case "r2_score" -> handler.getR2Score();
            case "confusion_matrix" -> handler.getConfusionMatrix();
            case "precision_at_k" -> handler.getPrecisionAtK();
            case "recall_at_k" -> handler.getRecallAtK();
            case "mean_reciprocal_rank" -> handler.getMeanReciprocalRank();
            case "average_precision" -> handler.getAveragePrecision();
            case "hits_at_k" -> handler.getHitsAtK();
            case "bleu_score" -> handler.getBleuScore();
            case "rouge_score" -> handler.getRougeScore();
            case "perplexity" -> handler.getPerplexity();
            case "cross_entropy" -> handler.getCrossEntropy();
            case "token_accuracy" -> handler.getTokenAccuracy();
            case "cpu" -> handler.getCpu();
            case "gpu" -> handler.getGpu();
            default -> throw new IllegalArgumentException("Unknown metric type: " + metricType);
        };
    }
}
