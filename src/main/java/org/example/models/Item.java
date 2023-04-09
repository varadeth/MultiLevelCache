package org.example.models;

public class Item implements Comparable<Item>{
    private final String key;
    private final Long accessedTime;

    public Item(String key, long accessedTime) {
        this.key = key;
        this.accessedTime = accessedTime;
    }

    @Override
    public int compareTo(Item o) {
        return (int) (this.accessedTime - o.accessedTime);
    }

    public boolean isKey(String key) {
        return this.key.equals(key);
    }
}
