package com.sparta.apiprac.restaurant.controller;

import com.sparta.apiprac.restaurant.dto.OrdersRequestDto;
import com.sparta.apiprac.restaurant.dto.OrdersResponseDto;
import com.sparta.apiprac.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 하기
    @PostMapping("/order/request")
    public OrdersResponseDto orderFood(
            @RequestBody OrdersRequestDto requestDto
            ){
        return orderService.order(requestDto);
    }

    // 모든 주문 조회
    @GetMapping("/orders")
    public List<OrdersResponseDto> findAllOrder(){
        return orderService.findAllOrder();
    }
}
