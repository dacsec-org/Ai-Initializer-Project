package org.dacss.projectinitai.contexts.generative;

/**
 * <h1>{@link Generative}</h1>
 * Enum class representing the different types of Generative AI techniques.
 * Each enum constant has a context message that provides a brief description of the purpose of the Generative AI technique.
 */
public enum Generative {
    DEEPFAKES,
    GENERATIVE_ADVERSARIAL_NETWORKS,
    TEXT_TO_IMAGE,
    VARIATIONAL_AUTOENCODERS,
    MUSIC_GENERATION,
    TEXT_GENERATION;

    public String getContextMessage() {
        return switch (this) {
            case DEEPFAKES -> "Your purpose is to create realistic fake videos or images. Use techniques to manipulate and generate media that appears authentic.";
            case GENERATIVE_ADVERSARIAL_NETWORKS -> "Your purpose is to generate new data samples using Generative Adversarial Networks (GANs). Focus on creating realistic and high-quality data.";
            case TEXT_TO_IMAGE -> "Your purpose is to generate images based on textual descriptions. Convert the given text into a corresponding visual representation.";
            case VARIATIONAL_AUTOENCODERS -> "Your purpose is to generate new data samples using Variational Autoencoders (VAEs). Focus on learning the underlying data distribution and producing similar samples.";
            case MUSIC_GENERATION -> "Your purpose is to create new music compositions. Use patterns and structures from existing music to generate original pieces.";
            case TEXT_GENERATION -> "Your purpose is to produce new text based on given input. Generate coherent and contextually relevant text that aligns with the provided prompt.";
        };
    }
}
