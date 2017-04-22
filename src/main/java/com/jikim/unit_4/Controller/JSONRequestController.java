package com.jikim.unit_4.Controller;

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
    public String requestWithJSON(@RequestBody Passenger passenger) {
        return passenger.toString();
    }

    @PostMapping("/string")
    public String requestWithJSONString(@RequestBody Person person){
        return "stubbbbbb";
    }
}
