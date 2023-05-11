/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ravibeli.circuitbreaker.controllers;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ravibeli@gmail.com
 * @project circuitbreaker
 * @created on 08 Jul, 2021 8:30 PM
 **/
@Slf4j
@RestController
public class DemoController {

    private CircuitBreakerFactory circuitBreakerFactory;

    private com.ravibeli.circuitbreaker.service.MockService mockService;

    public DemoController(CircuitBreakerFactory circuitBreakerFactory, com.ravibeli.circuitbreaker.service.MockService mockService) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.mockService = mockService;
    }

    @GetMapping("/get")
    public String get() {
        return mockService.get();
    }

    @GetMapping("/delay1/{seconds}")
    public String delaySupplier(@PathVariable int seconds) {
        return mockService.delay(seconds);
    }


    @GetMapping("/delay/{seconds}")
    public String delay(@PathVariable int seconds) {
        return  circuitBreakerFactory.create("custom").run(() -> (mockService.delay(seconds)), throwable -> fallBack());
    }

    private String fallBack() {
        log.info("CIRCUIT BREAKER FALLBACK");
        return "FALLBACK: There is a network hiccup. Please try after sometime.";
    }

}
