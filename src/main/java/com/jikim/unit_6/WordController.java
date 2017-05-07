package com.jikim.unit_6;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordController {

    private WordCounterService wordCounterService;

    public WordController(WordCounterService wordCounterService) {
        this.wordCounterService = wordCounterService;
    }

    @PostMapping("/count")
    public Map<String, Integer> getWordCount(@RequestBody String sentence) {
        return wordCounterService.count(sentence);
    }

}