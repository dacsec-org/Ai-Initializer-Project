package org.dacss.projectinitai.generative;

import lombok.Getter;

@Getter
public enum Generative {

    DEEPFAKES,
    GENERATIVE_ADVERSARIAL_NETWORKS,
    TEXT_TO_IMAGE,
    TEXT_TO_SPEECH,
    VARIATIONAL_AUTOENCODERS,
    MUSIC_GENERATION,
    TEXT_GENERATION;
    String value;

    Generative() {}
}
