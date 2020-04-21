package com.example.courxera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "courses.db";
    public static final String  TABLE_NAME = "student_courses";
    public static final String  COL_1 = "ID";
    public static final String  COL_2 = "NAME";
    public static final String  COL_3 = "SCHEDULE";
    public static final String  COL_4 = "CREDITS";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME,null, 1);
        /* SQLiteDatabase db = this.getWritableDatabase(); */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SCHEDULE TEXT, CREDITS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String courseName, String schedule, String credits ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,courseName) ;
        contentValues.put(COL_3,schedule) ;
        contentValues.put(COL_4,credits) ;
        long result = db.insert(TABLE_NAME,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String name, String schedule, String credits){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id) ;
        contentValues.put(COL_2,name) ;
        contentValues.put(COL_3,schedule) ;
        contentValues.put(COL_4,credits) ;
        db.update( TABLE_NAME, contentValues, " id = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
