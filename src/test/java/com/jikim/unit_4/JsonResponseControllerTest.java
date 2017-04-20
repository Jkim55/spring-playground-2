package com.jikim.unit_4;

import com.jikim.unit_4.Controller.JsonResponseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JsonResponseController.class)
public class JsonResponseControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testObject() throws Exception {
        RequestBuilder getRequestObj = MockMvcRequestBuilders.get("/json/simple-object")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        this.mvc.perform(getRequestObj)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.frosting", is("Chai")))
                .andExpect(jsonPath("$.sprinkles", is("Coconut")));
    }

    @Test
    public void testArray() throws Exception {
        RequestBuilder getRequestArray = MockMvcRequestBuilders.get("/json/array")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        this.mvc.perform(getRequestArray)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].frosting", is("Earl Gray")))
                .andExpect(jsonPath("$[0].sprinkles", is("Lavender")));
    }

}
