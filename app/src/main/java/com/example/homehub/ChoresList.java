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

public class ChoresList implements Serializable {
    private List<Chores> choresList = new ArrayList<>();
    private final String filePath = "path/to/your/file/dishes.dat"; // Reemplaza con la ruta real del archivo

    public ChoresList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadFromFile();
        }
    }

    public List<Chores> getChoresList() {
        return choresList;
    }

    public void setChoresList(List<Chores> choresList) {
        this.choresList = choresList;
    }

    public void addChores(Chores chore) {
        choresList.add(chore);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            choresList = (List<Chores>) ois.readObject();
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
        choresList.add(new Chores("Walk the dog", "Take the dog for a walk for at least 1 hour"));
        choresList.add(new Chores("Wash the dishes", "Wash all dirty dishes after dinner"));
        choresList.add(new Chores("Clean the bathroom", "Clean the toilet, sink, and shower"));
        choresList.add(new Chores("Make the bed", "Make the bed and smooth the sheets"));
        choresList.add(new Chores("Sweep the floor", "Sweep all the rooms in the house"));
        choresList.add(new Chores("Mop the floor", "Mop the floors in the kitchen and bathroom"));
        choresList.add(new Chores("Take out the trash", "Empty all trash bins and take out the trash"));
        choresList.add(new Chores("Mow the lawn", "Mow the front and back yard lawns"));
        choresList.add(new Chores("Do the laundry", "Wash, dry, and fold the laundry"));
        choresList.add(new Chores("Clean windows", "Clean all the windows in the house"));
        choresList.add(new Chores("Dust the furniture", "Dust the furniture and flat surfaces"));
        choresList.add(new Chores("Organize the garage", "Tidy and clean the garage"));
        choresList.add(new Chores("Iron the clothes", "Iron the shirts and pants"));
        choresList.add(new Chores("Water the plants", "Water all indoor and outdoor plants"));
        choresList.add(new Chores("Cook dinner", "Prepare and cook dinner for the family"));
        choresList.add(new Chores("Wash the car", "Wash and clean the car inside and out"));
        choresList.add(new Chores("Vacuum the house", "Vacuum all carpets and floors in the house"));
        choresList.add(new Chores("Clean the fridge", "Empty and clean the inside of the fridge"));
        choresList.add(new Chores("Organize the pantry", "Sort and clean the pantry shelves"));
        choresList.add(new Chores("Walk the cat", "Take the cat for a walk with a harness and leash"));
        choresList.add(new Chores("Do the shopping", "Go to the supermarket and buy the necessary groceries"));
        choresList.add(new Chores("Collect the mail", "Collect and organize the mail from the mailbox"));
        choresList.add(new Chores("Dust the curtains", "Dust the curtains in all the rooms"));
        choresList.add(new Chores("Clean garden furniture", "Clean and tidy the patio furniture"));
        choresList.add(new Chores("Change the sheets", "Change the sheets on all the beds"));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(choresList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
