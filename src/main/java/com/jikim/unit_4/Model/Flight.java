package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "US/Mountain")
    private Date departs;
    private List<Ticket> tickets;

    @JsonCreator
    public Flight(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "US/Mountain")
            @JsonProperty("Departs") Date departs,
            @JsonProperty("Tickets") List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
        System.out.println("**********This is Flight.departs" + this.departs);
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    public Date getDeparts() {
        return departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

}