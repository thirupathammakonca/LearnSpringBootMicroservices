package com.thiruacademy.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	@Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(predicateSpec -> predicateSpec.path("/ORDERSERVICE/**").uri("lb://ORDERSERVICE"))
                .route(predicateSpec -> predicateSpec.path("/PRODUCTSERVICE/**").uri("lb://PRODUCTSERVICE"))
                .route(predicateSpec -> predicateSpec.path("/PAYMENTSERVICE/**").uri("lb://PAYMENTSERVICE"))
                .build();
    }
}
