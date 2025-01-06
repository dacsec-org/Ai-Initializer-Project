package org.dacss.projectinitai.recognition;

import lombok.Getter;

@Getter
public enum Recognition {

    FACIAL_RECOGNITION,
    OBJECT_RECOGNITION,
    TEXT_RECOGNITION,
    VOICE_RECOGNITION,
    GESTURE_RECOGNITION,
    SCENE_RECOGNITION;
    String value;

    Recognition() {}
}
