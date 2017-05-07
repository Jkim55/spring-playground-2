package com.jikim.unit_6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordCounter.caseSensitive=false",
        "wordCounter.words.skip[0]=the",
        "wordCounter.words.skip[1]=an",
        "wordCounter.words.skip[2]=a"
})
public class WordConfigTest {
    @Autowired
    private WordConfig wordConfig;

    @Test
    public void testPropertiesAreMappedCorrectly() {
        assertThat(wordConfig.getCaseSensitive(), equalTo(false));
        assertThat(wordConfig.getWords().getSkip(), equalTo(Arrays.asList("the", "an", "a")));
    }

}