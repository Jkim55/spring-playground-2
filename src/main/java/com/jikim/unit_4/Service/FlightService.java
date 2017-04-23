package com.jikim.unit_4.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikim.unit_4.Model.Flight;
import com.jikim.unit_4.Model.Passenger;
import com.jikim.unit_4.Model.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlightService {

    public static List<Flight> fetchAllFlights() throws ParseException {
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");
        Passenger passenger1 = new Passenger("Roland", "Bertrand");
        Passenger passenger2 = new Passenger("Vanessa", null);

        List<Ticket> tickets1 = Arrays.asList(new Ticket(passenger1, 200));
        Flight flight1 = new Flight(departureDate, tickets1);

        List<Ticket> tickets2 = Arrays.asList(new Ticket(passenger2, 400));
        Flight flight2 = new Flight(departureDate, tickets2);

        return Arrays.asList(flight1, flight2);
    }

    public static Flight fetchSingleFlight() throws JsonProcessingException, ParseException {
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");
//        System.out.println("**********This is departureDate" + departureDate);
        List<Ticket> tickets = Arrays.asList(new Ticket(new Passenger("Roland", "Bertrand"), 200));
        Flight flight1 = new Flight(departureDate, tickets);
        String result = new ObjectMapper().writeValueAsString(flight1);
//        System.out.println("**********This is flight 1" + result);
        return flight1;
    }

    public static String calculateSalesTotal(Flight flight) {
        Integer totalSales = flight.getTickets().stream().mapToInt(passenger -> passenger.getPrice()).sum();
        return String.format("{\"result\": %d}", totalSales);
    }

}


//        System.out.println("*******" + new ObjectMapper().writeValueAsString(flight));
//        return new ObjectMapper().writeValueAsString(flight);

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date departureDate = df.parse("2017-04-21 14:34");  // Fri Apr 21 14:34:00 MDT 2017