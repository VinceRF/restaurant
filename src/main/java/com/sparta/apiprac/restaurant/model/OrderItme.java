package com.sparta.apiprac.restaurant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    @ManyToOne
    private Orders orders;

    @Builder
    public OrderItme(String name, int quantity, int price, Food food, Orders orders){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.orders = orders;
        this.food = food;
    }


}
