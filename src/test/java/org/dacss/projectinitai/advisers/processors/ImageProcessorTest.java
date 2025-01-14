package org.dacss.projectinitai.advisers.processors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>{@link ImageProcessorTest}</h1>
 * Methods under test:
 * <ul>
 *     <li>{@link #processString_validImage()}</li>
 *     <li>{@link #processString_invalidImage()}</li>
 *     <li>{@link #convertToBase64()}</li>
 * </ul>
 */
class ImageProcessorTest {

    private ImageProcessor processor;
    private final String testImagePath = "test_image.png";
    private final String processedImagePath = "processed_image.png";

    @BeforeEach
    void setUp() throws IOException {
        processor = new ImageProcessor();
        // Create a test image file
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        File testImageFile = new File(testImagePath);
        ImageIO.write(image, "png", testImageFile);
    }

    @AfterEach
    void tearDown() {
        // Delete the test and processed image files
        new File(testImagePath).delete();
        new File(processedImagePath).delete();
    }

    @Test
    void processString_validImage() {
        String result = processor.processString(testImagePath);
        assertTrue(result.contains("Image processed and saved as"));
        assertTrue(result.contains("Base64:"));
        assertTrue(new File(processedImagePath).exists());
    }

    @Test
    void processString_invalidImage() {
        String result = processor.processString("invalid_image_path.png");
        assertEquals("Error processing image", result);
    }

    @Test
    void convertToBase64() throws IOException {
        BufferedImage image = ImageIO.read(new File(testImagePath));
        String base64Image = processor.convertToBase64(image);
        assertNotNull(base64Image);
        assertFalse(base64Image.isEmpty());
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
        assertTrue(decodedBytes.length > 0);
    }
}
