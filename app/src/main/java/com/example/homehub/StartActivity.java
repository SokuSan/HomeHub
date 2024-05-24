package com.example.homehub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private ImageView expensesImg;
    private TextView expensesTv;
    private ImageView choresImg;
    private TextView choresTv;
    private ImageView shoppingImg;
    private TextView shoppingTv;
    private ImageView options;
    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent intent = getIntent();
        expensesImg = findViewById(R.id.startImgChart);
        expensesTv = findViewById(R.id.startTvExpenses);
        choresImg = findViewById(R.id.startImgHouse);
        choresTv = findViewById(R.id.startTvChores);
        shoppingImg = findViewById(R.id.startImgShopping);
        shoppingTv = findViewById(R.id.startTvShopping);
        options = findViewById(R.id.startImgOptions);
        page = "none";

        expensesImg.setOnClickListener(v -> {
            page = "expenses";
            travelIntent(page);
        });
        expensesTv.setOnClickListener(v -> {
            page = "expenses";
            travelIntent(page);
        });
        choresTv.setOnClickListener(v -> {
            page = "chores";
            travelIntent(page);
        });
        choresImg.setOnClickListener(v -> {
            page = "chores";
            travelIntent(page);
        });
        shoppingTv.setOnClickListener(v -> {
            page = "shopping";
            travelIntent(page);
        });
        shoppingImg.setOnClickListener(v -> {
            page = "shopping";
            travelIntent(page);
        });
        options.setOnClickListener(v -> {
            page = "options";
            travelIntent(page);
        });


    }
    public void travelIntent(String page){
        Intent intent = new Intent (StartActivity.this, StartActivity.class);;
        switch(page){
            case "chores" :
                intent = new Intent (StartActivity.this, ChoresList.class);
                break;
            case "shopping" :
                intent = new Intent (StartActivity.this, ShoppingListActivity.class);
                break;
            case "expenses" :
                intent = new Intent (StartActivity.this, ExpensesListActivity.class);
                break;
            case "options" :
                intent = new Intent (StartActivity.this, OptionsActivity.class);
                break;
        }
        startActivity(intent);
    }


}
