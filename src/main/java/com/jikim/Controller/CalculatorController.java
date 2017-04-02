package com.jikim.Controller;

import com.jikim.Service.CalculatorService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        CalculatorService calculatorService = new CalculatorService();

        switch (operation) {
            case "add":
                operator = "+";
                evaluated = calculatorService.add(x,y);
                break;
            case "subtract":
                operator = "-";
                evaluated = calculatorService.subtract(x,y);
                break;
            case "multiply":
                operator = "*";
                evaluated = calculatorService.multiply(x,y);
                break;
            case "divide":
                operator = "/";
                evaluated = calculatorService.divide(x,y);
                break;
        }
        return String.format("%d %s %d = %d", x, operator, y, evaluated);
    }

    @PostMapping("/sum")
    public String evaluateSumforMoreThan2Digits(@RequestParam MultiValueMap<String, String> queryParamNums) {
        List<String> nums = queryParamNums.get("n");
        String expression = String.join(" + ", nums);
        int evaluation = nums.stream().mapToInt(Integer::parseInt).sum();

        return String.format("%s = %d", expression, evaluation);
    }
}