package com.ivk23.reactive.app.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Kos
 */
@Component
@Scope("singleton")
public class SimpleCache {

    private Map<String, String> cache=  new HashMap<>();

    public void add(String key, String value) {
        this.cache.put(key,value);
    }

    public String get(String key) {
        return this.cache.get(key);
    }

}
