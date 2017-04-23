package com.jikim.unit_4.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private String drink;
    private String size;
    private String price;

    @JsonCreator
    public Item (
            @JsonProperty("drink") String drink,
            @JsonProperty("size") String size,
            @JsonProperty("price") String price){
        this.drink = drink;
        this.size = (size == "null") ? null : size;
        this.price = price;
    }

    public String getDrink() {
        return drink;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "drink='" + drink + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}
