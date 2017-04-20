package com.jikim.unit_3;

import com.jikim.unit_3.Controller.FormDataController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataController.class)
public class FormDataControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void canAccessIndividualParams () throws Exception {
        String term = "Corgies";
        String from = String.valueOf(new Random().nextInt());

        MockHttpServletRequestBuilder request1 = post("/fd/individual-example")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("t", term)
                .param("from", from);

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("%s searched from %s", term, from)));
    }

    @Test
    public void canAccessParamsAsMap () throws Exception {
        String type = "suede";
        String color = "dusty waterfall";

        MockHttpServletRequestBuilder request2 = post("/fd/map-example")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", type)
                .param("color", color);

        this.mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("{type=%s, color=%s}", type, color)));
    }

    @Test
    public void canAccessParamsAsObjects () throws Exception {
        String style = "Lace up ballet";
        String size = "7.5";

        MockHttpServletRequestBuilder request3 = post("/fd/object-example")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("style", style)
                .param("size", size);

        this.mvc.perform(request3)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("%s is available in %s", style, size)));
    }

    @Test
    public void canAccessParamAsRawBody () throws Exception {
        String brand = "MadeWell";
        String price = ("125.99");

        MockHttpServletRequestBuilder request4 = post("/fd/string-example")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("brand", brand)
                .param("price", price);

        this.mvc.perform(request4)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("brand=%s&price=%s", brand, price)));
    }
}
