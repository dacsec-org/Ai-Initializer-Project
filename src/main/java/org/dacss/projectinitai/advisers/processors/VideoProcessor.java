package org.dacss.projectinitai.advisers.processors;

import com.vaadin.flow.component.notification.Notification;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <h1>{@link VideoProcessor}</h1>
 * Processor class for video files.
 */
public class VideoProcessor implements ProcessingAdviserIface<byte[]> {

    /**
     * {@link #process(byte[])}
     * @param inputOutput user-input, and ai-output to be processed.
     * @return the processed data.
     */
    @Override
    public byte[] process(byte[] inputOutput) {
        try (InputStream inputStream = new ByteArrayInputStream(inputOutput);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Here we can process the buffer before writing it to the output stream
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            Notification.show("Error processing video data: " + e.getMessage());
            return null;
        }
    }

    /**
     * {@link #getFileInputOutputLocation(String)}
     * @param filePath the file path to be checked.
     * @return the file input/output location.
     */
    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    /**
     * {@link #getInputOutputDevice()}
     * @return the input/output device information.
     */
    public String getInputOutputDevice() {
        return "Video input/output device information is not available.";
    }
}
