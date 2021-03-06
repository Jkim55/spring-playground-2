package com.jikim.unit_4;

import com.jikim.unit_4.Controller.CustomNestedJsonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomNestedJsonController.class)
public class CustomNestedJsonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void fetchDetailsOnSingleTrip() throws Exception {
        String tripResponse = getJson("/customNestedResponse.json");

        MockHttpServletRequestBuilder request = get("/trips/trip");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(tripResponse));
    }

    private String getJson(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

}
