package com.example.homehub;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpensesList implements Serializable {
    private List<Expenses> expensesList = new ArrayList<>();
    private final String filePath = "path/to/your/file/dishes.dat"; // Reemplaza con la ruta real del archivo

    public ExpensesList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadFromFile();
        }
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }

    public void addExpenses(Expenses expenses) {
        expensesList.add(expenses);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            expensesList = (List<Expenses>) ois.readObject();
        } catch (FileNotFoundException e) {
            generateDefaultList();
            saveToFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            generateDefaultList();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateDefaultList() {
        expensesList.add(new Expenses("Received 10€", 10));
        expensesList.add(new Expenses("Grocery shopping", -50));
        expensesList.add(new Expenses("Rent payment", -600));
        expensesList.add(new Expenses("Monthly salary", 2000));
        expensesList.add(new Expenses("Sold bicycle", 150));
        expensesList.add(new Expenses("Electricity bill", -75));
        expensesList.add(new Expenses("Birthday gift", 50));
        expensesList.add(new Expenses("Dinner at restaurant", -30));
        expensesList.add(new Expenses("Tax refund", 300));
        expensesList.add(new Expenses("Clothing purchase", -100));
        expensesList.add(new Expenses("Bank interest", 15));
        expensesList.add(new Expenses("Movie tickets", -20));
        expensesList.add(new Expenses("Sold used books", 25));
        expensesList.add(new Expenses("Water bill", -40));
        expensesList.add(new Expenses("Product return", 60));
        expensesList.add(new Expenses("School supplies", -25));
        expensesList.add(new Expenses("Property rental income", 500));
        expensesList.add(new Expenses("Medicine purchase", -15));
        expensesList.add(new Expenses("Anniversary gift", 100));
        expensesList.add(new Expenses("Gym membership", -45));
        expensesList.add(new Expenses("Performance bonus", 200));
        expensesList.add(new Expenses("Gasoline purchase", -60));
        expensesList.add(new Expenses("Car insurance payment", -200));
        expensesList.add(new Expenses("Sold old furniture", 80));
        expensesList.add(new Expenses("Electronics purchase", -150));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(expensesList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int calculateTotalQuantity() {
        int total = 0;
        for (Expenses expenses : expensesList) {
            total += expenses.getQuantity();
        }
        return total;
    }

}
