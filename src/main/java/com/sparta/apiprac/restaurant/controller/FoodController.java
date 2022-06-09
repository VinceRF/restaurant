package com.sparta.apiprac.restaurant.controller;

import com.sparta.apiprac.restaurant.dto.FoodRequestDto;
import com.sparta.apiprac.restaurant.model.Food;
import com.sparta.apiprac.restaurant.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(
            @PathVariable Long restaurantId,
            @RequestBody List<FoodRequestDto> requestDtoList
    ){
        foodService.addFood(restaurantId, requestDtoList);
    }

    // 레스토랑 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ){
        return foodService.findAllRestaurantFoods(restaurantId);
    }
}
