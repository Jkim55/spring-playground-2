package com.jikim.unit_6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class WordCounterTest {

    @Autowired
    WordCounter wordCounter;

    @Test
    public void testShouldWork() {
        assertThat(wordCounter.getWordCounter(), instanceOf(WordCounter.class));
    }

}