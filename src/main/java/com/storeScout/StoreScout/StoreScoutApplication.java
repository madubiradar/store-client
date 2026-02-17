package com.storeScout.StoreScout;

import com.storeScout.StoreScout.client.StoreClient;
import com.storeScout.StoreScout.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class StoreScoutApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StoreScoutApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }


    StoreClient storeClient;

    @Autowired
    public StoreScoutApplication(StoreClient storeClient) {
        this.storeClient = storeClient;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter command line arguments: ");
        Scanner scanner = new Scanner(System.in);

        label:
        while (true) {

            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            String choice = null;
            Integer id = null;
            String category_name = null;

            if (tokens.length == 2) {
                choice = tokens[0];
                if (choice.equals("category")) {
                    category_name = tokens[1];
                } else {
                    id = Integer.parseInt(tokens[1]);
                }

            } else {
                choice = input;
            }

            switch (Objects.requireNonNull(choice)) {

                case "exit":
                    break label;

                case "list":
                    System.out.println("ID    | Price    | Product Title");
                    List<Product> productList = storeClient.findAll();
                    productList.stream()
                            .map(p -> p.getId() + " | " + p.getPrice() + " | " + p.getTitle())
                            .forEach(System.out::println);
                    break;

                case "view":
                    System.out.println("You have entered Id : " + id);
                    Product p = storeClient.findById(id);
                    if(p != null) {
                        System.out.println("ID: " + p.getId() + " | Description: " + p.getDescription() + " | Category: " + p.getCategory());
                    } else {
                        System.out.println("Product not found");
                    }
                    break;

                case "add":
                    System.out.println("Enter Title");
                    String title = scanner.nextLine();
                    System.out.println("Enter Price");
                    String price = scanner.nextLine();
                    System.out.println("Enter Description");
                    String description = scanner.nextLine();
                    System.out.println("Enter Category");
                    String category = scanner.nextLine();

                    Product product = new Product();
                    product.setTitle(title);
                    product.setPrice(Double.parseDouble(price));
                    product.setDescription(description);
                    product.setCategory(category);

                    Product addedProduct = storeClient.addProduct(product);
                    System.out.println("Successfully Added product : " + addedProduct.getId());
                    break;

                case "category":
                    List<Product> products = storeClient.findByCategory(category_name);
                    System.out.println("ID    | Title    | Price | Description | Category ");

                    products.stream()
                            .map(product1 -> product1.getId() + " | " + product1.getTitle() + " | " + product1.getPrice() + " | " + product1.getDescription() + " | "+product1.getCategory())
                            .forEach(System.out::println);
                    break;

            }
        }
    }
}
