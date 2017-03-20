package com.jikim.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class CalculatorController {

    @GetMapping("/pi")
    public String getPi () {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String getCalculation (
            @RequestParam(required = false, defaultValue = "add") String operation,
            @RequestParam int x,
            @RequestParam int y){
        String operator = "";
        int evaluated = 0;
        switch (operation) {
            case "add":
                operator = "+";
                evaluated = x + y;
                break;
            case "subtract":
                operator = "-";
                evaluated = x - y;
                break;
            case "multiply":
                operator = "*";
                evaluated = x * y;
                break;
            case "divide":
                operator = "/";
                evaluated = x / y;
                break;
        }
        return String.format("%d %s %d = %d", x, operator, y, evaluated);
    }

}
