package com.project.sdeliveryapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DataBHelper extends SQLiteOpenHelper {
    public static final String database_Name = "UserDetails.db";
    private static final String COLUMN_ID = "id";

    public DataBHelper(@Nullable Context context) {
        super(context, "UserDetails.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userdetailstable(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, item TEXT, phone TEXT, latitude TEXT, longtitude TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists userdetailstable");
        onCreate(db);
    }

    public Boolean insertdatatable(String name, String item, String phone,String latitude, String longtitude) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("item", item);
        contentValues.put("phone", phone);
        contentValues.put("latitude", latitude);
        contentValues.put("longtitude", longtitude);

        long result = DB.insert("userdetailstable", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getLastRow(){
        SQLiteDatabase db = this.getWritableDatabase();
        String tableName =  "userdetailstable";
        String query1 = "SELECT * FROM " + tableName + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor result = db.rawQuery(query1,null);
        if(result != null){
            result.moveToFirst();
        }
        db.close();
        return result;
    }
    String tname = "userdetailstable";
    public void resetAutoIncrementCounter(SQLiteDatabase db, String tname) {

        String sql = "DELETE FROM SQLITE_SEQUENCE WHERE name=?";
        db.execSQL(sql, new String[]{tname});
    }

}
