package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myDB3";
    static final int VERSION = 3;

    final String LOG_TAG = "myLogs";

    //
    // Таблица sky_objects
    //
    public static final String TABLE_NAME_SKY_OBJECTS = "sky_objects";
    // TNSO = TABLE_NAME_SKY_OBJECTS
    // Названия колонок
    public static final String TITLE_COLUMN_TNSO = "title";
    public static final String INT_ID_COLUMN_TNSO = "int_id";
    public static final String IMG_COLUMN_TNSO = "image";

    private static final String CREATE_TABLE_SKY_OBJECTS = "create table " + TABLE_NAME_SKY_OBJECTS +" ("
                                                            + "id integer primary key autoincrement,"
                                                            + TITLE_COLUMN_TNSO + " text" + ','
                                                            + INT_ID_COLUMN_TNSO + " integer" + ','
                                                            + IMG_COLUMN_TNSO + " text"
                                                            + ");";
    //
    // Таблица constellation
    //
    public static final String TABLE_NAME_CONSTELLATION = "constellation";
    // TNC = TABLE_NAME_CONSTELLATION
    public static final String TITLE_COLUMN_TNC = "title";
    public static final String INT_ID_COLUMN_TNC = "int_id";
    public static final String IMG_COLUMN_TNC = "img";
    public static final String TEXT_COLUMN_TNC = "text";

    private static final String CREATE_TABLE_CONSTELLATION = "create table " + TABLE_NAME_CONSTELLATION +" ("
                                                            + "id integer primary key autoincrement,"
                                                            + TITLE_COLUMN_TNC + " text" + ','
                                                            + INT_ID_COLUMN_TNC + " integer" + ','
                                                            + IMG_COLUMN_TNC + " text" + ','
                                                            + TEXT_COLUMN_TNC + " text "
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
        onCreateTableSkyObjects(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpdata(db);
    }

    protected void onCreateTableSkyObjects(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        DatasForDB datasForDB = new DatasForDB();

        //
        // Создание и заполнение sky_objects
        //
        db.execSQL(CREATE_TABLE_SKY_OBJECTS);

        String[] sky_objects = datasForDB.sky_objects;
        int[] sky_object_int_id = datasForDB.sky_objects_id;
        String[] sky_object_img = datasForDB.sky_objetcs_img;

        for(int i = 0; i < sky_objects.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNSO, sky_objects[i]);
            cv.put(INT_ID_COLUMN_TNSO, sky_object_int_id[i]);
            cv.put(IMG_COLUMN_TNSO, sky_object_img[i]);
            db.insert(TABLE_NAME_SKY_OBJECTS, null, cv);
        }


        //
        // Создание и заполнение constellation
        //
        db.execSQL(CREATE_TABLE_CONSTELLATION);

        String[] constellation_name = datasForDB.constellation_name;
        int[] constellation_id = datasForDB.constellation_id;
        String[] constellation_img = datasForDB.constallation_img;
        String[] constallation_text = datasForDB.constellation_text;

        for(int i = 0; i < constellation_name.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNC, constellation_name[i]);
            cv.put(INT_ID_COLUMN_TNC, constellation_id[i]);
            cv.put(IMG_COLUMN_TNC, constellation_img[i]);
            cv.put(TEXT_COLUMN_TNC, constallation_text[i]);
            db.insert(TABLE_NAME_CONSTELLATION, null, cv);
        }
    }

    public void onUpdata(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("drop table " + TABLE_NAME_CONSTELLATION + ";");
            db.execSQL("drop table " + TABLE_NAME_SKY_OBJECTS + ";");
            onCreateTableSkyObjects(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
