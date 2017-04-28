package com.jikim.unit_4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jikim.unit_4.Controller.FlightFinderController;
import com.jikim.unit_4.Model.Flight;
import com.jikim.unit_4.Model.Passenger;
import com.jikim.unit_4.Model.Ticket;
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
import java.util.Arrays;

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
    // TODO: Figure out timezone offset business. Does it occur when response is returned?

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
        JsonObject passengerName = new JsonObject();
        passengerName.addProperty("firstName", "Ji");
        passengerName.addProperty("lastName", "Kim");

        JsonObject passenger1 = new JsonObject();
        passenger1.add("passenger", passengerName);
        passenger1.addProperty("price", 100);

        JsonArray tickets = new JsonArray();
        tickets.add(passenger1);

        JsonObject flight = new JsonObject();
        flight.add("tickets", tickets);
        flight.add("departs", null);

        Gson builder = new GsonBuilder().create();
        String jsonString = builder.toJson(flight);


        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\": 100}"));
    }

    @Test
    public void calculateTicketSalesOnSingleFlight_WithSerializedGSON() throws Exception{
        Ticket t1 = new Ticket(new Passenger("Hannah", "Horvath"), 300);
        Ticket t2 = new Ticket(new Passenger("Marnie", "Michaels"), 300);
        Flight flight = new Flight(null, Arrays.asList(t1, t2));
        Gson gson = new GsonBuilder().create();

        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(flight));

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\": 600}"));
    }

    @Test
    public void calculateTicketSalesOnSingleFlight_WithFileFixture() throws Exception{
        String requestJson = getJson("/salesRequest.json");
        String responseJson = getJson("/salesResponse.json");

        MockHttpServletRequestBuilder postRequest = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

}
