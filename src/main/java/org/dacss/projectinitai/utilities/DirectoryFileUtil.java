package org.dacss.projectinitai.utilities;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class DirectoryFileUtil {

    public static void createDirectory(String path) throws IOException {
        File dir;
        try {
            dir = new File(path);
        } catch (Exception e) {
            throw new IOException("Failed to create directory: " + path);
        }
    }

    public static void createFile(String dirPath, String fileName) throws IOException {
        File file;
        try {
            file = new File(dirPath, fileName);
        } catch (Exception e) {
            throw new IOException("Failed to create file: " + fileName);
        }
    }
}
