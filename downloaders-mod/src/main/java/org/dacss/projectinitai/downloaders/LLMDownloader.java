package org.dacss.projectinitai.downloaders;

import org.dacss.projectinitai.directories.DirectoriesIface;
import org.dacss.projectinitai.directories.DirectoryActions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
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
    private static DirectoriesIface directoriesIface;
    private static String baseUrl;

    public LLMDownloader(String baseUrl, DirectoriesIface directoriesIface) {
        LLMDownloader.baseUrl = baseUrl;
        LLMDownloader.directoriesIface = directoriesIface;
    }

    private static List<FileDownloadInfo> getFilesToDownload() {
        return List.of(
                new FileDownloadInfo(".gitattributes", baseUrl + ".gitattributes?download=true"),
                new FileDownloadInfo("LICENSE", baseUrl + "LICENSE?download=true"),
                new FileDownloadInfo("README.md", baseUrl + "README.md?download=true"),
                new FileDownloadInfo("config.json", baseUrl + "config.json?download=true"),
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
            return Flux.fromIterable(getFilesToDownload())
                    .flatMap(fileInfo -> Flux.create(sink -> {
                        HttpClient client = HttpClient.newBuilder()
                                .followRedirects(HttpClient.Redirect.ALWAYS)
                                .build();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(fileInfo.getFileUrl()))
                                .GET()
                                .build();

                        CompletableFuture<HttpResponse<InputStream>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream());

                        responseFuture.thenAccept(response -> {
                            if (response.statusCode() == 200) {
                                try (InputStream inputStream = response.body()) {
                                    String subDir;
                                    if (fileInfo.getFileName().endsWith(".json") || fileInfo.getFileName().endsWith(".txt")) {
                                        subDir = "configs";
                                    } else if (fileInfo.getFileName().equals(".gitattributes") || fileInfo.getFileName().equals("LICENSE") || fileInfo.getFileName().equals("README.md")) {
                                        subDir = "info";
                                    } else if (fileInfo.getFileName().startsWith("model.")) {
                                        subDir = "model";
                                    } else {
                                        subDir = "checksums";
                                    }
                                    Path targetPath = Path.of(System.getProperty("user.home"), ".project-ai-initializer/models", llmName, subDir, fileInfo.getFileName()).normalize();
                                    directoriesIface.processDirFile(DirectoryActions.CREATE_DIRECTORY, targetPath.getParent().toString(), null)
                                            .publishOn(Schedulers.boundedElastic())
                                            .doOnComplete(() -> {
                                                try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(targetPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                                                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                                                    int bytesRead;
                                                    while ((bytesRead = inputStream.read(buffer.array())) != -1) {
                                                        buffer.limit(bytesRead);
                                                        fileChannel.write(buffer, fileChannel.size()).get();
                                                        buffer.clear();
                                                    }
                                                    log.info("Downloaded: {}", fileInfo.getFileName());
                                                    sink.next("Downloaded: " + fileInfo.getFileName());
                                                    sink.complete();
                                                } catch (IOException | InterruptedException | java.util.concurrent.ExecutionException llmDownloaderExc) {
                                                    sink.error(llmDownloaderExc);
                                                }
                                            }).subscribe();
                                } catch (IOException llmDownloaderExc) {
                                    sink.error(llmDownloaderExc);
                                }
                            } else {
                                sink.error(new IOException("Failed to download: " + fileInfo.getFileName() + " (HTTP " + response.statusCode() + ")"));
                            }
                        }).exceptionally(exc -> {
                            sink.error(exc);
                            return null;
                        });
                    }));
        } else {
            return Flux.error(new UnsupportedOperationException("Unsupported download action: " + action));
        }
    }
}
