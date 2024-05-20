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

public class ShoppingList implements Serializable {
    private List<Shopping> shoppingList = new ArrayList<>();
    private final String filePath = "path/to/your/file/dishes.dat"; // Reemplaza con la ruta real del archivo

    public ShoppingList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadFromFile();
        }
    }

    public List<Shopping> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void addShopping(Shopping shopping) {
        shoppingList.add(shopping);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            shoppingList = (List<Shopping>) ois.readObject();
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
        shoppingList.add(new Shopping("Milk"));
        shoppingList.add(new Shopping("Bread"));
        shoppingList.add(new Shopping("Eggs"));
        shoppingList.add(new Shopping("Butter"));
        shoppingList.add(new Shopping("Cheese"));
        shoppingList.add(new Shopping("Chicken breast"));
        shoppingList.add(new Shopping("Ground beef"));
        shoppingList.add(new Shopping("Carrots"));
        shoppingList.add(new Shopping("Broccoli"));
        shoppingList.add(new Shopping("Apples"));
        shoppingList.add(new Shopping("Bananas"));
        shoppingList.add(new Shopping("Oranges"));
        shoppingList.add(new Shopping("Tomatoes"));
        shoppingList.add(new Shopping("Lettuce"));
        shoppingList.add(new Shopping("Potatoes"));
        shoppingList.add(new Shopping("Onions"));
        shoppingList.add(new Shopping("Garlic"));
        shoppingList.add(new Shopping("Rice"));
        shoppingList.add(new Shopping("Pasta"));
        shoppingList.add(new Shopping("Olive oil"));
        shoppingList.add(new Shopping("Salt"));
        shoppingList.add(new Shopping("Pepper"));
        shoppingList.add(new Shopping("Yogurt"));
        shoppingList.add(new Shopping("Orange juice"));
        shoppingList.add(new Shopping("Cereal"));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shoppingList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
