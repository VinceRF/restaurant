package com.sparta.apiprac.restaurant.controller;


import com.sparta.apiprac.restaurant.dto.RestaurantRequestDto;
import com.sparta.apiprac.restaurant.model.Restaurant;
import com.sparta.apiprac.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(
            @RequestBody RestaurantRequestDto requestDto
            ){
        return restaurantService.addRestaurant(requestDto);
    }

    // 음식점 모두 조회
    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant(){
        return restaurantService.findAllRestaurant();
    }


}
