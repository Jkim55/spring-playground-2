package com.jikim.unit_4.Controller;

import com.jikim.unit_4.Model.Cronut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/json")
public class JsonResponseController {

    @GetMapping("simple-object")
    public Cronut RenderCronutAsObject(){
        return new Cronut ("Chai", "Coconut");
    }

    @GetMapping("/array")
    public List <Cronut>RenderCronutAsArray() {
        Cronut forJaime = new Cronut ("Earl Gray", "Lavender");
        Cronut forJanet = new Cronut("Sugar", "Birthday Sprinkles");
        Cronut forJoyce = new Cronut("Cheesecake", "Strawberry Ice");
    return Arrays.asList(forJaime, forJanet, forJoyce);
    }

}
