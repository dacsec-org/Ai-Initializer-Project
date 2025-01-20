//package org.dacss.projectinitai.rags.utilities;
///**/
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * <h1>{@link RAGUpdaterUtil}</h1>
// */
//public class RAGUpdaterUtil {
//
//    private static final Logger log = LoggerFactory.getLogger(RAGUpdaterUtil.class);
//
//    public void updateRAG(String source, String destination) throws IOException {
//        Path sourcePath = Paths.get(source);
//        Path destinationPath = Paths.get(destination);
//        updateFiles(sourcePath, destinationPath);
//    }
//
//    private void updateFiles(Path sourcePath, Path destinationPath) throws IOException {
//        Files.walk(sourcePath)
//                //todo:'Stream<Path>' used without 'try'-with-resources statement
//            .forEach(source -> {
//                Path destination = destinationPath.resolve(sourcePath.relativize(source));
//                try {
//                    if (Files.exists(destination)) {
//                        Files.delete(destination);
//                    }
//                    Files.copy(source, destination);
//                } catch (IOException updateRagExc) {
//                    log.error(STR."Failed to update file: \{source}", updateRagExc);
//                }
//            });
//    }
//}
