package com.sparta.apiprac.restaurant.service;

import com.sparta.apiprac.restaurant.dto.FoodResponseDto;
import com.sparta.apiprac.restaurant.dto.OrdersRequestDto;
import com.sparta.apiprac.restaurant.dto.OrdersResponseDto;
import com.sparta.apiprac.restaurant.model.Food;
import com.sparta.apiprac.restaurant.model.Orders;
import com.sparta.apiprac.restaurant.model.OrderItme;
import com.sparta.apiprac.restaurant.model.Restaurant;
import com.sparta.apiprac.restaurant.repository.FoodRepository;
import com.sparta.apiprac.restaurant.repository.OrderItemRepository;
import com.sparta.apiprac.restaurant.repository.OrderRepository;
import com.sparta.apiprac.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    // 음식 주문
    @Transactional
    public OrdersResponseDto order(OrdersRequestDto requestDto) {
        Restaurant restaurant = getRestaurant(requestDto);

        int totalPrice = 0;
        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>();
        List<OrderItme> orderItmes = requestDto.getFoods();
        List<OrderItme> orderItmeList = new ArrayList<>();
        for (OrderItme orderItmes2 : orderItmes) {
            int quantity = orderItmes2.getQuantity();
            checkQuantity(quantity);

            Food food = getFood(orderItmes2);

            OrderItme orderItme = OrderItme.builder()
                    .name(food.getName())
                    .quantity(orderItmes2.getQuantity())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderItemRepository.save(orderItme);
            FoodResponseDto foodResponseDto = new FoodResponseDto(orderItme);
            foodResponseDtoList.add(foodResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderItmeList.add(orderItme);
        }

            if (totalPrice < restaurant.getMinOrderPrice()) {
                throw new IllegalArgumentException("주문 금액이 최소 주문 금액보다 적습니다.");
            }

            int deliveryFee = restaurant.getDeliveryFee();
            totalPrice += deliveryFee;
            Orders orders = Orders.builder()
                    .restaurantName(restaurant.getRestaurantName())
                    .totalPrice(totalPrice)
                    .foods(orderItmeList)
                    .build();
            orderRepository.save(orders);
            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, foodResponseDtoList, deliveryFee);
            return ordersResponseDto;
        }

    // 주문 모두 조회
    @Transactional
    public List<OrdersResponseDto> findAllOrder() {
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = orderRepository.findAll();

        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByRestaurantName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodResponseDto> foodsResponseDtoList = new ArrayList<>();


            List<OrderItme> orderItemsList  = orderItemRepository.findOrderItmeByOrders(orders);
            for (OrderItme orderItem : orderItemsList) {
                FoodResponseDto foodResponseDto = new FoodResponseDto(orderItem);
                foodsResponseDtoList.add(foodResponseDto);
            }

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, foodsResponseDtoList, deliveryFee);
            ordersResponseDtoList.add(ordersResponseDto);
        }

        return ordersResponseDtoList;
    }


    // 주문 수량 유효성 검사
    private void checkQuantity(int quantity){
        if (quantity < 1 || quantity > 100){
            throw new IllegalArgumentException("주문 수량을 정확히 입력해주세요.");
        }
    }

    // 주문할 음식점 찾기
    private Restaurant getRestaurant(OrdersRequestDto requestDto){
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));
        return restaurant;
    }

    // 주문할 음식 찾기
    private Food getFood(OrderItme orderItem){
        Food food = foodRepository.findById(orderItem.getId())
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다."));
        return food;
    }
}
