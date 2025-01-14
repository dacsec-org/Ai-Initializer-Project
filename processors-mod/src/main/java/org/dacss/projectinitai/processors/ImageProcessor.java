package org.dacss.projectinitai.processors;

import com.vaadin.flow.component.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.dacss.projectinitai.interfaces.StringProcessingAdviserIface;
import org.springframework.stereotype.Component;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;


@Slf4j
@Component
public class ImageProcessor implements StringProcessingAdviserIface {

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

            return "Image processed and saved as " + outputFilePath + ". Base64: " + base64Image;
        } catch (IOException e) {
            log.error("Error processing image", e);
            Notification.show("Error processing image");
            return "Error processing image";
        }
    }

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
