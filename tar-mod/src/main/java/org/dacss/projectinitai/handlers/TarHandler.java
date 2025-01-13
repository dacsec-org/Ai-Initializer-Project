package org.dacss.projectinitai.handlers;

import org.dacss.projectinitai.services.TarService;

import java.io.File;
import java.io.IOException;

public class TarHandler {

    private final TarService tarService;

    public TarHandler() {
        this.tarService = new TarService();
    }

    public void createTar(File sourceDir, File tarFile) throws IOException {
        tarService.createTar(sourceDir, tarFile);
    }

    public void extractTar(File tarFile, File destDir) throws IOException {
        tarService.extractTar(tarFile, destDir);
    }

    public void deleteTar(File tarFile) throws IOException {
        tarService.deleteTar(tarFile);
    }

    public void extractAndDestroyTar(File tarFile, File destDir) throws IOException {
        tarService.extractAndDestroyTar(tarFile, destDir);
    }
}
