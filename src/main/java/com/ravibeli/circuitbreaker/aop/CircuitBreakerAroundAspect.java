package com.ravibeli.circuitbreaker.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = { "spring.cloud.circuitbreaker.resilience4j.enabled"}, matchIfMissing = true)
public class CircuitBreakerAroundAspect {

    @Autowired(required = false)
    CircuitBreakerFactory circuitBreakerFactory;

    @Around("@annotation(com.ravibeli.circuitbreaker.aspects.ApplyCircuitBreaker)")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Arguments passed to method are: {}", Arrays.toString(joinPoint.getArgs()));
        AtomicReference<Map<String, String>> fallback = new AtomicReference<>();

        Object proceed = circuitBreakerFactory.create(joinPoint.getSignature().toString())
            .run(() -> {
                try {
                    log.info("Inside CircuitBreaker logic in Aspect");
                    return joinPoint.proceed();
                } catch (Throwable t) {
                    log.info("delay call failed error", t);
                    fallback.get().put("FALLBACK", "There is a network hiccup. Please try after sometime.");
                    return fallback;
                }
            }, Throwable::getMessage);
        log.info("Result from method is: {}", proceed);

        return proceed;
    }
}
