package org.example;

import org.example.models.Item;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    @Test
    void shouldReturnTrueIfKeyMatched() {
        Item item = new Item("key", new Date().getTime());
        assertTrue(item.isKey("key"));
    }

    @Test
    void shouldReturnNegetiveValueIfAccessedBeforeArgumentItem() throws InterruptedException {
        Item item1 = new Item("key1", new Date().getTime());
        Thread.sleep(300);
        Item item2 = new Item("key2", new Date().getTime());
        assertTrue(item1.compareTo(item2) < 0);
    }

    @Test
    void shouldReturnPositiveValueIfAccessedAfterArgumentItem() throws InterruptedException {
        Item item1 = new Item("key1", new Date().getTime());
        Thread.sleep(300);
        Item item2 = new Item("key2", new Date().getTime());
        assertTrue(item2.compareTo(item1) > 0);
    }

    @Test
    void shouldReturnEqualToZeroIfAccessedTimeIsSame() throws InterruptedException {
        Item item1 = new Item("key1", new Date().getTime());
        Item item2 = new Item("key2", new Date().getTime());
        assertTrue(item2.compareTo(item1) == 0);
    }
}
