package com.storeScout.StoreScout;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StoreScoutApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StoreScoutApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
