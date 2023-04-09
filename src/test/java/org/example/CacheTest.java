package org.example;

import org.example.models.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    Cache cache;
    Cache cacheLvl2;
    Cache cacheLvl3;

    @BeforeEach
    void setUp() {
        PriorityQueue<String> priorityQueue1 = new PriorityQueue<>();
        priorityQueue1.add("key1");
        PriorityQueue<String> priorityQueue2 = new PriorityQueue<>();
        priorityQueue2.add("key2");
        PriorityQueue<String> priorityQueue3 = new PriorityQueue<>();
        priorityQueue3.add("key3");
        priorityQueue3.add("key4");
        priorityQueue3.add("key5");
        priorityQueue3.add("key6");
        priorityQueue3.add("key7");
        priorityQueue3.add("key8");
        priorityQueue3.add("key9");
        priorityQueue3.add("key10");
        priorityQueue3.add("key11");
        priorityQueue3.add("key12");
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