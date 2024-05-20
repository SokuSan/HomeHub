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

    ImageView expensesImg;
    TextView expensesTv;
    ImageView choresImg;
    TextView choresTv;
    ImageView shoppingImg;
    TextView shoppingTv;

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

        expensesImg.setOnClickListener(v -> {
            Intent intentTravel = new Intent(this, ExpensesListActivity.class);
            //intentTravel.putExtra("dishList", (Serializable) dishList.getDishList());

        });

    }


}
