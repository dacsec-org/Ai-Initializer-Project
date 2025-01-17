package org.dacss.projectinitai.downloaders;
/**/
import org.dacss.projectinitai.downloaders.services.HuggingFaceDownloaderService;
import org.dacss.projectinitai.directories.handlers.DirFileHandler;
/**/
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * <h1>{@link DownloadersModuleTest}</h1>
 * <p>
 *     Suite of tests for the downloaders-Mod.
 * </p>
 * Methods under test:
 * <ul>
 *     <li>{@link #testGetApiToken()}
 *     <li>{@link #testDownloadModel()}
 *     <li>{@link #testScrapeLLMLinks()}
 *     <li>{@link #testScrapeName()}
 *     <li>{@link #testScrapeDescription()}
 *     <li>{@link #testScrapeType()}
 *     <li>{@link #testScrapeAvailableSizes()}
 *     <li>{@link #testScrapePulls()}
 *     <li>{@link #testScrapeTags()}
 *     <li>{@link #testScrapeUpdated()}
 * </ul>
 */
public class DownloadersModuleTest {

    private HuggingFaceDownloaderService huggingFaceDownloaderService;
    private LLMLinkScraper llmLinkScraper;

    @BeforeMethod
    public void setUp() {
        DirFileHandler dirFileHandler = new DirFileHandler();
        huggingFaceDownloaderService = new HuggingFaceDownloaderService(dirFileHandler);
        llmLinkScraper = new LLMLinkScraper();
    }

    @AfterSuite
    public void tearDown() {
        // Add any necessary cleanup code here
    }

    @Test
    public void testGetApiToken() {
        String apiToken = huggingFaceDownloaderService.getApiToken();
        assertNotNull(apiToken, "API token should not be null");
        System.out.println("Test 'getApiToken()' passed: " + apiToken);
    }

    @Test(dependsOnMethods = "testGetApiToken")
    public void testDownloadModel() {
        boolean result = huggingFaceDownloaderService.downloadModel("test-model-id");
        assertTrue(result, "Model should be downloaded successfully");
        System.out.println(STR."Test 'downloadModel()' passed: \{result}");
    }

    @Test
    public void testScrapeLLMLinks() throws IOException {
        List<LLMS> llmsList = LLMLinkScraper.scrapeLLMLinks("https://huggingface.co/models");
        assertNotNull(llmsList, "LLMS list should not be null");
        assertFalse(llmsList.isEmpty(), "LLMS list should not be empty");
        System.out.println(STR."Test 'scrapeLLMLinks()' passed: \{llmsList.size()} items found");
    }

    @Test(dependsOnMethods = "testScrapeLLMLinks")
    public void testScrapeName() {
        String name = LLMLinkScraper.scrapeName("test-name");
        assertEquals(name, "test-name", "Name should be 'test-name'");
        System.out.println(STR."Test 'scrapeName()' passed: \{name}");
    }

    @Test(dependsOnMethods = "testScrapeName")
    public void testScrapeDescription() {
        String description = LLMLinkScraper.scrapeDescription("test-name test-description 123b");
        assertEquals(description, "test-description", "Description should be 'test-description'");
        System.out.println(STR."Test 'scrapeDescription()' passed: \{description}");
    }

    @Test(dependsOnMethods = "testScrapeDescription")
    public void testScrapeType() {
        String type = LLMLinkScraper.scrapeType("test-name test-type 123b");
        assertEquals(type, "test-type", "Type should be 'test-type'");
        System.out.println(STR."Test 'scrapeType()' passed: \{type}");
    }

    @Test(dependsOnMethods = "testScrapeType")
    public void testScrapeAvailableSizes() {
        String sizes = LLMLinkScraper.scrapeAvailableSizes("123b, 456b");
        assertEquals(sizes, "123b, 456b", "Sizes should be '123b, 456b'");
        System.out.println(STR."Test 'scrapeAvailableSizes()' passed: \{sizes}");
    }

    @Test(dependsOnMethods = "testScrapeAvailableSizes")
    public void testScrapePulls() {
        String pulls = LLMLinkScraper.scrapePulls("123K Pulls");
        assertEquals(pulls, "123K", "Pulls should be '123K'");
        System.out.println(STR."Test 'scrapePulls()' passed: \{pulls}");
    }

    @Test(dependsOnMethods = "testScrapePulls")
    public void testScrapeTags() {
        String tags = LLMLinkScraper.scrapeTags("123 Tags");
        assertEquals(tags, "123", "Tags should be '123'");
        System.out.println(STR."Test 'scrapeTags()' passed: \{tags}");
    }

    @Test(dependsOnMethods = "testScrapeTags")
    public void testScrapeUpdated() {
        String updated = LLMLinkScraper.scrapeUpdated("Updated 2023-10-01");
        assertEquals(updated, "2023-10-01", "Updated date should be '2023-10-01'");
        System.out.println(STR."Test 'scrapeUpdated()' passed: \{updated}");
    }
}
