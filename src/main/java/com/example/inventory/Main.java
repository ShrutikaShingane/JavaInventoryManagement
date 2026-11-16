
package com.example.inventory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        try {
            boolean running = true;

            while (running) {
                System.out.println("\n=== Inventory Management ===");
                System.out.println("1. Add Item to JSON");
                System.out.println("2. Find Item by ID");
                System.out.println("3. Decrease Stock");
                System.out.println("4. Display All Items");
                System.out.println("5. Batch Insert into PostgreSQL");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid number.");
                    continue;
                }

                try {
                    switch (choice) {

                        case 1: {
                            System.out.print("Enter item ID: ");
                            int id = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter item name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter quantity: ");
                            int quantity = Integer.parseInt(sc.nextLine());

                            inventory.addItem(
                                new Item(id, name, quantity)
                            );
                            break;
                        }

                        case 2: {
                            System.out.print("Enter item ID: ");
                            int id = Integer.parseInt(sc.nextLine());

                            inventory.findItemById(id)
                                .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println(
                                        "Item not found!"
                                    )
                                );
                            break;
                        }

                        case 3: {
                            System.out.print("Enter item ID: ");
                            int id = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter quantity to decrease: ");
                            int amount = Integer.parseInt(sc.nextLine());

                            inventory.decreaseStock(id, amount);
                            break;
                        }

                        case 4:
                            inventory.displayAllItems();
                            break;

                        case 5: {
                            List<Item> batch = new ArrayList<>();

                            System.out.print(
                                "How many items to insert? "
                            );
                            int count = Integer.parseInt(sc.nextLine());

                            if (count <= 0) {
                                System.out.println(
                                    "Enter a positive number."
                                );
                                break;
                            }

                            for (int i = 0; i < count; i++) {
                                System.out.println(
                                    "\nEnter details for item " + (i + 1)
                                );

                                System.out.print("ID: ");
                                int id = Integer.parseInt(sc.nextLine());

                                System.out.print("Name: ");
                                String name = sc.nextLine();

                                System.out.print("Quantity: ");
                                int quantity =
                                    Integer.parseInt(sc.nextLine());

                                batch.add(new Item(id, name, quantity));
                            }

                            inventory.batchInsertItems(batch);
                            break;
                        }

                        case 6:
                            running = false;
                            System.out.println("Application closed.");
                            break;

                        default:
                            System.out.println("Invalid choice.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid numeric values.");
                } catch (IllegalArgumentException
                         | InsufficientStockException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (SQLException e) {
                    System.out.println(
                        "Database error: " + e.getMessage()
                    );
                }
            }

        } finally {
            DatabaseConnection.closePool();
            sc.close();
        }
    }
}
