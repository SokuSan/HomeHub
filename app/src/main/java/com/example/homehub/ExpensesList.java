package com.example.homehub;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpensesList implements Serializable {
    private List<Expenses> expensesList = new ArrayList<>();
    private final String filename = "expenses.txt";

    public ExpensesList(Context context) {
        loadFromFile(context);
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }

    public void addExpenses(Expenses expenses, Context context) {
        expensesList.add(expenses);
        saveToExpensesFile(expensesList, context);
    }

    private void loadFromFile(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double quantity = Double.parseDouble(parts[1]);
                    expensesList.add(new Expenses(name, quantity));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (expensesList.isEmpty()) {
            generateList(context);
        }
    }

    public void saveToExpensesFile(List<Expenses> expensesList, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            for (Expenses expenses : this.expensesList) {
                bufferedWriter.write(expenses.getName() + "," + expenses.getQuantity() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateList(Context context) {
        addExpenses(new Expenses("Received 10€", 10.0), context);
        addExpenses(new Expenses("Grocery shopping", -50.0), context);
        addExpenses(new Expenses("Rent payment", -600.0), context);
        addExpenses(new Expenses("Monthly salary", 2000.0), context);
        addExpenses(new Expenses("Sold bicycle", 150.0), context);
        addExpenses(new Expenses("Electricity bill", -75.0), context);
        addExpenses(new Expenses("Birthday gift", 50.0), context);
        addExpenses(new Expenses("Dinner at restaurant", -30.0), context);
        addExpenses(new Expenses("Tax refund", 300.0), context);
        addExpenses(new Expenses("Clothing purchase", -100.0), context);
        addExpenses(new Expenses("Bank interest", 15.0), context);
        addExpenses(new Expenses("Movie tickets", -20.0), context);
        addExpenses(new Expenses("Sold used books", 25.0), context);
        addExpenses(new Expenses("Water bill", -40.0), context);
        addExpenses(new Expenses("Product return", 60.0), context);
        addExpenses(new Expenses("School supplies", -25.0), context);
        addExpenses(new Expenses("Property rental income", 500.0), context);
        addExpenses(new Expenses("Medicine purchase", -15.0), context);
        addExpenses(new Expenses("Anniversary gift", 100.0), context);
        addExpenses(new Expenses("Gym membership", -45.0), context);
        addExpenses(new Expenses("Performance bonus", 200.0), context);
        addExpenses(new Expenses("Gasoline purchase", -60.0), context);
        addExpenses(new Expenses("Car insurance payment", -200.0), context);
        addExpenses(new Expenses("Sold old furniture", 80.0), context);
    }

    public String calculateTotalQuantity() {
        double total = 0;
        for (Expenses expenses : expensesList) {
            total += expenses.getQuantity();
        }
        return String.valueOf(total);
    }
}
