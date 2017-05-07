package com.jikim.unit_6;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    WordCounter wordCounter;

    @Before
    public void setup() {
        String sentence = "Blue cows blue bell";
        Map<String, Integer> testCount = wordCounter.count(sentence);
        Map<String, Integer> mockCount = new HashMap<>();
        mockCount.put("marcel", 1);
        mockCount.put("the", 1);
        mockCount.put("shell", 2);
        mockCount.put("is", 1);
        mockCount.put("a", 1);
        when(wordCounter.count("Marcel the Shell is a shell")).thenReturn(mockCount);
    }

    @Test
    public void itPostsAString_ReturnsATally() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Marcel the Shell is a shell");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"marcel\":1,\"the\":1,\"a\":1,\"shell\":2,\"is\":1}"));
    }

}