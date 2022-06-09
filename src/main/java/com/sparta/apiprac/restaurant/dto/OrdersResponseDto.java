package com.sparta.apiprac.restaurant.dto;

import com.sparta.apiprac.restaurant.model.Orders;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersResponseDto {

    private String restaurantName;
    private List<FoodResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrdersResponseDto(Orders orders, List<FoodResponseDto> foods, int deliveryFee){
        this.restaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.totalPrice = orders.getTotalPrice();
        this.deliveryFee = deliveryFee;
    }
}
