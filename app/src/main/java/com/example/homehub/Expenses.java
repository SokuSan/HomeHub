package com.example.homehub;

public class Expenses {
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Expenses(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
}
