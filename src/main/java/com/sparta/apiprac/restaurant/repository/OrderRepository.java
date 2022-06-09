package com.sparta.apiprac.restaurant.repository;

import com.sparta.apiprac.restaurant.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
