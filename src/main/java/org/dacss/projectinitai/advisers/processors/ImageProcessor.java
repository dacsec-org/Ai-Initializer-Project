package org.dacss.projectinitai.advisers.processors;

import com.vaadin.flow.component.notification.Notification;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * <h1>{@link ImageProcessor}</h1>
 * Image Processor Adviser.
 */
public class ImageProcessor implements ProcessingAdviserIface<String> {

    /**
     * {@link #process(String)}
     * @param inputOutput user-input, and ai-output to be processed.
     */
    @Override
    public String process(String inputOutput) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File(inputOutput));
            if (image == null) {
                return "Invalid image file";
            }

            // Convert to grayscale
            BufferedImage grayscaleImage = new BufferedImage(
                image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = grayscaleImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            // Resize the image to a fixed width and height (e.g., 100x100)
            Image resizedImage = grayscaleImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            BufferedImage resizedBufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
            g = resizedBufferedImage.createGraphics();
            g.drawImage(resizedImage, 0, 0, null);
            g.dispose();

            // Save the processed image
            String outputFilePath = "processed_image.png";
            ImageIO.write(resizedBufferedImage, "png", new File(outputFilePath));

            // Convert the processed image to Base64
            String base64Image = convertToBase64(resizedBufferedImage);

            return "Image processed and saved as " + outputFilePath + ". Base64: " + base64Image;
        } catch (IOException e) {
            Notification.show("Error processing image");
            return "Error processing image";
        }
    }

    /**
     * Converts a BufferedImage to a Base64 string.
     *
     * @param image the BufferedImage to be converted
     * @return the Base64 string representation of the image
     */
    private String convertToBase64(BufferedImage image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            Notification.show("Error converting image to Base64");
            return "";
        }
    }
}
