package com.sparta.apiprac.restaurant.service;

import com.sparta.apiprac.restaurant.dto.FoodRequestDto;
import com.sparta.apiprac.restaurant.model.Food;
import com.sparta.apiprac.restaurant.model.Restaurant;
import com.sparta.apiprac.restaurant.repository.FoodRepository;
import com.sparta.apiprac.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    //음식점 등록
    @Transactional
    public void addFood(Long restaurantId, List<FoodRequestDto> requestDtoList) {

        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(foundRestaurant);
        Restaurant restaurant = foundRestaurant.get();

        for (FoodRequestDto requestDto : requestDtoList){
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();
            foodRepository.save(food);
        }


    }

    @Transactional
    public List<Food> findAllRestaurantFoods(Long retaurantId){
        Restaurant restaurant = restaurantRepository.findById(retaurantId)
                .orElseThrow(
                        () -> new IllegalArgumentException("음식점을 찾을 수 없습니다.")
                );
        return foodRepository.findFoodByRestaurant(restaurant);
    }


    // 음식점 못 찾을 시
    private void checkRestaurant(Optional<Restaurant> foundRestaurant){
        if (!foundRestaurant.isPresent())
            throw new IllegalArgumentException("음식점을 찾을 수 없습니다.");
    }

    // 음식점에 음식이 중복될 때
    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName){
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant,foodName);
        if (found.isPresent())
            throw new IllegalArgumentException("음식점에 이미 해당 음식이 존재합니다.");


    }

    // 음식 가격 유효성 검사
    private void checkFoodPrice(int foodPrice){
        if (foodPrice < 100)
            throw new IllegalArgumentException("음식 가격이 너무 낮습니다.");

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException("음식 가격이 너무 높습니다.");

        if (foodPrice % 100 != 0)
            throw new IllegalArgumentException("음식 가격은 100원 단위여야 합니다.");
    }


}

