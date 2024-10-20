package com.example.hackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "STUDENT_RECORD";
    private static final String TABLE_NAME = "STUDENT_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "EMAIL;";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String email){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2,name);
        values.put(COL_3,email);

        long var = db.insert(TABLE_NAME, null, values);
        if (var == -1){

            return false;
        }else{

            return true;
        }
    }
}
