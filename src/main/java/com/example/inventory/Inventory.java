
package com.example.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Inventory {

    private Map<Integer, Item> items = new HashMap<>();

    public void addItem(Item item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException(
                "Item ID already exists!"
            );
        }

        items.put(item.getId(), item);
        System.out.println("Item added successfully!");
    }

    public Optional<Item> findItemById(int id) {
        return Optional.ofNullable(items.get(id));
    }


public void decreaseStock(int id, int amount)
        throws InsufficientStockException {

    Item item = items.get(id);

    if (item == null) {
        throw new IllegalArgumentException("Item not found!");
    }

    item.decreaseStock(amount);
    System.out.println("Stock updated successfully!");
}


    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        for (Item item : items.values()) {
            System.out.println(item);
        }
    }
}
