package org.dacss.projectinitai.models.settings;

/**
 * <h1>{@link LLMSettingsTypes}</h1>
 * Enumerates all possible the types of settings that can be applied to a wide range of LLM models.
 */
public enum LLMSettingsTypes {
    GPU_DEVICE, // Default: "0"
    HEAT, // Default: "0.7"
    WINDOW_SIZE, // Default: "512"
    MAX_TOKENS, // Default: "2048"
    TOP_P, // Default: "0.9"
    TOP_K, // Default: "50"
    TEMPERATURE, // Default: "1.0"
    FREQUENCY_PENALTY, // Default: "0.0"
    PRESENCE_PENALTY, // Default: "0.0"
    STOP_SEQUENCES, // Default: "[]"
    LOG_PROBS, // Default: "null"
    N_BEST, // Default: "1"
    BEAM_WIDTH, // Default: "1"
    LENGTH_PENALTY, // Default: "1.0"
    REPETITION_PENALTY, // Default: "1.0"
    MIN_LENGTH, // Default: "0"
    MAX_LENGTH, // Default: "2048"
    NUM_RETURN_SEQUENCES, // Default: "1"
    DO_SAMPLE, // Default: "false"
    EARLY_STOPPING, // Default: "false"
    NO_REPEAT_NGRAM_SIZE, // Default: "0"
    BAD_WORDS, // Default: "[]"
    FORCE_WORDS, // Default: "[]"
    PAD_TOKEN_ID, // Default: "0"
    EOS_TOKEN_ID, // Default: "2"
    BOS_TOKEN_ID, // Default: "1"
    DECODER_START_TOKEN_ID, // Default: "0"
    DIVERSITY_PENALTY, // Default: "0.0"
    NUM_BEAMS, // Default: "1"
    NUM_BEAM_GROUPS, // Default: "1"
    OUTPUT_ATTENTIONS, // Default: "false"
    OUTPUT_HIDDEN_STATES, // Default: "false"
    OUTPUT_PAST, // Default: "true"
    RETURN_DICT, // Default: "false"
    RETURN_TENSORS, // Default: "false"
    RETURN_TEXT, // Default: "true"
    RETURN_FULL_TEXT, // Default: "true"
    RETURN_LOGITS, // Default: "false"
    RETURN_PROBABILITIES, // Default: "false"
    RETURN_TOP_K, // Default: "false"
    RETURN_TOP_P, // Default: "false"
    RETURN_TOP_N, // Default: "false"
    RETURN_TOP_LOG_PROBS, // Default: "false"
    RETURN_TOP_TOKENS, // Default: "false"
    RETURN_TOP_SEQUENCES, // Default: "false"
    RETURN_TOP_BEAMS, // Default: "false"
    RETURN_TOP_GROUPS, // Default: "false"
    RETURN_TOP_DIVERSITY, // Default: "false"
    RETURN_TOP_LENGTH, // Default: "false"
    RETURN_TOP_PENALTY, // Default: "false"
    RETURN_TOP_REPETITION // Default: "false"
}
