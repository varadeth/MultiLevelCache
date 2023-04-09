package org.example.models;

import java.util.Date;
import java.util.PriorityQueue;

public class Cache {
    private final PriorityQueue<Item> keys;
    private final Integer capacity;
    private final Cache cache;
    private final Integer readTime;
    private final Integer writeTime;

    public Cache(PriorityQueue<Item> keys, Integer capacity, Cache cache, Integer readTime, Integer writeTime) {
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
        this.keys.add(new Item(key, new Date().getTime()));
        return this.writeTime;
    }

    private boolean isPresent(String key) {
        return keys.stream().anyMatch(item -> item.isKey(key));
    }

    public Integer writeKey(String key) {
        if(isPresent(key)) {
            return this.readTime;
        }
        return this.readTime + writeToCurrentCache(key) + (cache != null ? cache.writeKey(key) : 0);
    }
}
