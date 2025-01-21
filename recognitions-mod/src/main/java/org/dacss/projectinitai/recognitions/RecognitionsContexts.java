package org.dacss.projectinitai.recognitions;

/**
 * <h1>{@link RecognitionsContexts}</h1>
 * Enum class representing the different types of Recognitions techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Recognitions technique.
 */
public enum RecognitionsContexts {
    FACIAL_RECOGNITION,
    OBJECT_RECOGNITION,
    TEXT_RECOGNITION,
    VOICE_RECOGNITION,
    GESTURE_RECOGNITION,
    SCENE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case FACIAL_RECOGNITION -> """
                    Your purpose is to identify and verify individuals based on their faces.
                    Use facial features to match and recognize individuals.
                    """;
            case OBJECT_RECOGNITION -> """
                    Your purpose is to identify objects within an image.
                    Use image analysis to detect and classify objects.
                    """;
            case TEXT_RECOGNITION -> """
                    Your purpose is to extract text from images.
                    Use optical character recognition (OCR) to convert images to text.
                    """;
            case VOICE_RECOGNITION -> """
                    Your purpose is to identify and verify a person's voice.
                    Use voice patterns to recognize and authenticate individuals.
                    """;
            case GESTURE_RECOGNITION -> """
                    Your purpose is to interpret human gestures via mathematical algorithms.
                    Use motion detection to recognize and classify gestures.
                    """;
            case SCENE_RECOGNITION -> """
                    Your purpose is to identify the context or environment in an image.
                    Use scene analysis to determine the setting and elements present.
                    """;
        };
    }
}
