package com.example.homehub;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpensesRecyclerViewAdapter extends RecyclerView.Adapter<ExpensesRecyclerViewAdapter.MyHolder> {

    private Context context;
    private List<Expenses> expensesList;
    private ExpensesList expensesListInstance;

    public ExpensesRecyclerViewAdapter(Context context, List<Expenses> expensesList) {
        this.context = context;
        this.expensesList = expensesList;
        this.expensesListInstance = new ExpensesList(context);
    }

    @NonNull
    @Override
    public ExpensesRecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_expenses, parent, false);
        return new ExpensesRecyclerViewAdapter.MyHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ExpensesRecyclerViewAdapter.MyHolder holder, int position) {
        Expenses expenses = expensesList.get(position);
        holder.name.setText(expenses.getName());
        holder.quantity.setText(String.valueOf(expenses.getQuantity()));

        holder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    expensesList.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    expensesListInstance.setExpensesList(expensesList);
                    expensesListInstance.saveToExpensesFile(expensesList, context);
                    Toast.makeText(context, "Deleted Line", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;
        private ImageView bin;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recyclerVExTvName);
            quantity = itemView.findViewById(R.id.recyclerVExTvMoneyQuantity);
            bin = itemView.findViewById(R.id.recyclerVExImgBin);
        }
    }
}
