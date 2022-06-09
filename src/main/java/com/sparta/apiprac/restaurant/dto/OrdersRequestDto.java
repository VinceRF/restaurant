package com.sparta.apiprac.restaurant.dto;

import com.sparta.apiprac.restaurant.model.OrderItme;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {

    private Long restaurantId;
    private List<OrderItme> foods;
}
