package com.project.sdeliveryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_Name = "UserServicedetailstablle.db";
    private static final String COL_ID = "id";
    public DatabaseHelper(@Nullable Context context) {

        super(context , "UserServicedetailstablle.db",null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userService(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT,problem TEXT,phone TEXT, longtitude TEXT , latitude TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists userService");
        onCreate(db);
    }



    public Boolean insertservicetable(String name,String type, String problem, String phone,String latitude, String longtitude) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("problem", problem);
        contentValues.put("phone", phone);
        contentValues.put("latitude", latitude);
        contentValues.put("longtitude", longtitude);

        long result = DB.insert("userService", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getLastTableRow(){
        SQLiteDatabase DB = this.getWritableDatabase();
        String tableName =  "userService";
        String query = "SELECT * FROM " + tableName + " ORDER BY " + COL_ID + " DESC LIMIT 1";
        Cursor cursor = DB.rawQuery(query,null);
        return cursor;
    }



}
