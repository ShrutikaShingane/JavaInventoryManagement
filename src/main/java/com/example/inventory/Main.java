
package com.example.inventory;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT =====");
            System.out.println("1. Add Item");
            System.out.println("2. Find Item by ID");
            System.out.println("3. Decrease Stock");
            System.out.println("4. Display All Items");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        System.out.print("Enter item ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter item name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter quantity: ");
                        int quantity =
                            Integer.parseInt(sc.nextLine().trim());

                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            break;
                        }

                        Item item = new Item(id, name, quantity);
                        inventory.addItem(item);
                        break;

                    case 2:
                        System.out.print("Enter item ID to search: ");
                        int searchId =
                            Integer.parseInt(sc.nextLine().trim());

                        Optional<Item> result =
                            inventory.findItemById(searchId);

                        if (result.isPresent()) {
                            System.out.println(result.get());
                        } else {
                            System.out.println("Item not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter item ID: ");
                        int stockId =
                            Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter quantity to decrease: ");
                        int amount =
                            Integer.parseInt(sc.nextLine().trim());

                        inventory.decreaseStock(stockId, amount);
                        break;

                    case 4:
                        inventory.displayAllItems();
                        break;

                    case 5:
                        System.out.println("Exiting Inventory System...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InsufficientStockException e) {
                System.out.println("Stock Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric values.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }
}
