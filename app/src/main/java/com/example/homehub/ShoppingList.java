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
                // Parsear cada línea como un gasto y agregarlo a la lista
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

        // Si la lista está vacía después de cargar desde el archivo, generar una nueva lista
        if (shoppingList.isEmpty()) {
            generateDefaultList(context);
        }
    }

    private void generateDefaultList(Context context) {
        addShopping(new Shopping("Milk"), context);
        addShopping(new Shopping("Bread"), context);
        addShopping(new Shopping("Eggs"), context);
        addShopping(new Shopping("Butter"), context);
        addShopping(new Shopping("Cheese"), context);
        addShopping(new Shopping("Chicken breast"), context);
        addShopping(new Shopping("Ground beef"), context);
        addShopping(new Shopping("Carrots"), context);
        addShopping(new Shopping("Broccoli"), context);
        addShopping(new Shopping("Apples"), context);
        addShopping(new Shopping("Bananas"), context);
        addShopping(new Shopping("Oranges"), context);
        addShopping(new Shopping("Tomatoes"), context);
        addShopping(new Shopping("Lettuce"), context);
        addShopping(new Shopping("Potatoes"), context);
        addShopping(new Shopping("Onions"), context);
        addShopping(new Shopping("Garlic"), context);
        addShopping(new Shopping("Rice"), context);
        addShopping(new Shopping("Pasta"), context);
        addShopping(new Shopping("Olive oil"), context);
        addShopping(new Shopping("Salt"), context);
        addShopping(new Shopping("Pepper"), context);
        addShopping(new Shopping("Yogurt"), context);
        addShopping(new Shopping("Orange juice"), context);
        addShopping(new Shopping("Cereal"), context);
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
