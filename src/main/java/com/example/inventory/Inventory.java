
package com.example.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Inventory {

    private Map<Integer, Item> items = new HashMap<>();

    private static final String FILE_NAME = "inventory.json";

    private static final ObjectMapper mapper = new ObjectMapper();

    public Inventory() {
        loadItems();
    }

    public void addItem(Item item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException(
                "Item ID already exists!"
            );
        }

        items.put(item.getId(), item);
        saveItems();

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
        saveItems();

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

    private void saveItems() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(FILE_NAME), items);

            System.out.println("Inventory saved to JSON.");
        } catch (IOException e) {
            System.out.println(
                "Error saving inventory: " + e.getMessage()
            );
        }
    }

    private void loadItems() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {
            items = mapper.readValue(
                file,
                new TypeReference<Map<Integer, Item>>() {}
            );

            System.out.println("Inventory loaded from JSON.");
        } catch (IOException e) {
            System.out.println(
                "Error loading inventory: " + e.getMessage()
            );
        }
    }
}
