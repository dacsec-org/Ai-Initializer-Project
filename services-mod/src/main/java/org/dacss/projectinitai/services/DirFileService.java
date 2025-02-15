//package org.dacss.projectinitai.services;
//

//import org.dacss.projectinitai.directories.DirectoriesIface;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import static org.dacss.projectinitai.directories.DirFileUtil.createDirectory;
//import static org.dacss.projectinitai.directories.DirFileUtil.createFile;
//import static org.dacss.projectinitai.directories.DestroyDirFileUtil.deleteDirectory;
//import static org.dacss.projectinitai.directories.DestroyDirFileUtil.deleteFile;
//
///**
// * <h1>{@link DirFileService}</h1>
// */
//@Service
//@Bridge("dir-file-service")
//public class DirFileService implements DirectoriesIface {
//
//    private static final Logger log = LoggerFactory.getLogger(DirFileService.class);
//
//    @Override
//    public void processDirFileAction(String action, String path, String fileName) {
//        try {
//            switch (action) {
//                case "create_directory":
//                    createDirectory(path);
//                    break;
//                case "create_file":
//                    createFile(path, fileName);
//                    break;
//                case "delete_directory":
//                    deleteDirectory(path);
//                    break;
//                case "delete_file":
//                    deleteFile(path, fileName);
//                    break;
//            }
//            log.info(GREEN + "From 'DirFileService' Operation completed: {} on {} with {}" + RESET, action, path, fileName);
//        } catch (Exception dirFileExc) {
//            log.error(RED + "From 'DirFileService' Error handling operation: {}" + RESET, action, dirFileExc);
//        }
//    }
//}
