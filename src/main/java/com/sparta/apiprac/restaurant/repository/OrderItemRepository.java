package com.sparta.apiprac.restaurant.repository;

import com.sparta.apiprac.restaurant.model.Orders;
import com.sparta.apiprac.restaurant.model.OrderItme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItme,Long> {
    List<OrderItme> findOrderItmeByOrders(Orders orders);
}
