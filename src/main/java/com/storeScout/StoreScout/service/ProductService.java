package com.storeScout.StoreScout.service;

import com.storeScout.StoreScout.client.StoreClient;
import com.storeScout.StoreScout.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    StoreClient storeClient;

    @Autowired
    public ProductService(StoreClient storeClient) {
        this.storeClient = storeClient;
    }

    /**
     * This method fetches all products
     * @return list of all products
     */
    @ShellMethod(key = "list")
    public void listAllProducts() {
        logger.info("ID    | Price    | Product Title");
        List<Product> productList = storeClient.findAll();
        productList.stream()
                .map(p -> p.getId() + " | " + p.getPrice() + " | " + p.getTitle())
                .forEach(System.out::println);

    }

    /**
     * This method fetches a product by id
     * @param id
     */
    @ShellMethod(key = "view")
    public void viewProduct(@ShellOption Integer id) {
        Product p = storeClient.findById(id);
        if (p != null) {
            System.out.println("--- PRODUCT DETAILS ---");
            System.out.printf("ID   : %d%n", p.getId());
            System.out.printf("Title: %s%n", p.getTitle());
            System.out.printf("Price: $%.2f%n", p.getPrice());
        } else {
            logger.warn("Product not found!");
        }
    }

    /**
     * This method interactively add product to store
     * @param title
     * @param price
     * @param description
     * @param category
     */

    @ShellMethod(key = "add")
    public void addProduct(
            @ShellOption String title,
            @ShellOption Double price,
            @ShellOption String description,
            @ShellOption String category
    ) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);

        Product addedProduct = storeClient.addProduct(product);

        logger.info("Successfully Added product! Assigned ID: {}", addedProduct.getId());
    }
}
