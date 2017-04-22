package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Trip {
    private int id;
    private String destination;
    private Date departsOn;
    private List<Person> passengers;
    private Person conductor;

    @JsonCreator
    public Trip(
            @JsonProperty("id") int id,
            @JsonProperty("destination") String destination,
            @JsonProperty("departsOn") Date departsOn,
            @JsonProperty("passengers") List<Person> passengers,
            @JsonProperty("conductor") Person conductor) {
        this.id = id;
        this.destination = destination;
        this.departsOn = departsOn;
        this.passengers = passengers;
        this.conductor = conductor;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDepartsOn() {
        return departsOn;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public Person getConductor() {
        return conductor;
    }
}
