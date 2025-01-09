package org.dacss.projectinitai.speech;

import lombok.Getter;

/**
 * <h1>{@link SpeechRecognition}</h1>
 */
@Getter
public enum SpeechRecognition {

    SPEECH_TO_TEXT,
    TEXT_TO_SPEECH,
    VOICE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case SPEECH_TO_TEXT -> "Speech-to-Text converts spoken language into written text.";
            case TEXT_TO_SPEECH -> "Text-to-Speech converts written text into spoken words.";
            case VOICE_RECOGNITION -> "Voice Recognition identifies and verifies a person's voice.";
        };
    }
}
