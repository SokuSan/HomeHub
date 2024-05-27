package com.example.homehub;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Util {

 @RequiresApi(api = Build.VERSION_CODES.O)
    public ChoresList initializeChores(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ChoresList(context);
        }
        return new ChoresList(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ExpensesList initializeExpenses(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ExpensesList(context);
        }
        return new ExpensesList(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ShoppingList initializeShopping(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ShoppingList(context);
        }
        return new ShoppingList(context);
    }


}
