package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myDB2";
    static final int VERSION = 2;

    final String LOG_TAG = "myLogs";

    // Таблица sky_objects
    public static final String TABLE_NAME_SKY_OBJECTS = "sky_objects";
    public static final String TABLE_NAME_SKY_OBJECTS_OLD = "sky_objects_old";
    // TNSO = TABLE_NAME_SKY_OBJECTS
    // Названия колонок
    public static final String NAME_COLUMN_TNSO = "name";
    public static final String NAME_ID_COLUMN_TNSO = "name_id";
    public static final String INT_ID_COLUMN_TNSO = "int_id";
    public static final String IMG_COLUMN_TNSO = "image";

    final String CREATE_TABLE_SKY_OBJECTS_OLD = "create table " + TABLE_NAME_SKY_OBJECTS_OLD + " ("
            + "id integer primary key autoincrement,"
            + NAME_COLUMN_TNSO + " text" + ','
            + NAME_ID_COLUMN_TNSO + " text" + ','
            + IMG_COLUMN_TNSO + " text"
            + ");";

    final String CREATE_TABLE_SKY_OBJECTS = "create table " + TABLE_NAME_SKY_OBJECTS +" ("
            + "id integer primary key autoincrement,"
            + NAME_COLUMN_TNSO + " text" + ','
            + NAME_ID_COLUMN_TNSO + " text" + ','
            + INT_ID_COLUMN_TNSO + " integer" + ','
            + IMG_COLUMN_TNSO + " text"
            + ");";

    DatasForDB datasForDB;

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DB_NAME, null, VERSION);
        datasForDB = new DatasForDB();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL(CREATE_TABLE_SKY_OBJECTS);

        ContentValues cv = new ContentValues();

        DatasForDB datasForDB = new DatasForDB();
        String[] sky_objects = datasForDB.sky_objects;
        String[] sky_object_name_id = datasForDB.sky_objects_name_id;
        int[] sky_object_int_id = datasForDB.sky_objects_id;
        String[] sky_object_img = datasForDB.sky_objetcs_img;

        for(int i = 0; i < sky_objects.length; ++i) {
            cv.clear();
            cv.put(NAME_COLUMN_TNSO, sky_objects[i]);
            cv.put(NAME_ID_COLUMN_TNSO, sky_object_name_id[i]);
            cv.put(INT_ID_COLUMN_TNSO, sky_object_int_id[i]);
            cv.put(IMG_COLUMN_TNSO, sky_object_img[i]);
            db.insert(TABLE_NAME_SKY_OBJECTS, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Не работает !!!!!


        ContentValues cv = new ContentValues();

        int[] sky_object_int_id = datasForDB.sky_objects_id;

        db.beginTransaction();
        try{

            db.execSQL("create table tmp_table ("
                    + "id integer primary key,"
                    + "id_int integer"
                    + ");");

            for (int i = 0; i < sky_object_int_id.length; i++) {
                cv.clear();
                cv.put("id_int", sky_object_int_id[i]);
                db.insert("tmp_table", null, cv);
            }

            db.execSQL(CREATE_TABLE_SKY_OBJECTS_OLD);
            db.execSQL("insert into " + TABLE_NAME_SKY_OBJECTS_OLD +" select id, "
                    + NAME_COLUMN_TNSO + ','
                    + NAME_ID_COLUMN_TNSO + ','
                    + IMG_COLUMN_TNSO
                    + "from " + TABLE_NAME_SKY_OBJECTS + ';'
            );

            db.execSQL("drop table " + TABLE_NAME_SKY_OBJECTS + ";");

            db.execSQL(CREATE_TABLE_SKY_OBJECTS);

            db.execSQL("insert into " + TABLE_NAME_SKY_OBJECTS + "select id"
                    + NAME_COLUMN_TNSO + ','
                    + NAME_ID_COLUMN_TNSO + ','
                    + IMG_COLUMN_TNSO
                    + "from " + TABLE_NAME_SKY_OBJECTS_OLD + ';'
            );

            db.execSQL("insert into " + TABLE_NAME_SKY_OBJECTS + "select id_int from tmp_table");

            db.execSQL("drop table tmp_table;");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

}
