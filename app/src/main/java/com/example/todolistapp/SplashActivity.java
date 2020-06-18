package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMEOUT = 2000;
    ImageView splashIcon;
    TextView createdBy, titleBelowIcon;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(1800);

        splashIcon = findViewById(R.id.iconSplash);
        createdBy = findViewById(R.id.createdBy);
        titleBelowIcon = findViewById(R.id.titleBelowIcon);
        databaseHelper = new DatabaseHelper(SplashActivity.this);

        splashIcon.setAnimation(fadeOut);
        createdBy.setAnimation(fadeOut);
        titleBelowIcon.setAnimation(fadeOut);

        // check whether there is already a username in the database or not
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE;", null);
        Boolean isThereUser = false;

        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            isThereUser = true;
        }


        // Move to new activity
        final Boolean finalIsThereUser = isThereUser;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // check if there user or not (true or false)
                // if there is not any user
                if (finalIsThereUser == false) {
                    Intent intent = new Intent(SplashActivity.this, LandingActivity.class);
                    startActivity(intent);
                    finish();
                }
                // if there is
                else if (finalIsThereUser == true){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
