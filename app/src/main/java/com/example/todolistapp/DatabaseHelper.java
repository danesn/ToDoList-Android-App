package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TODOLIST_TABLE = "TODOLIST_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_DETAIL = "DETAIL";
    public static final String COLUMN_DATE = "DATE";


    public DatabaseHelper(Context context) {
        super(context, "todolist.db", null, 1);
    }


    // called first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODOLIST_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DETAIL + " TEXT, " + COLUMN_DATE + " TEXT);";
        db.execSQL(createTableStatement);
    }


    //called if the database version number change.
    //it prevents previous users apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





    // My Function
    public boolean deleteOne(ToDoListModel toDoListModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TODOLIST_TABLE + " WHERE " + COLUMN_ID + " = " + toDoListModel.getId() + ";";

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            db.close();
            return true;
        }
        else {
            db.close();
            return false;
        }
    }

    public boolean addOne(ToDoListModel toDoListModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, toDoListModel.getTitleToDoList());
        cv.put(COLUMN_DETAIL, toDoListModel.getDescToDoList());
        cv.put(COLUMN_DATE, toDoListModel.getDateToDoList());

        long insert = db.insert(TODOLIST_TABLE, null, cv);
        if (insert == -1) {
            db.close();
            return false;
        }
        else {
            db.close();
            return true;
        }
    }

    public List<ToDoListModel> getEveryone() {
        List<ToDoListModel> returnList = new ArrayList<>();

        // get data drom the database
        String queryString = "SELECT * FROM " + TODOLIST_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new todolist objects. put them into the return list
            do {
                int toDoListID = cursor.getInt(0);
                String titleToDoList = cursor.getString(1);
                String detailToDoList = cursor.getString(2);
                String dateToDoList = cursor.getString(3);

                ToDoListModel newToDoListModel = new ToDoListModel(toDoListID, titleToDoList, detailToDoList, dateToDoList);
                returnList.add(newToDoListModel);

            } while (cursor.moveToNext());
        }
        else {
            // if failure. do not add anything to the list.
        }

        // close both the cursor and the db when done.
        cursor.close();
        db.close();

        //return the list
        return returnList;
    }


}
