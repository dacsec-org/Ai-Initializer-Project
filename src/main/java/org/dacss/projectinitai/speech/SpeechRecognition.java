package org.dacss.projectinitai.speech;

import lombok.Getter;

/**
 * <h1>{@link SpeechRecognition}</h1>
 * Enum class representing the different types of Speech Recognition techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Speech Recognition technique.
 */
@Getter
public enum SpeechRecognition {

    SPEECH_TO_TEXT,
    TEXT_TO_SPEECH,
    VOICE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case SPEECH_TO_TEXT -> "Your purpose is to convert spoken language into written text. Use speech-to-text technology to transcribe audio.";
            case TEXT_TO_SPEECH -> "Your purpose is to convert written text into spoken words. Use text-to-speech technology to generate audio from text.";
            case VOICE_RECOGNITION -> "Your purpose is to identify and verify a person's voice. Use voice recognition technology to authenticate individuals.";
        };
    }
}
