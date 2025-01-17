package org.dacss.projectinitai.processors.components;
/**/
import org.dacss.projectinitai.processors.interfaces.ByteProcessingAdviserIface;
/**/
import com.vaadin.flow.component.notification.Notification;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link AudioProcessorComp}</h1>
 * Audio Processor Component.
 */
@Component
public class AudioProcessorComp implements ByteProcessingAdviserIface {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AudioProcessorComp.class);

    /**
     * {@link #processBytes(byte[])}
     * Process byte array data.
     *
     * @param byteInputOutput
     * @return
     */
    @Override
    public byte[] processBytes(byte[] byteInputOutput) {
        try {
            // Convert byte array to AudioInputStream
            AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(byteInputOutput),
                    getAudioFormat(),
                    byteInputOutput.length / getAudioFormat().getFrameSize()
            );

            // Convert AudioInputStream back to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error processing audio data: ", e);
            Notification.show(STR."Error processing audio data: \{e.getMessage()}");
            return null;
        }
    }

    /**
     * {@link #getAudioFormat()}
     *
     * @return AudioFormat
     */
    private AudioFormat getAudioFormat() {
        return new AudioFormat(16000, 16, 1, true, true);
    }

    /**
     * {@link #getFileInputOutputLocation(String)}
     * Get file input/output location.
     *
     * @param filePath the file path
     * @return String
     */
    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    /**
     * {@link #getInputOutputDevice()}
     * Get input/output device information.
     *
     * @return String
     */
    public String getInputOutputDevice() {
        return "Audio input/output device information is not available.";
    }
}
