package org.dacss.projectinitai.generative;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenerativeTest {

    @Test
    void testEnumValues() {
        Generative[] expectedValues = {
            Generative.DEEPFAKES,
            Generative.GENERATIVE_ADVERSARIAL_NETWORKS,
            Generative.TEXT_TO_IMAGE,
            Generative.VARIATIONAL_AUTOENCODERS,
            Generative.MUSIC_GENERATION,
            Generative.TEXT_GENERATION
        };
        assertArrayEquals(expectedValues, Generative.values());
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Generative.DEEPFAKES, Generative.valueOf("DEEPFAKES"));
        assertEquals(Generative.GENERATIVE_ADVERSARIAL_NETWORKS, Generative.valueOf("GENERATIVE_ADVERSARIAL_NETWORKS"));
        assertEquals(Generative.TEXT_TO_IMAGE, Generative.valueOf("TEXT_TO_IMAGE"));
        assertEquals(Generative.VARIATIONAL_AUTOENCODERS, Generative.valueOf("VARIATIONAL_AUTOENCODERS"));
        assertEquals(Generative.MUSIC_GENERATION, Generative.valueOf("MUSIC_GENERATION"));
        assertEquals(Generative.TEXT_GENERATION, Generative.valueOf("TEXT_GENERATION"));
    }

    @Test
    void testGetContextMessage() {
        assertEquals("Deepfakes involve creating realistic fake videos or images.", Generative.DEEPFAKES.getContextMessage());
        assertEquals("Generative Adversarial Networks (GANs) are used to generate new data samples.", Generative.GENERATIVE_ADVERSARIAL_NETWORKS.getContextMessage());
        assertEquals("Text-to-Image models generate images based on textual descriptions.", Generative.TEXT_TO_IMAGE.getContextMessage());
        assertEquals("Variational Autoencoders (VAEs) are used for generating new data samples.", Generative.VARIATIONAL_AUTOENCODERS.getContextMessage());
        assertEquals("Music Generation models create new music compositions.", Generative.MUSIC_GENERATION.getContextMessage());
        assertEquals("Text Generation models produce new text based on given input.", Generative.TEXT_GENERATION.getContextMessage());
    }
}
