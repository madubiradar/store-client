package com.storeScout.StoreScout.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
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
