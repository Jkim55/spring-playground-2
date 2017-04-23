package com.jikim.unit_4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jikim.unit_4.Controller.JSONRequestController;
import com.jikim.unit_4.Model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONRequestController.class)
public class JSONRequestControllerTest {

    @Autowired
    private MockMvc mvc;

    private String getJson(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    @Test
    public void postWithGsonBuilderObjectRequestBody() throws Exception {
        JsonObject person = new JsonObject();
        person.addProperty("FirstName", "Ji");
        person.addProperty("LastName", "Kim");

        Gson builder = new GsonBuilder().create();

        String jsonString = builder.toJson(person);

        MockHttpServletRequestBuilder postRequest = post("/request/objectParam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("Passenger: {firstName='Ji', lastName='Kim'}"));
    }

    @Test
    public void postWithGsonSerializedObjectRequestBody() throws Exception {
        Person impersonator = new Person("Not Ji");

        Gson gson = new GsonBuilder().create();

        MockHttpServletRequestBuilder postRequest = post("/request/string")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(impersonator));

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("{name='Not Ji'}"));
    }

    @Test
    public void postWithRawBodyRequestBody() throws Exception {
        String json = getJson("/jsonRequest.json");

        MockHttpServletRequestBuilder request = post("/request/string")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{name='Ji Kim'}"));
    }

    @Test
    public void postWithNestedRawBodyRequestBody() throws Exception {
        String requestJson = getJson("/jsonNestedRequest.json");

        MockHttpServletRequestBuilder request = post("/request/nestedStringData")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"Jules\",\"items\":[{\"drink\":\"Cortado\",\"price\":\"2.75\"},{\"drink\":\"Lemongrass Tea\",\"size\":\"large\",\"price\":\"3.85\"}],\"fulfilled\":true}"));
    }

}
