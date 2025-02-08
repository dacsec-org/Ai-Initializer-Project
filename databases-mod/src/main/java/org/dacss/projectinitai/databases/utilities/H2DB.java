package org.dacss.projectinitai.databases.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dacss.projectinitai.databases.LLMDetails;
import org.dacss.projectinitai.databases.repos.LLMDetailsRepository;

import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <h1>{@link H2DB}</h1>
 * class to handle H2 database operations.
 */
public class H2DB {

    private LLMDetailsRepository llmDetailsRepository;

    /**
     * was intended  to save the data from the json file to the H2 database.
     * could not get the data from the json file due to mapping issues.
     * @return
     */
    public Flux<LLMDetails> handleH2() {
        return Flux.create(sink -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<LLMDetails> llmDetailsList = objectMapper.readValue(
                        new File("downloaders-mod/llm.json"),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, LLMDetails.class)
                );
                llmDetailsRepository.saveAll(llmDetailsList);
                llmDetailsList.forEach(sink::next);
            } catch (IOException e) {
                sink.error(e);
            }
            sink.complete();
        });
    }
}
