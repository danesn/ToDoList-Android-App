package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateToDoListActivity extends AppCompatActivity {

    Button buttonUpdateToDo;
    EditText edtUpdateTitle, edtUpdateDesc, edtUpdateDate;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do_list);

        buttonUpdateToDo = findViewById(R.id.buttonUpdateToDo);
        edtUpdateTitle = findViewById(R.id.edtUpdateTitle);
        edtUpdateDesc = findViewById(R.id.edtUpdateDesc);
        edtUpdateDate = findViewById(R.id.edtUpdateDate);

        // take intent id
        final int takeID = getIntent().getIntExtra("id", 0);

        // search todolist
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TODOLIST_TABLE WHERE ID = " +takeID+ ";", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);

            //set data todolist into edittext
            edtUpdateTitle.setText(cursor.getString(1).toString());
            edtUpdateDesc.setText(cursor.getString(2).toString());
            edtUpdateDate.setText(cursor.getString(3).toString());
        }

        buttonUpdateToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                String titleToDo, detailToDo, dateToDo;

                //get value of edittext
                titleToDo = edtUpdateTitle.getText().toString();
                detailToDo = edtUpdateDesc.getText().toString();
                dateToDo = edtUpdateDate.getText().toString();

                // check if input is empty
                if (titleToDo.length()>0 && detailToDo.length()>0 && dateToDo.length()>0){

                    // if not empty
                    // update into database
                    db.execSQL("UPDATE TODOLIST_TABLE SET TITLE= '" + titleToDo + "', DETAIL='" + detailToDo + "', DATE='" + dateToDo + "' WHERE ID= " + takeID + ";");
                    Toast.makeText(getApplicationContext(), "Success edited todo", Toast.LENGTH_SHORT).show();

                    MainActivity.ma.showToDoOnRecyclerView();
                    MainActivity.ma.showCountTasks();

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
