
package com.example.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonStorage {

    private static final String FILE_NAME = "inventory.json";

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveItems(List<Item> items) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(FILE_NAME), items);

            System.out.println("Inventory saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

public static List<Item> loadItems() {
    File file = new File(FILE_NAME);

    if (!file.exists()) {
        return new java.util.ArrayList<>();
    }

    try {
        return mapper.readValue(
            file,
            mapper.getTypeFactory()
                  .constructCollectionType(List.class, Item.class)
        );
    } catch (IOException e) {
        System.out.println("Error loading inventory: " + e.getMessage());
        return new java.util.ArrayList<>();
    }
}

}
