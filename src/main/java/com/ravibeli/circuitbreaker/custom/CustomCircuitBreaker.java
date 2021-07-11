package com.ravibeli.circuitbreaker.config;


import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

/**
 * @author ravibeli@gmail.com
 * @project circuitbreaker
 * @created on 11 Jul, 2021 4:31 PM
 **/

public class CustomCircuitBreaker implements CircuitBreaker {

    @Override
    public <T> T run(Supplier<T> toRun) {
        return CircuitBreaker.super.run(toRun);
    }

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        return toRun.get();
    }
}
