package org.dacss.projectinitai.recognition;

import lombok.Getter;

/**
 * <h1>{@link Recognition}</h1>
 */
@Getter
public enum Recognition {

    FACIAL_RECOGNITION,
    OBJECT_RECOGNITION,
    TEXT_RECOGNITION,
    VOICE_RECOGNITION,
    GESTURE_RECOGNITION,
    SCENE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case FACIAL_RECOGNITION -> "Facial Recognition identifies and verifies individuals based on their faces.";
            case OBJECT_RECOGNITION -> "Object Recognition identifies objects within an image.";
            case TEXT_RECOGNITION -> "Text Recognition extracts text from images.";
            case VOICE_RECOGNITION -> "Voice Recognition identifies and verifies a person's voice.";
            case GESTURE_RECOGNITION -> "Gesture Recognition interprets human gestures via mathematical algorithms.";
            case SCENE_RECOGNITION -> "Scene Recognition identifies the context or environment in an image.";
        };
    }
}
