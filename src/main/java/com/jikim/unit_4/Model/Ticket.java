package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private Passenger passenger;
    private Integer price;

    @JsonCreator
    public Ticket(
            @JsonProperty("passenger") Passenger passenger,
            @JsonProperty("price") Integer price) {
        this.passenger = passenger;
        this.price = price;
    }

    @JsonProperty("passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "Passenger=" + passenger +
                ", Price=" + price +
                '}';
    }
}
