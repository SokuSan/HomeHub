package com.example.homehub;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingRecyclerViewAdapter.MyHolder>{

    private Context context;
    private List<Shopping> shoppingList;
    private MyHolder holder;


    public ShoppingRecyclerViewAdapter(Context context, List<Shopping> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @NonNull
    @Override
    public ShoppingRecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_shopping, parent, false);
        return new ShoppingRecyclerViewAdapter.MyHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ShoppingRecyclerViewAdapter.MyHolder holder, int position) {
        Shopping shopping = shoppingList.get(position);
        holder.name.setText(shopping.getName());


        holder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    shoppingList.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    Toast.makeText(context, "Deleted Line", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView bin;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recyclerVShTvName);
            bin = itemView.findViewById(R.id.recyclerVShImgBin);
        }
    }
}


