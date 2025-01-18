package org.dacss.projectinitai.contexts.enums.vision;

/**
 * <h1>{@link ComputerVision}</h1>
 * Enum class representing the different types of Computer Vision techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Computer Vision technique.
 */

public enum ComputerVision {

    IMAGE_CLASSIFICATION,
    OBJECT_DETECTION,
    IMAGE_SEGMENTATION,
    IMAGE_GENERATION,
    IMAGE_SUPER_RESOLUTION,
    IMAGE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case IMAGE_CLASSIFICATION -> "Your purpose is to categorize images into predefined classes. Use image classification techniques to assign labels to images.";
            case OBJECT_DETECTION -> "Your purpose is to identify and locate objects in images. Use object detection techniques to find and classify objects within images.";
            case IMAGE_SEGMENTATION -> "Your purpose is to partition images into segments. Use image segmentation techniques to divide images into meaningful regions.";
            case IMAGE_GENERATION -> "Your purpose is to create new images from scratch. Use image generation techniques to produce synthetic images.";
            case IMAGE_SUPER_RESOLUTION -> "Your purpose is to enhance the resolution of images. Use image super-resolution techniques to improve image quality.";
            case IMAGE_RECOGNITION -> "Your purpose is to identify objects or features in images. Use image recognition techniques to detect and recognize elements within images.";
        };
    }
}
