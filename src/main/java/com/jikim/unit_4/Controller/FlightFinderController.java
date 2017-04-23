package com.jikim.unit_4.Controller;

import com.jikim.unit_4.Model.Flight;
import com.jikim.unit_4.Service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightFinderController {

    @GetMapping
    public List<Flight> getFlights() throws Exception {
        return FlightService.fetchAllFlights();
    }

    @GetMapping("/flight")
    public Flight getFlight() throws Exception {
        return FlightService.fetchSingleFlight();
    }

    @PostMapping("/tickets/total")
    public String getSalesTotalForFlight(@RequestBody Flight flight) throws Exception {
        return FlightService.calculateSalesTotal(flight);
    }

}