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

import com.ravibeli.circuitbreaker.service.impl.MockServiceImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
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

    Logger LOG = LoggerFactory.getLogger(DemoController.class);

    private CircuitBreakerFactory circuitBreakerFactory;

    private MockServiceImpl mockServiceImpl;

    public DemoController(CircuitBreakerFactory circuitBreakerFactory, MockServiceImpl mockServiceImpl) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.mockServiceImpl = mockServiceImpl;
    }

    @GetMapping("/get")
    public Map get() {
        return mockServiceImpl.get();
    }

    @GetMapping("/delay1/{seconds}")
    public Map delaySupplier(@PathVariable int seconds) {
        return mockServiceImpl.delay(seconds);
    }


    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {
        return (Map) circuitBreakerFactory.create("custom")
            .run(() -> (mockServiceImpl.delay(seconds)),throwable -> fallBack());
    }

    private Map fallBack() {
        LOG.info("CIRCUIT BREAKER FALLBACK");
        Map<String, String> fallback = new HashMap<>();
        fallback.put("FALLBACK", "There is a network hiccup. Please try after sometime.");
        return fallback;
    }

}
