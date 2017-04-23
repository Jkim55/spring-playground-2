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

    @JsonProperty("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    @JsonProperty("Price")
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
