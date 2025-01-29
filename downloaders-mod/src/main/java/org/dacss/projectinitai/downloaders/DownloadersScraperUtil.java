package org.dacss.projectinitai.downloaders;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadersScraperUtil {

    private static final Logger log = LoggerFactory.getLogger(DownloadersScraperUtil.class);

    public static void downloadLLMJsonFile() throws IOException {
        String url = "https://huggingface.co/api/models";
        log.info("Requesting URL: {}", url);

        try {
            Connection connection = Jsoup.connect(url);
            Connection.Response response = connection.ignoreContentType(true).execute();

            log.info("Response Status: {}", response.statusCode());
            log.info("Response Body: {}", response.body());

            File file = new File("downloaders-mod/llm.json");
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(response.body());
            }
        } catch (IOException e) {
            log.error("Failed to download LLM JSON file from Hugging Face", e);
            throw e;
        }
    }
}
