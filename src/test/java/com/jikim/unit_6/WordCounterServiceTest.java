package com.jikim.unit_6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class WordCounterServiceTest {

    @Autowired
    WordCounterService wordCounterService;

    @Test
    public void itCountsWords_ReturnsATally(){
        String sentence = "Blue cows blue bell";
        Map<String, Integer> testCount = wordCounterService.count(sentence);

        Map<String, Integer> mockCount = new HashMap<>();
        mockCount.put("blue", 2);
        mockCount.put("bell", 1);
        mockCount.put("cows", 1);

        assertThat(testCount, equalTo(mockCount));
    }

    @Test
    public void itUsesConfigClassToCountWords_ReturnsAFilteredTally(){
        String sentence = "The BROWN cow jumps over a brown fox";
        Map<String, Integer> testCount = wordCounterService.count(sentence);
        Map<String, Integer> mockCount = new HashMap<>();
        mockCount.put("brown", 2);
        mockCount.put("cow", 1);
        mockCount.put("jumps", 1);
        mockCount.put("over", 1);
        mockCount.put("fox", 1);

        assertThat(testCount, equalTo(mockCount));
    }

}