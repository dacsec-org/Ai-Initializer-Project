package org.dacss.projectinitai.loaders.handlers;

import org.dacss.projectinitai.utilities.DirectoryFileUtil;
import org.dacss.projectinitai.utilities.files.ZipUtil;

import java.io.IOException;

public class DirectoryFileHandler {

    private ZipUtil zipUtil;
    private DirectoryFileUtil directoryFileUtil;
    private TarUtil tarUntarUtil;

    public DirectoryFileHandler() throws IOException {
        /*fixme: zipDirectory is looking for 2 args*/
        zipUtil = ZipUtil.zipDirectory();
        DirectoryFileUtil.createDirectory("directoryName");
        tarUntarUtil = new TarUntarUtil();
    }
}
