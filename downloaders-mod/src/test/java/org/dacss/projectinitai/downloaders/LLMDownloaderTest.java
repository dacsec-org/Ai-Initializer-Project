//package org.dacss.projectinitai.downloaders;
//
//import org.dacss.projectinitai.directories.DirFileUtil;
//import org.dacss.projectinitai.directories.DirectoriesIface;
//import org.dacss.projectinitai.directories.DirectoryActions;
//import org.dacss.projectinitai.downloaders.DownloadAction;
//import org.dacss.projectinitai.downloaders.DownloadersIface;
//import org.dacss.projectinitai.downloaders.LLMDownloader;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * <h1>{@link LLMDownloaderTest}</h1>
// * Test class for {@link LLMDownloader}.
// * <p>
// * Methods under test:
// * <ul>
// *     <li>{@link LLMDownloader#download(DownloadAction, String)}</li>
// * </ul>
// */
//public class LLMDownloaderTest {
//
//    private DirectoriesIface directoriesIface;
//    private DownloadersIface downloader;
//    private DownloadAction action;
//
//    @BeforeClass
//    public void setUp() {
//        String baseUrl = "https://huggingface.co/Qwen/Qwen2.5-0.5B/resolve/main/";
//        directoriesIface = new DirFileUtil();
//        downloader = new LLMDownloader(baseUrl, directoriesIface);
//        action = DownloadAction.DOWNLOAD_LLM_MODEL;
//    }
//
//    @Test
//    public void testDownloadLLMModel() {
//        String llmName = "Qwen2.5-0.5B";
//        Set<String> expectedFiles = new HashSet<>(Set.of(
//                "Downloaded: .gitattributes",
//                "Downloaded: LICENSE",
//                "Downloaded: README.md",
//                "Downloaded: configs.json",
//                "Downloaded: generation_config.json",
//                "Downloaded: merges.txt",
//                "Downloaded: model.safetensors",
//                "Downloaded: tokenizer.json",
//                "Downloaded: tokenizer_config.json",
//                "Downloaded: vocab.json"
//        ));
//
//        Flux<Object> flux = downloader.download(action, llmName);
//
//        StepVerifier.create(flux.collectList())
//                .expectNextMatches(downloadedFiles -> {
//                    Set<String> downloadedSet = downloadedFiles.stream()
//                            .map(Object::toString)
//                            .collect(Collectors.toSet());
//                    return downloadedSet.equals(expectedFiles);
//                })
//                .expectComplete()
//                .verify();
//    }
//}
