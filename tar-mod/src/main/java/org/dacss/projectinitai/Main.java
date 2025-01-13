package org.dacss.projectinitai;

import org.dacss.projectinitai.handlers.TarHandler;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File tarFile = new File("path/to/your/archive.tar");
        File destDir = new File("path/to/extracted/files");

        TarHandler tarHandler = new TarHandler();

        try {
            tarHandler.extractAndDestroyTar(tarFile, destDir);
            System.out.println("Tar file extracted and deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
