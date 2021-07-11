package com.ravibeli.circuitbreaker.config;

import java.util.function.Function;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ConfigBuilder;

/**
 * @author ravibeli@gmail.com
 * @project circuitbreaker
 * @created on 11 Jul, 2021 4:31 PM
 **/

public class CustomCircuitBreakerFactory extends CircuitBreakerFactory {

    @Override
    public CircuitBreaker create(String id) {
        return new CustomCircuitBreaker();
    }

    @Override
    protected ConfigBuilder configBuilder(String id) {
        return null;
    }

    @Override
    public void configureDefault(Function defaultConfiguration) {

    }
}
