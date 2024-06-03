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

        if (choresList.isEmpty()) {
            addChores(new Chores("Example", "Example of a description"), context);
        }
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
