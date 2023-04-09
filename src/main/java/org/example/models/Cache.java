package org.example.models;

import java.util.PriorityQueue;

public class Cache {
    private final PriorityQueue<String> keys;
    private final Integer capacity;
    private final Cache cache;
    private final Integer readTime;
    private final Integer writeTime;

    public Cache(PriorityQueue<String> keys, Integer capacity, Cache cache, Integer readTime, Integer writeTime) {
        this.keys = keys;
        this.capacity = capacity;
        this.cache = cache;
        this.readTime = readTime;
        this.writeTime = writeTime;
    }

    public Integer readKey(String key) {
        Integer readTime = 0;
        if(isPresent(key)) {
            return this.readTime;
        }
        readTime += this.readTime + (cache != null ? cache.readKey(key) : 0);
        readTime += writeToCurrentCache(key);
        return readTime;
    }

    private Integer writeToCurrentCache(String key) {
        this.keys.add(key);
        return this.writeTime;
    }

    private boolean isPresent(String key) {
        return keys.contains(key);
    }

    public Integer writeKey(String key) {
        if(isPresent(key)) {
            return this.readTime;
        }
        return this.readTime + writeToCurrentCache(key) + (cache != null ? cache.writeKey(key) : 0);
    }
}
