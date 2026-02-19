package com.storeScout.StoreScout.client;

import com.storeScout.StoreScout.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface StoreClient {

    @GetExchange("/products")
    List<Product> findAll();

    @GetExchange("/products/{id}")
    Product findById(@PathVariable("id") Integer id);

    @PostExchange("/products")
    Product addProduct(@RequestBody Product product);

    @GetExchange("/products/category/{category_name}")
    List<Product> findByCategory(@PathVariable("category_name") String category);

}
