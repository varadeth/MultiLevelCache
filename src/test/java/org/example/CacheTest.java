package org.example;

import org.example.models.Cache;
import org.example.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    Cache cache;
    Cache cacheLvl2;
    Cache cacheLvl3;

    @BeforeEach
    void setUp() {
        PriorityQueue<Item> priorityQueue1 = new PriorityQueue<>();
        priorityQueue1.add(new Item("key1", new Date().getTime()));
        PriorityQueue<Item> priorityQueue2 = new PriorityQueue<>();
        priorityQueue2.add(new Item("key2", new Date().getTime()));
        PriorityQueue<Item> priorityQueue3 = new PriorityQueue<>();
        priorityQueue3.add(new Item("key3", new Date().getTime()));
        priorityQueue3.add(new Item("key4", new Date().getTime()));
        priorityQueue3.add(new Item("key5", new Date().getTime()));
        priorityQueue3.add(new Item("key6", new Date().getTime()));
        priorityQueue3.add(new Item("key7", new Date().getTime()));
        priorityQueue3.add(new Item("key8", new Date().getTime()));
        priorityQueue3.add(new Item("key9", new Date().getTime()));
        priorityQueue3.add(new Item("key10", new Date().getTime()));
        priorityQueue3.add(new Item("key11", new Date().getTime()));
        priorityQueue3.add(new Item("key12", new Date().getTime()));
        cacheLvl3 = new Cache(priorityQueue3, 10, null, 2, 5);
        cacheLvl2 = new Cache(priorityQueue2, 10, cacheLvl3, 1, 2);
        cache = new Cache(priorityQueue1, 10, cacheLvl2, 1, 1);
    }

    @Test
    void shouldReturnTimeTakenToRetrieveValueFromL1Cache() {
        final Integer expectedReadTime = 1;

        final Integer actualReadTime = cache.readKey("key1");

        assertEquals(expectedReadTime, actualReadTime);
    }

    @Test
    void shouldReturnTimeTakenToRetrieveValueFromL2Cache() {
        final Integer expectedReadTime = 3;

        final Integer actualReadTime = cache.readKey("key2");

        assertEquals(expectedReadTime, actualReadTime);
    }

    @Test
    void shouldReturnTimeTakenToRetrieveValueFromL3Cache() {
        final Integer expectedReadTime = 7;

        final Integer actualReadTime = cache.readKey("key3");

        assertEquals(expectedReadTime, actualReadTime);
    }

    @Test
    void shouldReturnTimeTakenToReadValueThatIsNotPresentInAnyCache() {
        final Integer expectedReadTime = 12;

        final Integer actualReadTime = cache.readKey("key13");

        assertEquals(expectedReadTime, actualReadTime);
    }

    @Test
    void shouldReturnTimeTakenToWriteValueToL1Cache() {
        final Integer expectedWriteTime = 7;

        final Integer actualWriteTime = cache.writeKey("key3");

        assertEquals(expectedWriteTime, actualWriteTime);
    }

    @Test
    void shouldReturnTimeTakenToWriteValueIfAlreadyPresentInL2Cache() {
        final Integer expectedWriteTime = 3;

        final Integer actualWriteTime = cache.writeKey("key2");

        assertEquals(expectedWriteTime, actualWriteTime);
    }
}