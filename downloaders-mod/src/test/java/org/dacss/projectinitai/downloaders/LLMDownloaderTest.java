package org.dacss.projectinitai.downloaders;

import org.dacss.projectinitai.directories.DirectoriesIface;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LLMDownloaderTest {

    private DirectoriesIface directoriesIface;
    private DownloadersIface downloader;
    private String rootDir;

    @BeforeClass
    public void setUp() {
        String baseUrl = "https://huggingface.co/Qwen/Qwen2.5-0.5B/resolve/main/";
        rootDir = "home/pai/.project-ai-initializer";
        downloader = new LLMDownloader(baseUrl, rootDir, directoriesIface);
    }

    @Test
    public void testDownloadLLMModel() {
        String llmName = "Qwen2.5-0.5B";
        Set<String> expectedFiles = new HashSet<>(Set.of(
                "Downloaded: .gitattributes",
                "Downloaded: LICENSE",
                "Downloaded: README.md",
                "Downloaded: config.json",
                "Downloaded: generation_config.json",
                "Downloaded: merges.txt",
                "Downloaded: model.safetensors",
                "Downloaded: tokenizer.json",
                "Downloaded: tokenizer_config.json",
                "Downloaded: vocab.json"
        ));

        Flux<Object> flux = downloader.download(llmName);

        StepVerifier.create(flux.collectList())
                .expectNextMatches(downloadedFiles -> {
                    Set<String> downloadedSet = downloadedFiles.stream()
                            .map(Object::toString)
                            .collect(Collectors.toSet());
                    return downloadedSet.equals(expectedFiles);
                })
                .expectComplete()
                .verify();
    }
}
