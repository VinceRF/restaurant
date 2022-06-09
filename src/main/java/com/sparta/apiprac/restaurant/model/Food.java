package com.sparta.apiprac.restaurant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor

public class Food extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_Id", nullable = false)
    private Restaurant restaurant;

    @Builder
    public Food(String name, int price, Restaurant restaurant){
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }


}
