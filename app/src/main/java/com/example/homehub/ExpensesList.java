package com.example.homehub;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

    public class ExpensesList {
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
            saveToFile(context); // Guardar en archivo cada vez que se agrega un gasto
        }

        private void loadFromFile(Context context) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    // Parsear cada línea como un gasto y agregarlo a la lista
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int quantity = Integer.parseInt(parts[1]);
                        expensesList.add(new Expenses(name, quantity));
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Si la lista está vacía después de cargar desde el archivo, generar una nueva lista
            if (expensesList.isEmpty()) {
                generateList(context);
            }
        }

        private void saveToFile(Context context) {
            try {
                FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
                for (Expenses expenses : expensesList) {
                    bufferedWriter.write(expenses.getName() + "," + expenses.getQuantity() + "\n");
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void generateList(Context context) {
            addExpenses(new Expenses("Received 10€", 10), context);
            addExpenses(new Expenses("Grocery shopping", -50), context);
            addExpenses(new Expenses("Rent payment", -600), context);
            addExpenses(new Expenses("Monthly salary", 2000), context);
            addExpenses(new Expenses("Sold bicycle", 150), context);
            addExpenses(new Expenses("Electricity bill", -75), context);
            addExpenses(new Expenses("Birthday gift", 50), context);
            addExpenses(new Expenses("Dinner at restaurant", -30), context);
            addExpenses(new Expenses("Tax refund", 300), context);
            addExpenses(new Expenses("Clothing purchase", -100), context);
            addExpenses(new Expenses("Bank interest", 15), context);
            addExpenses(new Expenses("Movie tickets", -20), context);
            addExpenses(new Expenses("Sold used books", 25), context);
            addExpenses(new Expenses("Water bill", -40), context);
            addExpenses(new Expenses("Product return", 60), context);
            addExpenses(new Expenses("School supplies", -25), context);
            addExpenses(new Expenses("Property rental income", 500), context);
            addExpenses(new Expenses("Medicine purchase", -15), context);
            addExpenses(new Expenses("Anniversary gift", 100), context);
            addExpenses(new Expenses("Gym membership", -45), context);
            addExpenses(new Expenses("Performance bonus", 200), context);
            addExpenses(new Expenses("Gasoline purchase", -60), context);
            addExpenses(new Expenses("Car insurance payment", -200), context);
            addExpenses(new Expenses("Sold old furniture", 80), context);
        }

        public String calculateTotalQuantity() {
            int total = 0;
            for (Expenses expenses : expensesList) {
                total += expenses.getQuantity();
            }
            return String.valueOf(total);
        }
    }

