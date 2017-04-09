package com.jikim.Controller;

import com.jikim.Service.CalculatorService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String calculateVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height){
        int sum = length * width * height;
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, sum);
    }

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculateArea(
            @RequestParam String type,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer height,
            @RequestParam(required = false) Integer radius) {
        try {
            if (type.equals("circle")) {
                double area = Math.PI * Math.pow(radius, 2);
                BigDecimal bdArea = new BigDecimal(area).setScale(2, RoundingMode.DOWN);
                return String.format("A circle with a radius of %d has an area of %s", radius, bdArea);
            } else if (type.equals("rectangle")) {
                int area = width * height;
                return String.format("A rectangle with a dimension of %d x %d has an area of %d", width, height, area);
            } else {
                return "";
            }
        }
        catch (Exception e) {
            return "invalid";
        }
    }
}
