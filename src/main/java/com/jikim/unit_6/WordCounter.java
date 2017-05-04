package com.jikim.unit_6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Configuration
public class WordCounter {

    @Bean
    public WordCounter getWordCounter() {
        return new WordCounter();
    }

    public Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordTally = new HashMap<>();

        List<String> words = Arrays.asList(sentence.toLowerCase().split(" "));

        Stream.of(sentence.toLowerCase().split(" "))
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
