package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
/**/
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1>{@link VideoProcessorComp}</h1>
 * Processor for video responses.
 */
@Component
public class VideoProcessorComp implements ByteProcessingAdviserIface {

    private static final Logger log = LoggerFactory.getLogger(VideoProcessorComp.class);

    @Override
    public byte[] processBytes(byte[] byteInputOutput) {
        try (InputStream inputStream = new ByteArrayInputStream(byteInputOutput);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Here we can process the buffer before writing it to the output stream
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error processing video data: ", e);
            Notification.show(STR."Error processing video data: \{e.getMessage()}");
            return null;
        }
    }

    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    public String getInputOutputDevice() {
        return "Video input/output device information is not available.";
    }
}
