package com.jikim.unit_4.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.jikim.unit_4.Model.Flight;
import com.jikim.unit_4.Model.Passenger;
import com.jikim.unit_4.Model.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightFinder {

    @GetMapping
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public List<Flight> getFlights() throws ParseException {
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");
        Passenger passenger1 = new Passenger("Roland", "Bertrand");
        Passenger passenger2 = new Passenger("Vanessa", null);

        List<Ticket> tickets1 = Arrays.asList(new Ticket(passenger1, 200));
        Flight flight1 = new Flight(departureDate, tickets1);

        List<Ticket> tickets2 = Arrays.asList(new Ticket(passenger2, 400));
        Flight flight2 = new Flight(departureDate, tickets2);

        return Arrays.asList(flight1, flight2);
    }

    @GetMapping("/flight")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public Flight getFlight() throws JsonProcessingException, ParseException {
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");

        System.out.println("**********This is departureDate" + departureDate);

        List<Ticket> tickets = Arrays.asList(new Ticket(new Passenger("Roland", "Bertrand"), 200));
        Flight flight1 = new Flight(departureDate, tickets);
        String result = new ObjectMapper().writeValueAsString(flight1);
        System.out.println("**********This is flight 1" + result);

        return flight1;
    }

}

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date departureDate = df.parse("2017-04-21 14:34");  // Fri Apr 21 14:34:00 MDT 2017