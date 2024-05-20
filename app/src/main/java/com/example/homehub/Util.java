package com.example.homehub;

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
    public ExpensesList initializeExpenses() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ExpensesList();
        }
        return new ExpensesList();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ShoppingList initializeShopping() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new ShoppingList();
        }
        return new ShoppingList();
    }
    /*public static Intent navigation(MenuItem item, AppCompatActivity activity, ChoresList dishList) {
        int itemId = item.getItemId();
        Intent intent = null;

        if (itemId == R.id.dishList) {
            intent = new Intent(activity, ListActivity.class);
        } else if (itemId == R.id.options) {
            intent = new Intent(activity, OptionsActivity.class);
        } else if (itemId == R.id.start) {
            intent = new Intent(activity, StartActivity.class);
        }
        intent.putExtra("dishList", (Serializable) dishList.getDishList());

        return intent;
    }*/
}
