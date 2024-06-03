package com.example.homehub;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList implements Serializable {
    private List<Shopping> shoppingList = new ArrayList<>();
    private final String filename = "shopping.txt";
    public ShoppingList(Context context) {
            loadFromFile(context);
    }

    public List<Shopping> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void addShopping(Shopping shopping, Context context) {
        shoppingList.add(shopping);
        saveToShoppingFile(context, shoppingList);
    }

    private void loadFromFile(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 1) {
                    String name = parts[0];
                    shoppingList.add(new Shopping(name));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (shoppingList.isEmpty()) {
            addShopping(new Shopping("Example"), context);
        }
    }

    public void saveToShoppingFile(Context context, List<Shopping> shoppingList) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            for (Shopping shopping : this.shoppingList) {
                bufferedWriter.write(shopping.getName() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
