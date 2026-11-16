
package com.example.inventory;

public class Item {

    private int id;
    private String name;
    private int quantity;

    public Item(int id, String name, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(
                "Quantity cannot be negative"
            );
        }

        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseStock(int amount)
            throws InsufficientStockException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero"
            );
        }

        if (amount > quantity) {
            throw new InsufficientStockException(
                "Insufficient stock available!"
            );
        }

        quantity -= amount;
    }

    @Override
    public String toString() {
        return "Item ID: " + id
            + ", Name: " + name
            + ", Quantity: " + quantity;
    }
}
