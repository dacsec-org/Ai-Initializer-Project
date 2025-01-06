package org.dacss.projectinitai.vision;

import lombok.Getter;

@Getter
public enum ComputerVision {

    IMAGE_CLASSIFICATION,
    OBJECT_DETECTION,
    IMAGE_SEGMENTATION,
    IMAGE_GENERATION,
    IMAGE_SUPER_RESOLUTION,
    IMAGE_RECOGNITION;
    String value;

    ComputerVision() {}
}
