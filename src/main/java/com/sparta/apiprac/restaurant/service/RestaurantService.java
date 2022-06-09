package com.sparta.apiprac.restaurant.service;


import com.sparta.apiprac.restaurant.dto.RestaurantRequestDto;
import com.sparta.apiprac.restaurant.model.Restaurant;
import com.sparta.apiprac.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    // 음식점 등록
    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDto requestDto){
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        checkMinOrderPrice(minOrderPrice);
        checkDeliveryFee(deliveryFee);

        Restaurant restaurant = Restaurant.builder()
                .restaurantName(requestDto.getRestaurantName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    // 음식점 모두 조회
    @Transactional
    public List<Restaurant> findAllRestaurant(){
        return restaurantRepository.findAll();
    }

    // 음식점 최소 주문 금액 유효성 검사
    private void checkMinOrderPrice(int minOrderPrice) {
        if (!(1000 <= minOrderPrice && minOrderPrice <= 100_000)){
            throw new IllegalArgumentException("최소 주문 금액을 바르게 입력해주세요.");
        }
        if (minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("최소 주문 금액 단위는 100원입니다.");
        }
    }

    // 음식점 배달료 금액 유효성 검사
    private void checkDeliveryFee(int deliveryFee){
        if (0 > deliveryFee || deliveryFee > 10_000){
            throw new IllegalArgumentException("배달료를 정확히 입력해주세요.");
        }
        if (deliveryFee % 500 != 0){
            throw new IllegalArgumentException("배달료는 500원 단위입니다.");
        }

    }
}
