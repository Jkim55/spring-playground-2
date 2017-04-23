package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class OrderDetail {
    private String name;
    private Item[] items;
    private Boolean fulfilled;

    @JsonCreator
    public OrderDetail(
            @JsonProperty("name") String name,
            @JsonProperty("items") Item[] items,
            @JsonProperty("fulfilled") Boolean fulfilled) {
        this.name = name;
        this.items = items;
        this.fulfilled = fulfilled;
    }


    public String getName() {
        return name;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public Item[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order Detail: {" +
                "name='" + name + '\'' +
                ", items=" + Arrays.toString(items) +
                ", fulfilled=" + fulfilled +
                '}';
    }

}