//package org.dacss.projectinitai.rags.utilities;
///**/
///**/
//import org.slf4j.Logger;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * <h1>{@link RAGClonerUtil}</h1>
// */
//public class RAGCreatorUtil {
//
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RAGCreatorUtil.class);
//
//    public void createRAG(String source, String destination) throws IOException {
//        Path sourcePath = Paths.get(source);
//        Path destinationPath = Paths.get(destination);
//        createRAGDirectory(destinationPath);
//        copyFiles(sourcePath, destinationPath);
//    }
//
//    private void createRAGDirectory(Path destinationPath) throws IOException {
//        if (!Files.exists(destinationPath)) {
//            Files.createDirectories(destinationPath);
//        }
//    }
//
//    private void copyFiles(Path sourcePath, Path destinationPath) throws IOException {
//        Files.walk(sourcePath)
//                //todo: 'Stream<Path>' used without 'try'-with-resources statement
//            .forEach(source -> {
//                Path destination = destinationPath.resolve(sourcePath.relativize(source));
//                try {
//                    Files.copy(source, destination);
//                } catch (IOException e) {
//                    log.error("Failed to copy file: " + source, e);
//                }
//            });
//    }
//}
