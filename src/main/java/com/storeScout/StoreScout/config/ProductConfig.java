package com.storeScout.StoreScout.config;

import com.storeScout.StoreScout.client.StoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProductConfig {

    @Value("${environment.base-url}")
    private String baseUrl;

    /**
     * Creates and configures a {@link StoreClient} proxy bean to interact with the
     * external Store API.
     * <p>
     * This method utilizes the Spring 6 Declarative HTTP Client feature. It builds
     * an underlying {@link RestClient} using the configured base URL, adapts it
     * through a {@link RestClientAdapter}, and uses a {@link HttpServiceProxyFactory}
     * to generate a dynamic implementation of the StoreClient interface.
     * </p>
     *
     * @param clientBuilder the {@link RestClient.Builder} provided by the Spring context
     * @return a thread-safe proxy implementation of {@link StoreClient}
     * @see HttpServiceProxyFactory
     */
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
