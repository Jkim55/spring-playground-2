package com.jikim.unit_4;

import com.jikim.unit_4.Controller.FlightFinder;
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
@WebMvcTest(FlightFinder.class)
public class FlightFinderTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void fetchSingleFlight() throws Exception {
        String flightResponse = getFixture("/flightResponse.json");

        MockHttpServletRequestBuilder request = get("/flights/flight");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(flightResponse));
    }

    @Test
    public void fetchMultipleFlights() throws Exception {
        String flightsResponse = getFixture("/flightsResponse.json");

        MockHttpServletRequestBuilder request = get("/flights");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(flightsResponse));
    }

    private String getFixture(String path) throws Exception {  //should I extract this as a helper class?
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

}
