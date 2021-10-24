package com.example.puccheckingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDriverCred extends SQLiteOpenHelper {

    public DBDriverCred(Context context) {
        super(context, "Userdata1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table DriverCredentials(name TEXT, password TEXT, email TEXT, mobileno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists DriverCredentials");
    }

    public Boolean insertuserdata(String name, String password, String email, String mobileno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("mobileno", mobileno);
        long result=DB.insert("DriverCredentials", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata (String name, String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DriverCredentials where name = ? and password = ?", new String[]{name,pwd});
        return cursor;

    }

    public Cursor getpwd (String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DriverCredentials where password = ?", new String[]{pwd});
        return cursor;

    }

    public Cursor getmob (String mobno)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DriverCredentials where mobileno = ?", new String[]{mobno});
        return cursor;

    }

}

