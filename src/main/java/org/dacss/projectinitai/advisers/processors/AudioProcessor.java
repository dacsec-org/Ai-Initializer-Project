package org.dacss.projectinitai.advisers.processors;

import com.vaadin.flow.component.notification.Notification;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link AudioProcessor}</h1>
 * Audio Processor for audio data.
 */
public class AudioProcessor implements ProcessingAdviserIface<byte[]> {

    /**
     * {@link #process(byte[])}
     * @param inputOutput user-input, and ai-output to be processed.
     */
    @Override
    public byte[] process(byte[] inputOutput) {
        try {
            // Convert byte array to AudioInputStream
            AudioInputStream audioInputStream = new AudioInputStream(
                    new ByteArrayInputStream(inputOutput),
                    getAudioFormat(),
                    inputOutput.length / getAudioFormat().getFrameSize()
            );

            // Convert AudioInputStream back to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, byteArrayOutputStream);
            inputOutput = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Notification.show("Error processing audio data: " + e.getMessage());
        }
        return inputOutput; // Return processed data
    }

    /**
     * {@link #getAudioFormat()}
     * @return AudioFormat
     */
    private AudioFormat getAudioFormat() {
        return new AudioFormat(16000, 16, 1, true, true);
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
        return "Audio input/output device information is not available.";
    }
}
