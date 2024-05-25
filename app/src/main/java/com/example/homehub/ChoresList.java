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

public class ChoresList implements Serializable {
    private List<Chores> choresList = new ArrayList<>();
    private final String filename = "chores.txt";
    public ChoresList(Context context) {
            loadFromFile(context);
    }

    public List<Chores> getChoresList() {
        return choresList;
    }

    public void setChoresList(List<Chores> choresList) {
        this.choresList = choresList;
    }

    public void addChores(Chores chore, Context context) {
        choresList.add(chore);
        saveToChoresFile(context, choresList);
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
                    String description = parts[1];
                    choresList.add(new Chores(name, description));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si la lista está vacía después de cargar desde el archivo, generar una nueva lista
        if (choresList.isEmpty()) {
            generateDefaultList(context);
        }
    }

    private void generateDefaultList(Context context) {
        addChores(new Chores("Walk the dog", "Take the dog for a walk for at least 1 hour"), context);
        addChores(new Chores("Wash the dishes", "Wash all dirty dishes after dinner"), context);
        addChores(new Chores("Clean the bathroom", "Clean the toilet, sink, and shower"), context);
        addChores(new Chores("Make the bed", "Make the bed and smooth the sheets"), context);
        addChores(new Chores("Sweep the floor", "Sweep all the rooms in the house"), context);
        addChores(new Chores("Mop the floor", "Mop the floors in the kitchen and bathroom"), context);
        addChores(new Chores("Take out the trash", "Empty all trash bins and take out the trash"), context);
        addChores(new Chores("Mow the lawn", "Mow the front and back yard lawns"), context);
        addChores(new Chores("Do the laundry", "Wash, dry, and fold the laundry"), context);
        addChores(new Chores("Clean windows", "Clean all the windows in the house"), context);
        addChores(new Chores("Dust the furniture", "Dust the furniture and flat surfaces"), context);
        addChores(new Chores("Organize the garage", "Tidy and clean the garage"), context);
        addChores(new Chores("Iron the clothes", "Iron the shirts and pants"), context);
        addChores(new Chores("Water the plants", "Water all indoor and outdoor plants"), context);
        addChores(new Chores("Cook dinner", "Prepare and cook dinner for the family"), context);
        addChores(new Chores("Wash the car", "Wash and clean the car inside and out"), context);
        addChores(new Chores("Vacuum the house", "Vacuum all carpets and floors in the house"), context);
        addChores(new Chores("Clean the fridge", "Empty and clean the inside of the fridge"), context);
        addChores(new Chores("Organize the pantry", "Sort and clean the pantry shelves"), context);
        addChores(new Chores("Walk the cat", "Take the cat for a walk with a harness and leash"), context);
        addChores(new Chores("Do the shopping", "Go to the supermarket and buy the necessary groceries"), context);
        addChores(new Chores("Collect the mail", "Collect and organize the mail from the mailbox"), context);
        addChores(new Chores("Dust the curtains", "Dust the curtains in all the rooms"), context);
        addChores(new Chores("Clean garden furniture", "Clean and tidy the patio furniture"), context);
        addChores(new Chores("Change the sheets", "Change the sheets on all the beds"), context);
    }



    public void saveToChoresFile(Context context, List<Chores> choresList) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            for (Chores chores : this.choresList) {
                bufferedWriter.write(chores.getName() + "," + chores.getDescription() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
