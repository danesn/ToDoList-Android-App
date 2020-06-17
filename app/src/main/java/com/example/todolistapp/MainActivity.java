package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewItems;
    ArrayList<ToDoListModel> list;
    ToDoListAdapter toDoListAdapter;
    ImageButton addButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);

        recyclerViewItems = findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ToDoListModel>();

        ToDoListModel alan = new ToDoListModel("asdasdsa", "qwewqe", "popopo");
        ToDoListModel asd = new ToDoListModel("wer", "vbmn", "hjk");

        ToDoListModel alan1 = new ToDoListModel("asdasdsa", "qwewqe", "popopo");
        ToDoListModel asd1 = new ToDoListModel("wer", "vbmn", "hjk");

        ToDoListModel alan2 = new ToDoListModel("asdasdsa", "qwewqe", "popopo");
        ToDoListModel asd2 = new ToDoListModel("wer", "vbmn", "hjk");

        list.add(alan);
        list.add(asd);

        list.add(alan1);
        list.add(asd1);

        list.add(alan2);
        list.add(asd2);


        toDoListAdapter = new ToDoListAdapter(MainActivity.this, list);
        recyclerViewItems.setAdapter(toDoListAdapter);
        toDoListAdapter.notifyDataSetChanged();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewToDoListActivity.class);
                startActivity(intent);
            }
        });

    }
}
