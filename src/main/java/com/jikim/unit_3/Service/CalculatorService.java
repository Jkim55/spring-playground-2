package com.jikim.unit_3.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorService {

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divide(int num1, int num2) {
        return num1 / num2;
    }

    public String areaOfCircle(int radius) {
        double area = Math.PI * Math.pow(radius, 2);
        BigDecimal bdArea = new BigDecimal(area).setScale(2, RoundingMode.DOWN);
        return String.format("A circle with a radius of %d has an area of %s", radius, bdArea);
    }

    public String areaOfRectangle(int width, int height) {
        int area = width * height;
        return String.format("A rectangle with a dimension of %d x %d has an area of %d", width, height, area);
    }
}
