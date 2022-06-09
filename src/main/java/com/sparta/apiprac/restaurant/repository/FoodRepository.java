package com.sparta.apiprac.restaurant.repository;

import com.sparta.apiprac.restaurant.model.Food;
import com.sparta.apiprac.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findFoodByRestaurant(Restaurant restaurant);

    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
