package net.dacss.controllers;

import net.dacss.interfaces.ControllsIface;
import net.dacss.services.ControllsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class will provide the REST API endpoints for interacting with the LLM
 */
@RestController
@RequestMapping("/api/llm")
public class ControllsController implements ControllsIface {

    /**
     * The LLM services
     */
    private final ControllsService llmService;

    /**
     * Constructor for the LLM controllers
     * @param llmService - the LLM services
     */
    public ControllsController(ControllsService llmService) {
        this.llmService = llmService;
    }

    /**
     * This method will interact with the LLM
     * @param message - the message to be sent to the LLM
     * @return - the response from the LLM
     */
    @GetMapping("/chat")
    public String chat(@RequestBody String message) {
        return llmService.chat(message);
    }

    /**
     * This method will provide general chat and advisory services
     * @param text - the text to be sent to the LLM
     * @return - the response from the LLM
     */
    @PostMapping("/embedding")
    public String embedding(@RequestBody String text) {
        return llmService.embedding(text);
    }

    /**
     * This method will convert text to an image
     * @param text - the text to be converted to an image
     * @return - the image generated from the text
     */
    @PostMapping("/text-to-image")
    public String textToImage(@RequestBody String text) {
        return llmService.textToImage(text);
    }

    /**
     * This method will provide audio transcription services
     * @param audio - the audio to be transcribed
     * @return - the transcription of the audio
     */
    @PostMapping("/audio-transcription")
    public String audioTranscription(@RequestBody byte[] audio) {
        return llmService.audioTranscription(audio);
    }

    /**
     * This method will provide text to speech services
     * @param text - the text to be converted to speech
     * @return - the speech generated from the text
     */
    @PostMapping("/text-to-speech")
    public byte[] textToSpeech(@RequestBody String text) {
        return llmService.textToSpeech(text);
    }

    /**
     * This method will provide advisory services
     * @param content - the content to be advised on
     * @return - the response from the LLM
     */
    @PostMapping("/moderation")
    public String moderation(@RequestBody String content) {
        return llmService.moderation(content);
    }

    /**
     * Override the interactWithLLM method via the ControllsIface interface
     * @param input - the input to be sent to the LLM
     * @return - the response from the LLM
     */
    @Override
    public String interactWithLLM(String input) {
        return "";
    }
}
