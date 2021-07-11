package com.ravibeli.circuitbreaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.TimeoutRetryPolicy;

/**
 * @author ravibeli@gmail.com
 * @project circuitbreaker
 * @created on 08 Jul, 2021 8:30 PM
 **/
@Configuration
public class ResiliencyConfig {

    @Bean
    @ConditionalOnProperty(name = { "spring.cloud.circuitbreaker.resilience4j.enabled"}, havingValue="false")
    public CircuitBreakerFactory customCircuitBreakerFactory() {
        return new CustomCircuitBreakerFactory();
    }

    @Bean
    @ConditionalOnProperty(name = { "spring.cloud.circuitbreaker.resilience4j.enabled"}, havingValue="false")
    public Customizer<SpringRetryCircuitBreakerFactory> defaultSpringRetryCircuitBreakerFactory() {
        return factory -> factory.configureDefault(id -> new SpringRetryConfigBuilder(id)
            .retryPolicy(new TimeoutRetryPolicy()).build());
    }

    @Bean
    @ConditionalOnProperty(name = { "spring.cloud.circuitbreaker.resilience4j.enabled"}, havingValue="true")
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build())
            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
            .build());
    }

}
