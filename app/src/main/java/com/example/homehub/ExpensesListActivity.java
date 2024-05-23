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

public class ExpensesListActivity extends AppCompatActivity {
    private Button add;
    private Util util;
    private TextView total;
    private ExpensesList expensesList;
    private ExpensesRecyclerViewAdapter expensesRecyclerViewAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        Intent intent = getIntent();
        util = new Util();
        add = findViewById(R.id.expensesButtonAdd);
        total = findViewById(R.id.expensesTvQuantity);
        expensesList = util.initializeExpenses();

        assignment();
        total.setText(expensesList.calculateTotalQuantity());
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddExpensesActivity addExpensesActivity = new AddExpensesActivity();
                addExpensesActivity.show(getSupportFragmentManager(), "");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void assignment() {
        RecyclerView recyclerView = findViewById(R.id.expensesRecyclerView);
        expensesRecyclerViewAdapter = new ExpensesRecyclerViewAdapter(this, expensesList.getExpensesList());
        recyclerView.setAdapter(expensesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
