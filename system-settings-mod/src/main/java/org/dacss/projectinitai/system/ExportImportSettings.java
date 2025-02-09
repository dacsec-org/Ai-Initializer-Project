package org.dacss.projectinitai.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <h1>{@link ExportImportSettings}</h1>
 * This class provides methods to export and import system settings.
 */
@Service
public class ExportImportSettings {

    /**
     * {@link Path} to the hard-copy settings file.
     */
    private static final Path SETTINGS_FILE_PATH = Paths.get("/etc/project-ai-initializer/project-ai-initializer.conf");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Flux<Object> exportSettings(Flux<SystemSettingsEntity> settings, Path filePath) {
        return settings.collectList()
                .flatMapMany(list -> {
                    try {
                        objectMapper.writeValue(filePath.toFile(), list);
                        return Flux.empty();
                    } catch (IOException exportSettingsExc) {
                        return Flux.error(exportSettingsExc);
                    }
                }).cast(Object.class);
    }

    public static Flux<Object> importSettings(Path filePath) {
        try {
            List<SystemSettingsEntity> settings = objectMapper.readValue(filePath.toFile(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, SystemSettingsEntity.class));
            return Flux.fromIterable(settings).cast(Object.class);
        } catch (IOException importSettingsExc) {
            return Flux.error(importSettingsExc);
        }
    }

    public Flux<Object> writeSettingsToFile(Flux<SystemSettingsEntity> settings) {
        return exportSettings(settings, SETTINGS_FILE_PATH);
    }

    public Flux<Object> readSettingsFromFile() {
        return importSettings(SETTINGS_FILE_PATH);
    }
}
