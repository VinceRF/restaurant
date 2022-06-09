package com.sparta.apiprac.restaurant.repository;

import com.sparta.apiprac.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findByRestaurantName(String restaurantName);

}
