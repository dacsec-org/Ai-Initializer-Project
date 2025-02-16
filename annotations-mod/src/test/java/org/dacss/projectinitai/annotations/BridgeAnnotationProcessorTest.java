//package org.dacss.projectinitai.annotations;
//
//import org.junit.jupiter.api.Test;
//
//import javax.tools.JavaCompiler;
//import javax.tools.ToolProvider;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class BridgeAnnotationProcessorTest {
//
//    @Test
//    void testAnnotationProcessorProcessesBridgeAnnotation() throws Exception {
//        // Create temporary directories for source and output files
//        Path tempDir = Files.createTempDirectory("bridge_processor_test");
//        Path sourcePath = tempDir.resolve("MockService.java");
//
//        // Define a test service in source code
//        String sourceCode = """
//                package org.dacss.projectinitai.test;
//
//                import org.dacss.projectinitai.annotations.Bridge;
//
//                @Bridge("mock-service")
//                public class MockService { }
//                """;
//
//        // Write the source code to a file
//        Files.write(sourcePath, sourceCode.getBytes());
//
//        // Set up the Java Compiler and compile the source with the annotation processor
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        Path outputDir = Files.createTempDirectory("bridge_processor_output");
//
//        var task = compiler.getTask(
//                null,
//                null,
//                null,
//                java.util.List.of(
//                        "-d", outputDir.toString(), // Specify output directory
//                        "-processor", "org.dacss.projectinitai.annotations.BridgeAnnotationProcessor" // Use the created annotation processor
//                ),
//                null,
//                compiler.getStandardFileManager(null, null, null)
//                        .getJavaFileObjectsFromFiles(java.util.List.of(sourcePath.toFile()))
//        );
//
//        boolean success = task.call();
//        assertTrue(success, "Compilation must succeed");
//
//        // Verify the expected output (e.g., handler class generation)
//        Path generatedClassPath = outputDir.resolve("org/dacss/projectinitai/generated/MockServiceHandler.class");
//        assertTrue(Files.exists(generatedClassPath), "Generated class should exist for MockService.");
//    }
//}
