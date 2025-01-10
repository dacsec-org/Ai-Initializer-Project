package org.dacss.projectinitai.advisers.processors;

import com.vaadin.flow.component.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class AudioProcessor implements ByteProcessingAdviserIface {

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
            Notification.show("Error processing audio data: " + e.getMessage());
            return null;
        }
    }

    private AudioFormat getAudioFormat() {
        return new AudioFormat(16000, 16, 1, true, true);
    }

    public String getFileInputOutputLocation(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    public String getInputOutputDevice() {
        return "Audio input/output device information is not available.";
    }
}
