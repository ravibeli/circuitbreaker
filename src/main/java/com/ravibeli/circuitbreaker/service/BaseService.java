package com.ravibeli.circuitbreaker.service;

import java.util.Map;

public interface BaseService {
    public Map get();

    public Map delay(int seconds);

    public Map fallBack();
}
