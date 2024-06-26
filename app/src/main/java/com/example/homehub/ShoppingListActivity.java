package com.example.homehub;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingListActivity extends AppCompatActivity implements AddShoppingDialogInterface{
    private FloatingActionButton add;
    private Util util;
    private ImageView back;
    private ShoppingList shoppingList;
    private ShoppingRecyclerViewAdapter shoppingRecyclerViewAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        Intent intent = getIntent();
        util = new Util();
        back = findViewById(R.id.shoppingImgBack);
        add = findViewById(R.id.shoppingBtnAdd);
        shoppingList = util.initializeShopping(getApplicationContext());

        assignment();

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddShoppingActivity addShoppingActivity = new AddShoppingActivity();
                addShoppingActivity.show(getSupportFragmentManager(), "");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentBack = new Intent(ShoppingListActivity.this, StartActivity.class);
                startActivity(intentBack);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void assignment() {
        RecyclerView recyclerView = findViewById(R.id.shoppingRecyclerView);
        shoppingRecyclerViewAdapter = new ShoppingRecyclerViewAdapter(this, shoppingList.getShoppingList());
        recyclerView.setAdapter(shoppingRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void AddShoppingActivity(Shopping shopping) {
        shoppingList.addShopping(shopping, getApplicationContext());
        shoppingRecyclerViewAdapter.notifyItemInserted(shoppingList.getShoppingList().size() - 1);
    }
}
