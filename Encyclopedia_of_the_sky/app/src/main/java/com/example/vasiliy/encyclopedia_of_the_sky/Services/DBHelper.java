package com.example.vasiliy.encyclopedia_of_the_sky.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myDB3";
    static final int VERSION = 3;

    DatasForDB datasForDB;

    final String LOG_TAG = "myLogs";

    //
    // Таблица themes
    //
    public static final String TABLE_NAME_THEMES = "themes";
    // TNT = TABLE_NAME_THEMES
    public static final String TITLE_COLUMN_TNT = "title";
    public static final String INT_ID_COLUMN_TNT = "int_id";

    private static final String CREATE_TABLE_THEMES = "create table " + TABLE_NAME_THEMES + " ("
                                                    + "id integer primary key autoincrement,"
                                                    + TITLE_COLUMN_TNT + " text" + ','
                                                    + INT_ID_COLUMN_TNT + " integer"
                                                    + ");";

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
    public static final String TEXT_WHERE_FROM_COLUMN_TNC = "text_where_from";
    public static final String TEXT_INF_COLUMN_TNC = "text_inf";

    private static final String CREATE_TABLE_CONSTELLATION = "create table " + TABLE_NAME_CONSTELLATION +" ("
                                                            + "id integer primary key autoincrement,"
                                                            + TITLE_COLUMN_TNC + " text" + ','
                                                            + INT_ID_COLUMN_TNC + " integer" + ','
                                                            + IMG_COLUMN_TNC + " text" + ','
                                                            + TEXT_WHERE_FROM_COLUMN_TNC + " text" + ','
                                                            + TEXT_INF_COLUMN_TNC + " text"
                                                            + ");";


    //
    //  Таблица planets
    //
    public static final String TABLE_NAME_PLANET = "planet";
    // TNP = TABLE_NAME_PLANET
    public static final String TITLE_COLUMN_TNP = "title";
    public static final String INT_ID_COLUMN_TNP = "int_id";
    public static final String IMG_COLUMN_TNP = "img";
    public static final String MASS_COLUMN_TNP = "mass";
    public static final String RADIUS_COLUMN_TNP = "radius";
    public static final String DAY_COLUMN_TNP = "day";
    public static final String YEAR_COLUMN_TNP = "year";
    public static final String RADIUS_SUN_COLUMN_TNP = "radius_sun";
    public static final String INFO_COLUMN_TNP = "info";

    private static final String CREATE_TABLE_PLANET = "create table " + TABLE_NAME_PLANET +" ("
                                                    + "id integer primary key autoincrement,"
                                                    + TITLE_COLUMN_TNP + " text" + ','
                                                    + INT_ID_COLUMN_TNP + " integer" + ','
                                                    + IMG_COLUMN_TNP + " text" + ','
                                                    + MASS_COLUMN_TNP + " text" + ','
                                                    + RADIUS_COLUMN_TNP + " text" + ','
                                                    + DAY_COLUMN_TNP + " text" + ','
                                                    + YEAR_COLUMN_TNP + " text" + ','
                                                    + RADIUS_SUN_COLUMN_TNP + " text" + ','
                                                    + INFO_COLUMN_TNP + " text"
                                                    + ");";

    //
    // VIEW_OBJECT
    //
    public static final String TABLE_NAME_VIEW_OBJ = "view_obj";
    // TNVO = TABLE_NAME_VIEW_OBJ
    public static final String TITLE_COLUMN_TNVO = "title";
    public static final String TEXT_COLUMN_TNVO = "text";

    private static final String CREATE_TABLE_VIEW_OBJECT = "create table " + TABLE_NAME_VIEW_OBJ + " ("
                                                        + "id integer primary key autoincrement,"
                                                        + TITLE_COLUMN_TNVO + " text" + ','
                                                        + TEXT_COLUMN_TNVO + " text"
                                                        + ");";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        datasForDB = new DatasForDB();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        onCreateTableSkyObjects(db);
        onCreateTableConstelltions(db);
        onCreateTablePlanets(db);
        onCreateTableThemes(db);
        onCreateViewObject(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpdata(db);
    }

    public void onUpdata(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("drop table if exists " + TABLE_NAME_CONSTELLATION + ";");
            db.execSQL("drop table if exists " + TABLE_NAME_SKY_OBJECTS + ";");
            db.execSQL("drop table if exists " + TABLE_NAME_PLANET + ";");
            db.execSQL("drop table if exists " + TABLE_NAME_THEMES + ";");
            db.execSQL("drop table if exists " + TABLE_NAME_VIEW_OBJ + ";");
            onCreate(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //
    // Создание и заполнение themes
    //
    private void onCreateTableThemes(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_THEMES);

        String[] themes = datasForDB.themes;
        int[] intId= datasForDB.intId;

        for(int i = 0; i < themes.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNT, themes[i]);
            cv.put(INT_ID_COLUMN_TNT, intId[i]);
            db.insert(TABLE_NAME_THEMES, null, cv);
        }
    }


    //
    // Создание и заполнение sky_objects
    //
    private void onCreateTableSkyObjects(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(CREATE_TABLE_SKY_OBJECTS);

        String[] skyObjects = datasForDB.skyObjects;
        int[] skyObjectIntId = datasForDB.skyObjectsId;
        String[] skyObjectImg = datasForDB.skyObjetcsImg;

        for(int i = 0; i < skyObjects.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNSO, skyObjects[i]);
            cv.put(INT_ID_COLUMN_TNSO, skyObjectIntId[i]);
            cv.put(IMG_COLUMN_TNSO, skyObjectImg[i]);
            db.insert(TABLE_NAME_SKY_OBJECTS, null, cv);
        }
    }

    //
    // Создание и заполнение constellation
    //
    private void onCreateTableConstelltions(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        db.execSQL(CREATE_TABLE_CONSTELLATION);

        String[] constellationTitle = datasForDB.constellationName;
        int[] constellationId = datasForDB.constelletionId;
        String[] constellationImg = datasForDB.constellationImg;
        String[] constellationTextWhereFrom = datasForDB.constellationWhereFrom;
        String[] constellationTextInf = datasForDB.constellationTextInf;

        for(int i = 0; i < constellationTitle.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNC, constellationTitle[i]);
            cv.put(INT_ID_COLUMN_TNC, constellationId[i]);
            cv.put(IMG_COLUMN_TNC, constellationImg[i]);
            cv.put(TEXT_WHERE_FROM_COLUMN_TNC, constellationTextWhereFrom[i]);
            cv.put(TEXT_INF_COLUMN_TNC, constellationTextInf[i]);
            db.insert(TABLE_NAME_CONSTELLATION, null, cv);
        }
    }

    //
    // Планеты
    //
    private void onCreateTablePlanets(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        db.execSQL(CREATE_TABLE_PLANET);

        String[] planetTitle = datasForDB.planetName;
        int[] planetId = datasForDB.planetId;
        String[] planetImg = datasForDB.planetImg;
        String[] planetMass = datasForDB.planetMass;
        String[] planetRadius = datasForDB.planetRadius;
        String[] planetDay = datasForDB.planetDay;
        String[] planetYear = datasForDB.planetYear;
        String[] planetRadiusSun = datasForDB.planetRadiusSun;
        String[] planetInfo = datasForDB.planetInfo;

        for(int i = 0; i < planetTitle.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNP, planetTitle[i]);
            cv.put(INT_ID_COLUMN_TNP, planetId[i]);
            cv.put(IMG_COLUMN_TNP, planetImg[i]);
            cv.put(MASS_COLUMN_TNP, planetMass[i]);
            cv.put(RADIUS_COLUMN_TNP, planetRadius[i]);
            cv.put(DAY_COLUMN_TNP, planetDay[i]);
            cv.put(YEAR_COLUMN_TNP, planetYear[i]);
            cv.put(RADIUS_SUN_COLUMN_TNP, planetRadiusSun[i]);
            cv.put(INFO_COLUMN_TNP, planetInfo[i]);
            db.insert(TABLE_NAME_PLANET, null, cv);
        }
    }


    //
    // VIEW_OBJECT
    //
    private void onCreateViewObject(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VIEW_OBJECT);

        ContentValues cv = new ContentValues();

        String[] elemSky = datasForDB.elemSky;
        String[] elemText = datasForDB.elemText;
        for(int i = 0; i < elemSky.length; ++i) {
            cv.clear();
            cv.put(TITLE_COLUMN_TNVO, elemSky[i]);
            cv.put(TEXT_COLUMN_TNVO, elemText[i]);
            db.insert(TABLE_NAME_VIEW_OBJ, null, cv);
        }
    }


}
