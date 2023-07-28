package com.meliksah.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced//birden fazla service instance ile eşleşme durumunda otomatik match işlemi yapması için eklendi.
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
