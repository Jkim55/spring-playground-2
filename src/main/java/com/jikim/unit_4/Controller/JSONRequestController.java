package com.jikim.unit_4.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikim.unit_4.Model.OrderDetail;
import com.jikim.unit_4.Model.Passenger;
import com.jikim.unit_4.Model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class JSONRequestController {

    @PostMapping("/objectParam")
    public String requestWithJson(@RequestBody Passenger passenger) {
        return passenger.toString();
    }

    @PostMapping("/string")
    public String requestWithJsonString(@RequestBody Person person){
        return person.toString();
    }

    @PostMapping("/nestedStringData")
    public String requestWithNestedJsonString(@RequestBody OrderDetail order) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(order);
    }
}
