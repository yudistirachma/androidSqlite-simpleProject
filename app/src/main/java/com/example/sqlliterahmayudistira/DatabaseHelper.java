package com.example.sqlliterahmayudistira;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static  final  String DATABASE_NAME = "Student.db";
    public  static  final  String TABLE_NAME = "t_mahasiswa";
    public  static  final  String COL_1 = "ID";
    public  static  final  String COL_2 = "NAMA";
    public  static  final  String COL_3 = "ALAMAT";
    public  static  final  String COL_4 = "JENIS_KELAMIN";
    public  static  final  String COL_5 = "AGAMA";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, ALAMAT TEXT,JENIS_KELAMIN TEXT, AGAMA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nama, String alamat, String jenis_kelamin, String agama ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, alamat);
        contentValues.put(COL_4, jenis_kelamin);
        contentValues.put(COL_5, agama);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null );
        return  res;
    }

    public  boolean updateData(String id,String nama, String alamat, String jenis_kelamin, String agama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, alamat);
        contentValues.put(COL_4, jenis_kelamin);
        contentValues.put(COL_5, agama);
        db.update(TABLE_NAME, contentValues ,"id = ?",new String[] {id});
        return  true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[] {id});
    }

}
