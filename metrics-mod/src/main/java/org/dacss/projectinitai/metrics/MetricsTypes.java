package org.dacss.projectinitai.metrics;

/**
 * <h1>{@link MetricsTypes}</h1>
 * Each enum constant represents a specific metric type.
 */
public enum MetricsTypes {
    ACCURACY,
    PRECISION,
    RECALL,
    F1_SCORE,
    ROC_AUC,
    LOG_LOSS,
    MEAN_SQUARED_ERROR,
    MEAN_ABSOLUTE_ERROR,
    R2_SCORE,
    CONFUSION_MATRIX,
    PRECISION_AT_K,
    RECALL_AT_K,
    MEAN_RECIPROCAL_RANK,
    AVERAGE_PRECISION,
    HITS_AT_K,
    BLEU_SCORE,
    ROUGE_SCORE,
    PERPLEXITY,
    CROSS_ENTROPY,
    TOKEN_ACCURACY,
    CPU,
    GPU;

    public String getDescription() {
        return switch (this) {
            case ACCURACY -> "Measures the ratio of correctly predicted instances to the total instances.";
            case PRECISION -> "Measures the ratio of correctly predicted positive observations to the total predicted positives.";
            case RECALL -> "Measures the ratio of correctly predicted positive observations to all observations in the actual class.";
            case F1_SCORE -> "Harmonic mean of precision and recall.";
            case ROC_AUC -> "Measures the area under the receiver operating characteristic curve.";
            case LOG_LOSS -> "Measures the performance of a classification model where the prediction is a probability value between 0 and 1.";
            case MEAN_SQUARED_ERROR -> "Measures the average of the squares of the errors or deviations.";
            case MEAN_ABSOLUTE_ERROR -> "Measures the average of the absolute errors.";
            case R2_SCORE -> "Measures the proportion of the variance in the dependent variable that is predictable from the independent variables.";
            case CONFUSION_MATRIX -> "A table used to describe the performance of a classification model.";
            case PRECISION_AT_K -> "Measures the precision of the top-K predictions.";
            case RECALL_AT_K -> "Measures the recall of the top-K predictions.";
            case MEAN_RECIPROCAL_RANK -> "Measures the average of the reciprocal ranks of results.";
            case AVERAGE_PRECISION -> "Measures the average precision of the predictions.";
            case HITS_AT_K -> "Measures the number of relevant items found in the top-K predictions.";
            case BLEU_SCORE -> "Measures the quality of text which has been machine-translated from one natural language to another.";
            case ROUGE_SCORE -> "Measures the quality of summaries by comparing them to reference summaries.";
            case PERPLEXITY -> "Measures how well a probability distribution or probability model predicts a sample.";
            case CROSS_ENTROPY -> "Measures the performance of a classification model whose output is a probability value between 0 and 1.";
            case TOKEN_ACCURACY -> "Measures the accuracy of predicted tokens in sequence generation tasks.";
            case CPU -> "Measures the performance of the CPU during model training or inference.";
            case GPU -> "Measures the performance of the GPU during model training or inference.";
        };
    }
}
