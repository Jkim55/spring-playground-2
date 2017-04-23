package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Passenger {
    private String firstName;
    private String lastName;

    @JsonCreator
    public Passenger(
            @JsonProperty("FirstName") String firstName ,
            @JsonProperty("LastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("LastName")
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Passenger: {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
