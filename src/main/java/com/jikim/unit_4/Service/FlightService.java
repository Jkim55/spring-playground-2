package com.jikim.unit_4.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikim.unit_4.Model.Flight;
import com.jikim.unit_4.Model.Passenger;
import com.jikim.unit_4.Model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlightService {

    public static List<Flight> fetchAllFlights() throws ParseException {
        Passenger passenger1 = new Passenger("Roland", "Bertrand");
        Passenger passenger2 = new Passenger("Vanessa", null);
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");

        List<Ticket> tickets1 = Arrays.asList(new Ticket(passenger1, 200));
        Flight flight1 = new Flight(departureDate, tickets1);

        List<Ticket> tickets2 = Arrays.asList(new Ticket(passenger2, 400));
        Flight flight2 = new Flight(departureDate, tickets2);

        return Arrays.asList(flight1, flight2);
    }

    public static Flight fetchSingleFlight() throws JsonProcessingException, ParseException {

        Date departureDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");
        String dateTimeFormatPattern = "yyyy/MM/dd HH:mm:ss z";
        DateFormat format = new SimpleDateFormat(dateTimeFormatPattern);
        String departureDateString = format.format(departureDate);  //this does work BUT it's a string
        System.out.println(
                "Date '" + departureDate + "' formatted with SimpleDateFormat and '"
                        + dateTimeFormatPattern + "': " + departureDateString);

        Date olddDay = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2017-04-21 14:34");

        List<Ticket> tickets = Arrays.asList(new Ticket(new Passenger("Roland", "Bertrand"), 200));
        Flight flight1 = new Flight(olddDay, tickets);
        System.out.println("**********This is flight 1.getDeparts*****" + flight1.getDeparts());
        return flight1;
    }

    public static String calculateSalesTotal(Flight flight) {
        Integer totalSales = flight.getTickets().stream().mapToInt(passenger -> passenger.getPrice()).sum();
        return String.format("{\"result\": %d}", totalSales);
    }

    public static HashMap<String, Integer> calculateSalesTotalUsingRS(Flight flight) {
        Integer totalSales = flight.getTickets().stream().mapToInt(passenger -> passenger.getPrice()).sum();
        HashMap<String, Integer> response = new HashMap<>();
        response.put("result", totalSales);
        return response;
    }

}