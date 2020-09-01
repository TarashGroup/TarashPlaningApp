package com.example.myclock.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseAdapter {
    DatabaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Gson gson = new Gson();
    public DatabaseAdapter(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
    }
    //***********************************************maxid
    public int getMaxID(int id){
        String[] cul = {DatabaseHelper.KEY_ID,DatabaseHelper.KEY_VALUE};
        String[] i = {String.valueOf(id)};
        Cursor cursor = db.query(DatabaseHelper.MAXID_TABLE_NAME,cul,
                DatabaseHelper.KEY_ID + "=?", i ,null,null,null);
        int value = -1;
        while (cursor.moveToNext())
            value = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_VALUE));
        return value;
    }
    private void insertMaxId(int id , int value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_ID , id);
        contentValues.put(DatabaseHelper.KEY_VALUE, value);
        db.insert(DatabaseHelper.MAXID_TABLE_NAME , null , contentValues);
    }
    public void updateMaxID(int id,int value){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_VALUE,value);
        String []whereArgs = {String.valueOf(id)};
        int count = db.update(DatabaseHelper.MAXID_TABLE_NAME, contentValues,
                DatabaseHelper.KEY_ID + "=?",whereArgs);
        if (count == 0) insertMaxId(id,value);
    }
    //***********************************************maxid/

    //*******************************************lesson
    public HashMap<Integer,Lesson> getLessons(){
        HashMap<Integer,Lesson> lessons = new HashMap<>();
        String[] cul = {DatabaseHelper.KEY_ID,DatabaseHelper.KEY_VALUE};
        Cursor cursor = db.query(DatabaseHelper.ALL_LESSON_TABLE_NAME,cul,
                null, null ,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID));
            Lesson lesson = gson.fromJson(cursor.getString(
                    cursor.getColumnIndex(DatabaseHelper.KEY_VALUE)),Lesson.class);
            lessons.put(id,lesson);
        }
        return lessons;
    }
    public void addLesson(int id, Lesson lesson){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_ID , id);
        contentValues.put(DatabaseHelper.KEY_VALUE, gson.toJson(lesson));
        db.insert(DatabaseHelper.ALL_LESSON_TABLE_NAME , null , contentValues);
    }
    public void updateLesson(int id,Lesson lesson){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_VALUE,gson.toJson(lesson));
        String []whereArgs = {String.valueOf(id)};
        db.update(DatabaseHelper.ALL_LESSON_TABLE_NAME, contentValues,
                DatabaseHelper.KEY_ID + "=?",whereArgs);
    }
    public void removeLesson(int id ){
        String [] whereArgs = {String.valueOf(id)};
        db.delete(DatabaseHelper.ALL_LESSON_TABLE_NAME ,
                DatabaseHelper.KEY_ID + "=?" , whereArgs);
    }
    //*******************************************lesson/


    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "database.db";
        private static final String MAXID_TABLE_NAME = "maxes";
        private static final String ALL_LESSON_TABLE_NAME = "lessons";
        private static final String ALL_COURSES_TABLE_NAME = "courses";
        private static final int DATABASE_VERSION = 1;
        private static final String KEY_ID = "id";
        private static final String KEY_VALUE = "value";

        private static final String CREATE_MAXID_TABLE = "create table " + MAXID_TABLE_NAME +
                " (" + KEY_ID + " integer, " + KEY_VALUE + " integer)";

        private static final String CREATE_ALL_LESSON = "create table " + ALL_LESSON_TABLE_NAME +
                " ("  + KEY_ID + "integer, " + KEY_VALUE + " text)";

        private static final String CREATE_ALL_COURSES = "create table " + ALL_COURSES_TABLE_NAME +
                " (" + KEY_ID + "integer, " + KEY_VALUE + " text)";

        private Context context;
        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_MAXID_TABLE);
            sqLiteDatabase.execSQL(CREATE_ALL_LESSON);
            sqLiteDatabase.execSQL(CREATE_ALL_COURSES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
