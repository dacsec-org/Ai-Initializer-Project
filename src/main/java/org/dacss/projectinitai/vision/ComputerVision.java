package org.dacss.projectinitai.vision;

import lombok.Getter;

/**
 * <h1>{@link ComputerVision}</h1>
 */
@Getter
public enum ComputerVision {

    IMAGE_CLASSIFICATION,
    OBJECT_DETECTION,
    IMAGE_SEGMENTATION,
    IMAGE_GENERATION,
    IMAGE_SUPER_RESOLUTION,
    IMAGE_RECOGNITION;

    public String getContextMessage() {
        return switch (this) {
            case IMAGE_CLASSIFICATION -> "Image Classification categorizes images into predefined classes.";
            case OBJECT_DETECTION -> "Object Detection identifies and locates objects in images.";
            case IMAGE_SEGMENTATION -> "Image Segmentation partitions images into segments.";
            case IMAGE_GENERATION -> "Image Generation creates new images from scratch.";
            case IMAGE_SUPER_RESOLUTION -> "Image Super-Resolution enhances the resolution of images.";
            case IMAGE_RECOGNITION -> "Image Recognition identifies objects or features in images.";
        };
    }
}
