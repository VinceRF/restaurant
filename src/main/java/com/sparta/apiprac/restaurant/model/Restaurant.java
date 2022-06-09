package com.sparta.apiprac.restaurant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Builder
    public Restaurant(String restaurantName, int minOrderPrice, int deliveryFee){
        this.restaurantName = restaurantName;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
