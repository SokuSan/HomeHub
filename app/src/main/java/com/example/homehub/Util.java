package com.example.homehub;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Util {

 @RequiresApi(api = Build.VERSION_CODES.O)
    public ChoresList initializeChores() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ChoresList();
        }
        return new ChoresList();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ExpensesList initializeExpenses(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ExpensesList(context);
        }
        return new ExpensesList(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ShoppingList initializeShopping() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ShoppingList();
        }
        return new ShoppingList();
    }
}
