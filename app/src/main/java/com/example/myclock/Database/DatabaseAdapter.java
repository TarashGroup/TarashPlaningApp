package com.example.myclock.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DatabaseAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    Gson gson = new Gson();
    public void savePlan(ArrayList<Plan> plans){
        String plansToString = gson.toJson(plans);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "max.db";
        private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
