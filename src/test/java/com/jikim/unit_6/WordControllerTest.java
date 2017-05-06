package com.jikim.unit_6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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

import static org.mockito.Matchers.any;
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
        when(wordCounter.getWordCounter()).thenReturn(new WordCounter());
    }

    @Test
    public void itPostsAString_ReturnsATally() throws Exception {
        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Marcel the Shell is a shell");

//        JsonObject mockTally = new JsonObject();
//        mockTally.addProperty("marcel", "1");
//        mockTally.addProperty("the", "1");
//        mockTally.addProperty("shell", "2");
//        mockTally.addProperty("is", "1");
//        mockTally.addProperty("a", "1");
//        Gson builder = new GsonBuilder().create();
//        String tallyString = builder.toJson(mockTally);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

}