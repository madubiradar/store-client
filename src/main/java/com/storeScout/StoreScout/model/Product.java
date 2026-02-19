package com.storeScout.StoreScout.model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String imageUrl;
}
