package com.example.homehub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChoresListActivity extends AppCompatActivity {
    private FloatingActionButton floatingBtn;
    private Util util;
    private ChoresList choresList;
    private ChoresRecyclerViewAdapter choresRecyclerViewAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chores_list);
        Intent intent = getIntent();
        util = new Util();
        floatingBtn = findViewById(R.id.choresFloatingBtn);
        choresList = util.initializeChores();

        assignment();

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddChoresActivity addChoresActivity = new AddChoresActivity();
                addChoresActivity.show(getSupportFragmentManager(), "");
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void assignment() {
        RecyclerView recyclerView = findViewById(R.id.choresRecyclerView);
        choresRecyclerViewAdapter = new ChoresRecyclerViewAdapter(this, choresList.getChoresList());
        recyclerView.setAdapter(choresRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
