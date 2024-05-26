package com.example.homehub;

public class Expenses {
    private String name;
    private double quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Expenses(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
