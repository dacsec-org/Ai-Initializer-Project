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
//import java.util.Comparator;
//
///**
// * <h1>{@link RAGDestroyerUtil}</h1>
// */
//public class RAGDestroyerUtil {
//
//    private static final Logger log = LoggerFactory.getLogger(RAGDestroyerUtil.class);
//
//    public void destroyRAG(String ragPath) throws IOException {
//        Path path = Paths.get(ragPath);
//        deleteDirectory(path);
//    }
//
//    private void deleteDirectory(Path path) throws IOException {
//        if (Files.exists(path)) {
//            Files.walk(path)
//                    //todo:'Stream<Path>' used without 'try'-with-resources statement
//                .sorted(Comparator.reverseOrder())
//                .forEach(p -> {
//                    try {
//                        Files.delete(p);
//                    } catch (IOException destroyRagExc) {
//                        log.error(STR."Failed to delete file: \{p}", destroyRagExc);
//                    }
//                });
//        }
//    }
//}
