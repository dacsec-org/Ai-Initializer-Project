package org.dacss.projectinitai.tar;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <h1>{@link TarCompressorUtil}</h1>
 * Utility class for creating tar files.
 * This class provides methods to compress directories and files into a tar archive.
 */
@Component
public class TarCompressorUtil {

    private static final Logger log = LoggerFactory.getLogger(TarCompressorUtil.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    /**
     * <h3>{@link #TarCompressorUtil()}</h3>
     * Default 0-arg constructor.
     */
    TarCompressorUtil() {}

    /**
     * <h3>{@link #createTarFile()}</h3>
     * Creates a tar file from the specified source directory.
     * This method compresses the contents of the source directory into a tar archive.
     *
     * @return a {@link Flux} that emits a message indicating the success or failure of the tar file creation
     * @throws IOException if an I/O error occurs during the tar file creation
     */
    static Flux<Object> createTarFile() {
        return Flux.create(sink -> {
            File sourceDir = new File("/path/to/source");
            File tarFile = new File("/path/to/tarfile.tar");
            try (FileOutputStream fos = new FileOutputStream(tarFile);
                 TarArchiveOutputStream tos = new TarArchiveOutputStream(fos)) {
                addFilesToTar(tos, sourceDir, "");
                sink.next(tarFile.exists() ? "Tar file created successfully" : "Failed to create tar file");
                sink.complete();
            } catch (IOException e) {
                sink.error(e);
            }
        });
    }

    /**
     * <h3>{@link #addFilesToTar(TarArchiveOutputStream, File, String)}</h3>
     * Adds files to the tar archive.
     * This method recursively adds files and directories to the tar archive.
     *
     * @param tos    the TarArchiveOutputStream to write to
     * @param file   the file or directory to add
     * @param parent the parent directory path
     * @throws IOException if an I/O error occurs during the file addition
     */
    private static void addFilesToTar(TarArchiveOutputStream tos, File file, String parent) throws IOException {
        String entryName = parent + file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);
        tos.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    tos.write(buffer, 0, len);
                }
            }
            tos.closeArchiveEntry();
        } else if (file.isDirectory()) {
            tos.closeArchiveEntry();
            for (File childFile : Objects.requireNonNull(file.listFiles())) {
                addFilesToTar(tos, childFile, entryName + "/");
            }
        }
    }
}
