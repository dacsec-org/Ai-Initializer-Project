package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.StringProcessingAdviserIface;
/**/
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * <h1>{@link ImageProcessorComp}</h1>
 * Image processor component class.
 */
@Component
public class ImageProcessorComp implements StringProcessingAdviserIface {

    private static final Logger log = LoggerFactory.getLogger(ImageProcessorComp.class);

    /**
     * {@link #processString(String)}.
     * Process image file.
     *
     * @param stringInputOutput Image file path to process.
     * @return Image processed and saved as a file.
     */
    @Override
    public String processString(String stringInputOutput) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File(stringInputOutput));
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

            return STR."Image processed and saved as \{outputFilePath}. Base64: \{base64Image}";
        } catch (IOException e) {
            log.error("Error processing image", e);
            Notification.show("Error processing image");
            return "Error processing image";
        }
    }

    /**
     * {@link #convertToBase64(BufferedImage)}.
     * Convert image to Base64.
     *
     * @param image Image to convert.
     * @return Image converted to Base64.
     */
    protected String convertToBase64(BufferedImage image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            log.error("Error converting image to Base64", e);
            Notification.show("Error converting image to Base64");
            return "";
        }
    }
}
