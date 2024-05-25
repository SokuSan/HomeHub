package com.example.homehub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.Serializable;

public class AddChoresActivity extends AppCompatDialogFragment implements Serializable {

    private AddChoresDialogInterface addChoresDialogInterface;
    private EditText name;
    private EditText description;
    private ChoresList choresList;
    private Util util;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.floatinglayout_chores, null);

        name = view.findViewById(R.id.atName);
        description = view.findViewById(R.id.etDescription);

        builder.setView(view)
                .setPositiveButton("Create", (dialogInterface, i) -> {
                    String name = this.name.getText().toString();
                    String description = this.description.getText().toString();

                    Chores chores = new Chores(name, description);
                    util = new Util();
                    choresList = util.initializeChores(getContext());
                    choresList.addChores(chores, getContext());

                    addChoresDialogInterface.AddChoresActivity(chores);
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addChoresDialogInterface = (AddChoresDialogInterface) context;
    }

}
