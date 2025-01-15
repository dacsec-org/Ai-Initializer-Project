package org.dacss.projectinitai.loaders.handlers;

import org.dacss.projectinitai.utilities.DirectoryFileUtil;
import org.dacss.projectinitai.utilities.files.ZipUtil;

public class DirectoryFileHandler {

    private ZipUtil zipUtil;
    private DirectoryFileUtil directoryFileUtil;
    private TarUntarUtil tarUntarUtil;

    public DirectoryFileHandler() {
        zipUtil = new ZipUtil();
        directoryFileUtil = new DirectoryFileUtil();
        tarUntarUtil = new TarUntarUtil();
    }
}
