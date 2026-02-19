package com.storeScout.StoreScout.config;

import com.storeScout.StoreScout.client.StoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Value("${environment.base-url}")
    private String baseUrl;

    @Bean
    public StoreClient storeClient(RestClient.Builder clientBuilder) {

        //Build underlying RestClient with BaseURL
        RestClient restClient = clientBuilder
                .baseUrl(baseUrl)
                .build();

        // Create Adapter using that client
        RestClientAdapter adapter = RestClientAdapter.create(restClient);


        //Create factory and generate proxy implementation
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();

        return factory.createClient(StoreClient.class);

    }
}
