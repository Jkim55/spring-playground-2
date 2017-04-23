package com.jikim.unit_4;

import com.jikim.unit_4.Controller.FlightFinderController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightFinderController.class)
public class FlightFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    private String getJson(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

    @Test
    public void fetchSingleFlight() throws Exception {
        String flightResponse = getJson("/flightResponse.json");

        MockHttpServletRequestBuilder request = get("/flights/flight");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(flightResponse));
    }
    // TODO: Figure out timezone offset business when Flight objects are instantiated

    @Test
    public void fetchMultipleFlights() throws Exception {
        String flightsResponse = getJson("/flightsResponse.json");

        MockHttpServletRequestBuilder request = get("/flights");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(flightsResponse));
    }

    @Test
    public void calculateTicketSalesOnSingleFlight_WithStringLiteral() throws Exception{
        String requestJson = getJson("/salesRequest.json");
        String responseJson = getJson("/salesResponse.json");

        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    // TODO: Implementation for last two tests

    @Test
    public void calculateTicketSalesOnSingleFlight_WithSerializedGSON() throws Exception{
        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total");

        this.mvc.perform(postRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void calculateTicketSalesOnSingleFlight_WithFileFixture() throws Exception{
        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total");

        this.mvc.perform(postRequest)
                .andExpect(status().isOk());
    }

}
