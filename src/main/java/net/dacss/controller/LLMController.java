package net.dacss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.dacss.service.LLMService;

@RestController
@RequestMapping("/api/llm")
public class LLMController {

    @Autowired
    private LLMService llmService;

    @GetMapping("/chat")
    public String chat(@RequestBody String message) {
        return llmService.chat(message);
    }

    @PostMapping("/embedding")
    public String embedding(@RequestBody String text) {
        return llmService.embedding(text);
    }

    @PostMapping("/text-to-image")
    public String textToImage(@RequestBody String text) {
        return llmService.textToImage(text);
    }

    @PostMapping("/audio-transcription")
    public String audioTranscription(@RequestBody byte[] audio) {
        return llmService.audioTranscription(audio);
    }

    @PostMapping("/text-to-speech")
    public byte[] textToSpeech(@RequestBody String text) {
        return llmService.textToSpeech(text);
    }

    @PostMapping("/moderation")
    public String moderation(@RequestBody String content) {
        return llmService.moderation(content);
    }
}
