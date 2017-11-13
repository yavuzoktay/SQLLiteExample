package com.yavuzoktay.sqlliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yavuz on 12.11.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME ="people.db";
    public static final String TABLE_NAME="people_table";
    public static final String COL1="ID";
    public static final String COL2="NAME";
    public static final String COL3="EMAIL";
    public static final String COL4="TVSHOW";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE" +TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOCRIMENT, " +
                " NAME TEXT, EMAIL TEXT, TVSHOW TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String email,String tvShow){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,tvShow);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean updateData(String id,String name,String email,String tvShow){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,tvShow);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID= ?",new String[]{id});
    }

























}
