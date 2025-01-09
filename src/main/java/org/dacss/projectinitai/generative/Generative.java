package org.dacss.projectinitai.generative;

import lombok.Getter;

/**
 * <h1>{@link Generative}</h1>
 */
@Getter
public enum Generative {
    DEEPFAKES,
    GENERATIVE_ADVERSARIAL_NETWORKS,
    TEXT_TO_IMAGE,
    VARIATIONAL_AUTOENCODERS,
    MUSIC_GENERATION,
    TEXT_GENERATION;

    public String getContextMessage() {
        return switch (this) {
            case DEEPFAKES -> "Deepfakes involve creating realistic fake videos or images.";
            case GENERATIVE_ADVERSARIAL_NETWORKS -> "Generative Adversarial Networks (GANs) are used to generate new data samples.";
            case TEXT_TO_IMAGE -> "Text-to-Image models generate images based on textual descriptions.";
            case VARIATIONAL_AUTOENCODERS -> "Variational Autoencoders (VAEs) are used for generating new data samples.";
            case MUSIC_GENERATION -> "Music Generation models create new music compositions.";
            case TEXT_GENERATION -> "Text Generation models produce new text based on given input.";
        };
    }
}
