package com.jikim.unit_4.Controller;

import com.jikim.unit_4.Model.Trip;
import com.jikim.unit_4.Model.Person;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class CustomNestedJsonController {

    @GetMapping("/trip")
    public Trip getSingleTripData() throws ParseException {
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-06");
        List <Person> passengers = Arrays.asList(new Person("Eliza"), new Person("Samuel"));
        Person conductor = new Person("Angelica");
        Trip trip = new Trip(10, "London", departureDate, passengers, conductor);

        return trip;
    }
}
