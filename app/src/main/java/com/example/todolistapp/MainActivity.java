package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewItems;
    ToDoListAdapter toDoListAdapter;
    ImageButton addButton;
    List<ToDoListModel> listToDo;
    TextView tvCountTask;

    DatabaseHelper databaseHelper;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma = this;
        addButton = findViewById(R.id.addButton);
        recyclerViewItems = findViewById(R.id.recyclerViewItems);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        showToDoOnRecyclerView();
        showCountTasks();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewToDoListActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showCountTasks() {
        List<ToDoListModel> takeData = new ArrayList<>();

        takeData = databaseHelper.getEveryone();
        int amountOfToDoList = takeData.size();

        tvCountTask = findViewById(R.id.tvCountTask);
        if (amountOfToDoList == 1) {
            tvCountTask.setText("Today you have " +amountOfToDoList+ " task");
        }
        else if (amountOfToDoList > 1){
            tvCountTask.setText("Today you have " +amountOfToDoList+ " tasks");
        }
        else {
            tvCountTask.setText("Today you have no tasks");
        }
    }


    public void showToDoOnRecyclerView() {

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        toDoListAdapter = new ToDoListAdapter(MainActivity.this, (ArrayList<ToDoListModel>) databaseHelper.getEveryone());
        recyclerViewItems.setAdapter(toDoListAdapter);
        toDoListAdapter.notifyDataSetChanged();

    }
}
