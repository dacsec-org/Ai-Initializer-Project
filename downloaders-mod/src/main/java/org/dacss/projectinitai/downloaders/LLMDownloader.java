package org.dacss.projectinitai.downloaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * <h1>{@link LLMDownloader}</h1>
 */
public class LLMDownloader {

    private static final Logger log = LoggerFactory.getLogger(LLMDownloader.class);
    private static final String USER_HOME = System.getProperty("user.home");
    private static String baseUrl;

    public LLMDownloader(String baseUrl) {
        LLMDownloader.baseUrl = baseUrl;
    }

    private static List<FileDownloadInfo> getFilesToDownload() {
        return List.of(
                new FileDownloadInfo(".gitattributes", baseUrl + ".gitattributes?download=true"),
                new FileDownloadInfo("LICENSE", baseUrl + "LICENSE?download=true"),
                new FileDownloadInfo("README.md", baseUrl + "README.md?download=true"),
                new FileDownloadInfo("generation_config.json", baseUrl + "generation_config.json?download=true"),
                new FileDownloadInfo("merges.txt", baseUrl + "merges.txt?download=true"),
                new FileDownloadInfo("model.safetensors", baseUrl + "model.safetensors?download=true"),
                new FileDownloadInfo("tokenizer.json", baseUrl + "tokenizer.json?download=true"),
                new FileDownloadInfo("tokenizer_config.json", baseUrl + "tokenizer_config.json?download=true"),
                new FileDownloadInfo("vocab.json", baseUrl + "vocab.json?download=true")
        );
    }

    public static Flux<Object> downloadLLM(DownloadAction action, String llmName) {
        if (action == DownloadAction.DOWNLOAD_LLM_MODEL) {
            int parallelism = Runtime.getRuntime().availableProcessors();
            return Flux.fromIterable(getFilesToDownload())
                    .parallel(parallelism)
                    .runOn(Schedulers.boundedElastic())
                    .flatMap(fileInfo -> Flux.create(sink -> {
                        HttpClient client = HttpClient.newBuilder()
                                .followRedirects(HttpClient.Redirect.ALWAYS)
                                .build();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(fileInfo.getFileUrl()))
                                .GET()
                                .build();

                        CompletableFuture<HttpResponse<InputStream>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream());
                        // Process the response
                        responseFuture.thenAccept(response -> {
                            if (response.statusCode() == 200) {
                                try (InputStream inputStream = response.body()) {
                                    Path targetPath = getTargetPath(llmName, fileInfo.getFileName());
                                    createDirectory(targetPath.getParent().toString()).publishOn(Schedulers.boundedElastic()).doOnComplete(() -> {
                                                // Write the file to disk in an asynchronous manner with a buffer size of 1024 bytes
                                                try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(targetPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                                    int bytesRead;
                                                    // Read the input stream and write to the file channel
                                                    while ((bytesRead = inputStream.read(buffer.array())) != -1) {
                                                        buffer.limit(bytesRead);
                                                        fileChannel.write(buffer, fileChannel.size()).get();
                                                        buffer.clear();
                                                    }
                                                    // Log the download completion
                                                    log.info("Downloaded: {}", fileInfo.getFileName());
                                                    // Emit the download completion event
                                                    sink.next("Downloaded: " + fileInfo.getFileName());
                                                    sink.complete();
                                                } catch (IOException | InterruptedException | java.util.concurrent.ExecutionException llmDownloaderExc) {
                                                    log.error("Error writing file: {}", fileInfo.getFileName(), llmDownloaderExc);
                                                    sink.next("Failed to download: " + fileInfo.getFileName() + " (Exception: " + llmDownloaderExc.getMessage() + ")");
                                                    sink.complete();
                                                }
                                            }).subscribe();
                                } catch (IOException llmDownloaderInputStreamExc) {
                                    log.error("Error processing input stream for file: {}", fileInfo.getFileName(), llmDownloaderInputStreamExc);
                                    sink.next("Failed to download: " + fileInfo.getFileName() + " (Exception: " + llmDownloaderInputStreamExc.getMessage() + ")");
                                    sink.complete();
                                }
                            } else {
                                log.error("Failed to download: {} (HTTP {})", fileInfo.getFileName(), response.statusCode());
                                sink.next("Failed to download: " + fileInfo.getFileName() + " (HTTP " + response.statusCode() + ")");
                                sink.complete();
                            }
                        }).exceptionally(exc -> {
                            log.error("Error during HTTP request for file: {}", fileInfo.getFileName(), exc);
                            sink.next("Failed to download: " + fileInfo.getFileName() + " (Exception: " + exc.getMessage() + ")");
                            sink.complete();
                            return null;
                        });
                    })).sequential();
        } else {
            return Flux.error(new UnsupportedOperationException("Unsupported download action: " + action));
        }
    }

    private static Flux<Object> createDirectory(String path) {
        return Flux.create(sink -> {
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    log.error("Failed to create directory: {}", path);
                } else {
                    log.info("Directory created successfully: {}", path);
                }
            } else {
                log.info("Directory already exists: {}", path);
            }
            sink.next(new Object());
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private static Path getTargetPath(String llmName, String fileName) {
        String subDir;
        if (fileName.endsWith(".json") || fileName.endsWith(".txt")) {
            subDir = "configs";
        } else if (fileName.equals(".gitattributes") || fileName.equals("LICENSE") || fileName.equals("README.md")) {
            subDir = "info";
        } else if (fileName.startsWith("model.")) {
            subDir = "model";
        } else {
            subDir = "checksums";
        }
        return Paths.get(USER_HOME).resolve(Paths.get(".project-ai-initializer/models", llmName, subDir, fileName)).normalize();
    }
}
