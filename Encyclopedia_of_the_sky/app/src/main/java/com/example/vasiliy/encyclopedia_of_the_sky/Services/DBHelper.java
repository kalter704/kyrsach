package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";

    final String TABLE_NAME_SKY_OBJECTS = "sky_objects";
    final String CREATE_TABLE_SKY_OBJECTS = "create table " + TABLE_NAME_SKY_OBJECTS +" ("
            + "id integer primary key autoincrement,"
            + "name text"
            + ");";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL(CREATE_TABLE_SKY_OBJECTS);

        ContentValues cv = new ContentValues();

        DatasForDB datasForDB = new DatasForDB();
        String[] sky_objects = datasForDB.sky_objects;

        for(int i = 0; i < sky_objects.length; ++i) {
            cv.clear();
            cv.put("name", sky_objects[i]);
            db.insert(TABLE_NAME_SKY_OBJECTS, null, cv);
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
