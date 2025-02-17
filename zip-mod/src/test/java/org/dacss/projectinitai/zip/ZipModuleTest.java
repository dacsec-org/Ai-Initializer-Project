//package org.dacss.projectinitai.zip;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class ZipModuleTest {
//
//    private Path zipFile;
//
//    @BeforeMethod
//    public void setUp() throws IOException {
//        zipFile = Paths.get("resources/test.zip");
//        // Ensure the zip file exists
//        if (!Files.exists(zipFile)) {
//            throw new IOException("Zip file does not exist: " + zipFile);
//        }
//    }
//
//    @Test
//    public void testExtractZip() {
//        Flux<Object> flux = new ZipExtractor(zipFile.toAbsolutePath().toString()).processZip(zipFile.toAbsolutePath().toString());
//
//        StepVerifier.create(flux)
//                .expectNextMatches(message -> message instanceof String && ((String) message).contains("Zip file extracted successfully"))
//                .expectComplete()
//                .verify();
//    }
//}
