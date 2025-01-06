package org.dacss.projectinitai.speech;

import lombok.Getter;

@Getter
public enum SpeechRecognition {

    SPEECH_TO_TEXT,
    TEXT_TO_SPEECH,
    VOICE_RECOGNITION;
    String value;

    SpeechRecognition() {}
}


