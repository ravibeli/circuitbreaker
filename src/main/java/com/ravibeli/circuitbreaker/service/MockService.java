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

package com.ravibeli.circuitbreaker.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ravibeli@gmail.com
 * @project circuitbreaker
 * @created on 08 Jul, 2021 8:30 PM
 **/
@Slf4j
@Service
public class MockService {

    private RestTemplate rest;

    public MockService(RestTemplate rest) {
        this.rest = rest;
    }

    public String get() {
        return rest.getForObject("https://hub.dummyapis.com/delay?seconds=1", String.class);
    }

    public String delay(int seconds) {
        return rest.getForObject("https://hub.dummyapis.com/delay?seconds=" + seconds, String.class);
    }

}
