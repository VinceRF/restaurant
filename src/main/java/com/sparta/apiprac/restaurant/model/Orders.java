package com.sparta.apiprac.restaurant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_Id")
    private List<OrderItme> foods;

    @Builder
    public Orders(String restaurantName, int totalPrice, List<OrderItme> foods){
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.foods = foods;
    }

}
