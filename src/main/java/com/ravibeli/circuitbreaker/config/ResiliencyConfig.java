package com.ravibeli.circuitbreaker.config;


import com.ravibeli.circuitbreaker.custom.CustomCircuitBreakerFactory;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @ConditionalOnProperty(name = { "spring.cloud.circuitbreaker.resilience4j.enabled"}, havingValue="true")
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10)).build())
            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
            .build());
    }

}
