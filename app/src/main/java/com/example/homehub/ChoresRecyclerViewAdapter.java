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

public class ChoresRecyclerViewAdapter extends RecyclerView.Adapter<ChoresRecyclerViewAdapter.MyHolder>{

    private Context context;
    private List<Chores> choresList;
    private ChoresRecyclerViewAdapter.MyHolder holder;


    public ChoresRecyclerViewAdapter(Context context, List<Chores> choresList) {
        this.context = context;
        this.choresList = choresList;
    }

    @NonNull
    @Override
    public ChoresRecyclerViewAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyclerview_chores, parent, false);
        return new ChoresRecyclerViewAdapter.MyHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ChoresRecyclerViewAdapter.MyHolder holder, int position) {
        Chores chores = choresList.get(position);
        holder.name.setText(chores.getName());
        holder.description.setText(chores.getDescription());
        holder.description.setVisibility(View.GONE);


        holder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    choresList.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    Toast.makeText(context, "Deleted Line", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.description.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (holder.description.getVisibility() == View.VISIBLE){
                    holder.description.setVisibility(View.GONE);
                } else {
                    holder.description.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return choresList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private ImageView bin;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recyclerVChTvName);
            description = itemView.findViewById(R.id.recyclerVChTvDescription);
            bin = itemView.findViewById(R.id.recyclerVChImgBin);
        }

        }
    }


