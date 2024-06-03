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
            addExpenses(new Expenses("Example", 00.0), context);
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

    public String calculateTotalQuantity() {
        double total = 0;
        for (Expenses expenses : expensesList) {
            total += expenses.getQuantity();
        }
        return String.valueOf(total);
    }
}
