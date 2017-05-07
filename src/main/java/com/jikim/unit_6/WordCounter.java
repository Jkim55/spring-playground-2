package com.jikim.unit_6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class WordCounter {
    private final WordConfig wordConfig;

    public WordCounter(WordConfig wordConfig) {
        this.wordConfig = wordConfig;
    }

    public Map<String, Integer> count(String sentence) {
        Map<String, Integer> wordTally = new HashMap<>();

        String[] wordArray = wordConfig.getCaseSensitive() ? sentence.split(" ") : sentence.toLowerCase().split(" ");

        Stream.of(wordArray)
                .filter(word ->!wordConfig.getWords().getSkip().contains(word))
                .filter(word -> {
                    if(wordTally.containsKey(word)) {
                        wordTally.put(word, wordTally.get(word) + 1);
                        return false;
                    }
                    return true;
                })
                .forEach(word -> wordTally.put(word, 1));
        return wordTally;
    }

}