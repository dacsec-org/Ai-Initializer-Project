//package org.dacss.projectinitai.downloaders;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class SearchModelsTest {
//
//    private SearchModels searchModels;
//    private DownloadAction action;
//
//    @BeforeClass
//    public void setUp() {
//        searchModels = new SearchModels();
//        action = DownloadAction.SEARCH;
//    }
//
//    @Test
//    public void testSearchModels() {
//        String query = "qwen";
//        Flux<Object> flux = SearchModels.searchModels(action, query);
//
//        StepVerifier.create(flux.collectList())
//                .expectNextMatches(models -> {
//                    if (models.isEmpty()) {
//                        return false;
//                    }
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    try {
//                        for (Object model : models) {
//                            JsonNode jsonNode = objectMapper.readTree(model.toString());
//                            System.out.println("JSON Response: " + jsonNode.toPrettyString());
//                        }
//                        return true;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return false;
//                    }
//                })
//                .expectComplete()
//                .verify();
//    }
//}
