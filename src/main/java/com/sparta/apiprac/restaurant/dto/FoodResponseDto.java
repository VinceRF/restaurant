package com.sparta.apiprac.restaurant.dto;

import com.sparta.apiprac.restaurant.model.OrderItme;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private String name;
    private int quantity;
    private int price;

    public FoodResponseDto(OrderItme orderItme){
        this.name = orderItme.getName();
        this.quantity = orderItme.getQuantity();
        this.price = orderItme.getPrice();
    }
}
