package com.sparta.apiprac.restaurant.dto;


import lombok.Getter;

@Getter
public class RestaurantRequestDto {

    private String restaurantName;

    private int minOrderPrice;

    private int deliveryFee;


}
