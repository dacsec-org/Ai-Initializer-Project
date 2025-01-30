package org.dacss.projectinitai.databases.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dacss.projectinitai.databases.LLMDetails;
import org.dacss.projectinitai.databases.LLMDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <h1>{@link H2Util}</h1>
 * Utility class to handle H2 database operations.
 */
@Component
public class H2Util {

    private LLMDetailsRepository llmDetailsRepository;

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
