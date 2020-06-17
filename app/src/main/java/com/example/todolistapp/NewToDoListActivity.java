package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewToDoListActivity extends AppCompatActivity {

    Button buttonAddToDo;
    EditText edtTitle, edtDesc, edtDate;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do_list);

        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtDate = findViewById(R.id.edtDate);
        buttonAddToDo = findViewById(R.id.buttonAddToDo);

        buttonAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoListModel toDoListModel;
                String titleToDo, detailToDo, dateToDo;

                titleToDo = edtTitle.getText().toString();
                detailToDo = edtDesc.getText().toString();
                dateToDo = edtDate.getText().toString();
                int defaultID = 1;

                // check if input is empty
               if (titleToDo.length()>0 && detailToDo.length()>0 && dateToDo.length()>0){
                    // if not empty insert data object into database
                    toDoListModel = new ToDoListModel (defaultID , titleToDo, detailToDo, dateToDo);
                    databaseHelper = new DatabaseHelper(getApplicationContext());

                    boolean isSuccess = databaseHelper.addOne(toDoListModel);
                    Toast.makeText(getApplicationContext(), "Success added new todo", Toast.LENGTH_SHORT).show();

                    MainActivity.ma.showCountTasks();
                    MainActivity.ma.showToDoOnRecyclerView();
                    finish();
                }
                else {
                    // if empty
                    Toast.makeText(getApplicationContext(), "Text cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
