package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    private Passenger passenger;
    private Integer price;

    @JsonCreator
    public Ticket(
            @JsonProperty("Passenger") Passenger passenger,
            @JsonProperty("Price") Integer price) {
        this.passenger = passenger;
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Integer getPrice() {
        return price;
    }
}
