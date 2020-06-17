package com.example.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ToDoListModel> toDoList;

    public ToDoListAdapter(Context c, ArrayList<ToDoListModel> p) {
        context = c;
        toDoList = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.titleToDoList.setText(toDoList.get(position).getTitleToDoList());
        myViewHolder.descToDoList.setText(toDoList.get(position).getDescToDoList());
        myViewHolder.dateToDoList.setText(toDoList.get(position).getDateToDoList());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleToDoList, descToDoList, dateToDoList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleToDoList = (TextView)itemView.findViewById(R.id.titleToDoList);
            descToDoList = (TextView)itemView.findViewById(R.id.descToDoList);
            dateToDoList = (TextView)itemView.findViewById(R.id.dateToDoList);


        }
    }

}
