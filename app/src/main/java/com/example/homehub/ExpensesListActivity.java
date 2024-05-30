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

public class ExpensesListActivity extends AppCompatActivity implements AddExpensesDialogInterface {
    private FloatingActionButton add;
    private ImageView back;
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
        back = findViewById(R.id.expensesBackbtn);
        add = findViewById(R.id.expensesBtnAdd);
        total = findViewById(R.id.expensesTvQuantity);
        expensesList = util.initializeExpenses(getApplicationContext());

        assignment();
        updateTotalText();
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddExpensesActivity addExpensesActivity = new AddExpensesActivity();
                addExpensesActivity.show(getSupportFragmentManager(), "");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentBack = new Intent(ExpensesListActivity.this, StartActivity.class);
                startActivity(intentBack);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void assignment() {
        RecyclerView recyclerView = findViewById(R.id.expensesRecyclerView);
        expensesRecyclerViewAdapter = new ExpensesRecyclerViewAdapter(this, expensesList.getExpensesList(), new UpdateTotalCallback() {
            @Override
            public void updateTotal() {
                updateTotalText();
            }
        });
        recyclerView.setAdapter(expensesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateTotalText() {
        total.setText(expensesList.calculateTotalQuantity());
    }

    @Override
    public void AddExpensesActivity(Expenses expenses) {
        expensesList.addExpenses(expenses, getApplicationContext());
        expensesRecyclerViewAdapter.notifyItemInserted(expensesList.getExpensesList().size() - 1);
        updateTotalText();
    }
}

interface UpdateTotalCallback {
    void updateTotal();
}
