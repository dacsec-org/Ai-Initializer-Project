package org.dacss.projectinitai.utilities;
/**/

import java.io.File;
import java.io.IOException;

/**
 * <h1>{@link DirectoryFileUtil}</h1>
 * <p>
 * Utility class for creating directories and files.
 * </p>
 */
public class DirectoryFileUtil {

    /**
     * Creates a directory.
     *
     * @param path the directory path
     * @throws IOException if an I/O error occurs
     */
    public static void createDirectory(String path) throws IOException {
        File dir;
        try {
            dir = new File(path);
            //todo: implement the logic to create the directory
        } catch (Exception dirCreateExc) {
            throw new IOException(STR."Failed to create directory: \{path}");
        }
    }

    /**
     * Creates a file.
     *
     * @param dirPath  the directory path
     * @param fileName the file name
     * @throws IOException if an I/O error occurs
     */
    public static void createFile(String dirPath, String fileName) throws IOException {
        File file;
        try {
            file = new File(dirPath, fileName);
            //todo: implement the logic to create the file
        } catch (Exception fileCreateExc) {
            throw new IOException(STR."Failed to create file: \{fileName}");
        }
    }
}
