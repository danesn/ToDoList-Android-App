package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LandingActivity extends AppCompatActivity {

    Button buttonGetStarted;
    EditText edtName;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        buttonGetStarted = findViewById(R.id.buttonGetStarted);
        edtName = findViewById(R.id.edtName);
        databaseHelper = new DatabaseHelper(LandingActivity.this);

        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getName = edtName.getText().toString();

                // check if input is empty or not
                if (getName.length()>0) {
                    //if not then insert name data into database
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    String sqlQuery = "INSERT INTO USER_TABLE (NAME) VALUES ('" +getName+ "');";
                    db.execSQL(sqlQuery);

                    // close database
                    db.close();

                    // then move to main activity
                    Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    edtName.setError("It must be fill");
                }


            }
        });

    }
}
