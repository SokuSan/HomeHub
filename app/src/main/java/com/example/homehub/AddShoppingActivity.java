package com.example.homehub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.Serializable;

public class AddShoppingActivity extends AppCompatDialogFragment implements Serializable {

    private AddShoppingDialogInterface addShoppingDialogInterface;
    private EditText name;
    private ShoppingList shoppingList;
    private Util util;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.floatinglayout_shopping, null);

        name = view.findViewById(R.id.etName);

        builder.setView(view)
                .setPositiveButton("Create", (dialogInterface, i) -> {
                    String name = this.name.getText().toString();

                    Shopping shopping = new Shopping(name);
                    util = new Util();
                    shoppingList = util.initializeShopping();
                    shoppingList.addShopping(shopping);

                    addShoppingDialogInterface.AddShoppingActivity(shopping);
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addShoppingDialogInterface = (AddShoppingDialogInterface) context;
    }
}
