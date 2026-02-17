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

import java.util.Scanner;

@SpringBootApplication
public class StoreScoutApplication implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StoreScoutApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}


	@Autowired
	StoreClient storeClient;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enter command line arguments: ");
		Scanner scanner = new Scanner(System.in);

        label:
        while(true){

            String choice = scanner.next();
            int id = Integer.parseInt(scanner.next());
		switch (choice) {

			case "exit":
				break label;

			case "list":
				System.out.println("ID    | Price    | Product Title");
				storeClient.findAll().stream()
				.map(p -> p.getId() + " | " + p.getPrice() + " | " + p.getTitle())
				.forEach(System.out::println);
				break;

			case "view":
				System.out.println("You have entered Id : " + id);
				Product p = storeClient.findById(id);
				System.out.println("ID: " + p.getId() + " | Description: " + p.getDescription() + " | Category: " + p.getCategory());
				break;
}
        }
    }
}
