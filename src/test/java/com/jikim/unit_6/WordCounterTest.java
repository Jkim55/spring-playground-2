package com.jikim.unit_6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class WordCounterTest {

    @Autowired
    WordCounter wordCounter;

    @Test
    public void itCountsWords_ReturnsATally(){
        String sentence = "Blue cows blue bell";
        Map<String, Integer> testCount = wordCounter.count(sentence);
        Map<String, Integer> mockCount = new HashMap<>();
        mockCount.put("blue", 2);
        mockCount.put("bell", 1);
        mockCount.put("cows", 1);

        assertThat(testCount, equalTo(mockCount));
    }
}