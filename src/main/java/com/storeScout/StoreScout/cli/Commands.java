package com.storeScout.StoreScout.cli;

import com.storeScout.StoreScout.client.StoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class Commands {

    StoreClient storeClient;

    @Autowired
    public Commands(StoreClient storeClient) {
        this.storeClient = storeClient;
    }

    @ShellMethod(key = "list")
    public void listAllProducts() {
        System.out.println("ID    | Price    | Product Title");
        List<Product> productList = storeClient.findAll();
        productList.stream()
                .map(p -> p.getId() + " | " + p.getPrice() + " | " + p.getTitle())
                .forEach(System.out::println);

    }

    @ShellMethod(key = "view")
    public void viewProduct(@ShellOption Integer id) {
        Product p = storeClient.findById(id);
        if (p != null) {
            System.out.println("--- PRODUCT DETAILS ---");
            System.out.printf("ID   : %d%n", p.getId());
            System.out.printf("Title: %s%n", p.getTitle());
            System.out.printf("Price: $%.2f%n", p.getPrice());
        } else {
            System.out.println("Product not found!");
        }
    }

    @ShellMethod(key = "add")
    public void addProduct(
            @ShellOption String title,
            @ShellOption Double price,
            @ShellOption String description,
            @ShellOption String category
    ) {
        // 1. Create the product object using the parameters
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);

        // 2. Call the API
        Product addedProduct = storeClient.addProduct(product);

        // 3. Feedback
        System.out.println("Successfully Added product! Assigned ID: " + addedProduct.getId());
    }
}
