package com.example.homehub;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.Serializable;

public class AddExpensesActivity extends AppCompatDialogFragment implements Serializable {
    private AddExpensesDialogInterface addExpensesDialogInterface;
    private EditText name;
    private EditText quantity;
    private ExpensesList expensesList;
    private Util util;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.floatinglayout_expenses, null);

        name = view.findViewById(R.id.etNameExpenses);
        quantity = view.findViewById(R.id.etQuantityexpenses);

        builder.setView(view)
                .setPositiveButton("Create", (dialogInterface, i) -> {
                    String name = this.name.getText().toString();
                    String quantityStr = this.quantity.getText().toString();

                    if (name.isEmpty()) {
                        Toast.makeText(getContext(), "Name can't be empty.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        if (!quantityStr.matches("[-+]?\\d+(\\.\\d{1,2})?")) {
                            throw new NumberFormatException();
                        }

                        double quantity = Double.parseDouble(quantityStr);

                        Expenses expenses = new Expenses(name, quantity);
                        util = new Util();
                        expensesList = util.initializeExpenses(getContext());
                        expensesList.addExpenses(expenses, getContext());

                        addExpensesDialogInterface.AddExpensesActivity(expenses);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "The number must have up to two decimal places and may start with + or -.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dismiss();
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addExpensesDialogInterface = (AddExpensesDialogInterface) context;
    }
}
