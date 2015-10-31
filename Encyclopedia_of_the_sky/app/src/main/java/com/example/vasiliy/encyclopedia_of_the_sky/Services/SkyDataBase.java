package com.example.vasiliy.encyclopedia_of_the_sky.Services;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.ConstellationObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.MyObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.PlanetObject;
import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SkyDataBase {

    private final String LOG_TAG_DB = "DataBaseQuery";

    private final Context context;
    private DBHelper dbh;
    private SQLiteDatabase db;

    public SkyDataBase(Context context) {
        this.context = context;
    }

    public void open() {
        dbh = new DBHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void close() {
        if(dbh != null) {
            dbh.close();
        }
    }

    public void onUpdataDB() {
        this.open();
        dbh.onUpdata(db);
        this.close();
    }

    public List<SkyObject> getListSkyObjects() {
        this.open();
        List<SkyObject> list = new ArrayList<>();

        Cursor c;

        c = db.query(dbh.TABLE_NAME_SKY_OBJECTS, null, null, null, null, null, null);

        logCursor(c);

        int nameColIndex = c.getColumnIndex(dbh.TITLE_COLUMN_TNSO);
        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNSO);
        int imgColIndex = c.getColumnIndex(dbh.IMG_COLUMN_TNSO);

        if(c.moveToFirst()) {
            do {
                SkyObject temp = new SkyObject(c.getString(nameColIndex), c.getInt(intIdColIndex), c.getString(imgColIndex));
                list.add(temp);
            } while(c.moveToNext());
        }

        c.close();
        this.close();
        return list;
    }

    public List<SkyObject> getListConstellationSimply() {
        this.open();
        List<SkyObject> list = new ArrayList<>();

        Cursor c;

        String[] columns = {dbh.TITLE_COLUMN_TNC, dbh.INT_ID_COLUMN_TNC};
        c = db.query(dbh.TABLE_NAME_CONSTELLATION, columns, null, null, null, null, dbh.TITLE_COLUMN_TNC);

        logCursor(c);

        int nameColIndex = c.getColumnIndex(dbh.TITLE_COLUMN_TNC);
        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNC);

        if(c.moveToFirst()) {
            do {
                ConstellationObject temp = new ConstellationObject(c.getString(nameColIndex), c.getInt(intIdColIndex));
                list.add(temp);
            } while(c.moveToNext());
        }

        this.close();
        return list;
    }

    public ConstellationObject getConstellationById(int id) {
        this.open();
        Cursor c;

        String[] columns = {
                dbh.TITLE_COLUMN_TNC,
                dbh.INT_ID_COLUMN_TNC,
                dbh.IMG_COLUMN_TNC,
                dbh.TEXT_WHERE_FROM_COLUMN_TNC,
                dbh.TEXT_INF_COLUMN_TNC
        };

        String where = dbh.INT_ID_COLUMN_TNC + " = ?";

        String[] arg = { String.valueOf(id) };

        c = db.query(dbh.TABLE_NAME_CONSTELLATION, columns, where, arg, null, null, null);

        logCursor(c);

        int titleColIndex = c.getColumnIndex(dbh.TITLE_COLUMN_TNC);
        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNC);
        int imgColIndex = c.getColumnIndex(dbh.IMG_COLUMN_TNC);
        int textInfColIndex = c.getColumnIndex(dbh.TEXT_INF_COLUMN_TNC);
        int textWhereColIndex = c.getColumnIndex(dbh.TEXT_WHERE_FROM_COLUMN_TNC);

        c.moveToFirst();
        ConstellationObject constellationObject = new ConstellationObject(
                c.getString(titleColIndex),
                c.getInt(intIdColIndex),
                c.getString(imgColIndex),
                c.getString(textInfColIndex),
                c.getString(textWhereColIndex)
        );
        this.close();
        return constellationObject;
    }

    public List<Integer> getConstellationIdList() {
        List<Integer> list = new ArrayList<>();
        this.open();
        Cursor c;

        String[] columns = { dbh.INT_ID_COLUMN_TNC };

        c = db.query(dbh.TABLE_NAME_CONSTELLATION, columns, null, null, null, null, dbh.TITLE_COLUMN_TNC);

        logCursor(c);

        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNC);

        if(c.moveToFirst()) {
            do {
                list.add(c.getInt(intIdColIndex));
            } while(c.moveToNext());
        }
        this.close();
        return list;
    }

    public List<SkyObject> getListPlanetsSimply() {
        this.open();
        List<SkyObject> list = new ArrayList<>();

        Cursor c;

        String[] columns = {dbh.TITLE_COLUMN_TNP, dbh.INT_ID_COLUMN_TNP};
        c = db.query(dbh.TABLE_NAME_PLANET, columns, null, null, null, null, dbh.TITLE_COLUMN_TNP);

        logCursor(c);

        int nameColIndex = c.getColumnIndex(dbh.TITLE_COLUMN_TNP);
        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNP);

        if(c.moveToFirst()) {
            do {
                PlanetObject temp = new PlanetObject(c.getString(nameColIndex), c.getInt(intIdColIndex));
                list.add(temp);
            } while(c.moveToNext());
        }

        this.close();
        return list;
    }

    public PlanetObject getPlanetById(int id) {
        this.open();
        Cursor c;

        String[] columns = {
                dbh.TITLE_COLUMN_TNP,
                dbh.INT_ID_COLUMN_TNP,
                dbh.IMG_COLUMN_TNP,
                dbh.MASS_COLUMN_TNP,
                dbh.RADIUS_COLUMN_TNP,
                dbh.DAY_COLUMN_TNP,
                dbh.YEAR_COLUMN_TNP,
                dbh.RADIUS_SUN_COLUMN_TNP,
                dbh.INFO_COLUMN_TNP
        };

        String where = dbh.INT_ID_COLUMN_TNP + " = ?";

        String[] arg = { String.valueOf(id) };

        c = db.query(dbh.TABLE_NAME_PLANET, columns, where, arg, null, null, null);

        logCursor(c);

        c.moveToFirst();
        PlanetObject planetObject = new PlanetObject(
                c.getString(c.getColumnIndex(dbh.TITLE_COLUMN_TNP)),
                c.getInt(c.getColumnIndex(dbh.INT_ID_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.IMG_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.MASS_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.RADIUS_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.DAY_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.YEAR_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.RADIUS_SUN_COLUMN_TNP)),
                c.getString(c.getColumnIndex(dbh.INFO_COLUMN_TNP))
        );
        this.close();
        return planetObject;
    }

    public List<Integer> getPlanetIdList() {
        List<Integer> list = new ArrayList<>();
        this.open();
        Cursor c;

        String[] columns = { dbh.INT_ID_COLUMN_TNP };

        c = db.query(dbh.TABLE_NAME_PLANET, columns, null, null, null, null, dbh.TITLE_COLUMN_TNP);

        logCursor(c);

        int intIdColIndex = c.getColumnIndex(dbh.INT_ID_COLUMN_TNP);

        if(c.moveToFirst()) {
            do {
                list.add(c.getInt(intIdColIndex));
            } while(c.moveToNext());
        }
        this.close();
        return list;
    }

    public List<MyObject> getThemes() {
        this.open();
        List<MyObject> list = new ArrayList<>();

        Cursor c;

        c = db.query(dbh.TABLE_NAME_THEMES, null, null, null, null, null, null);

        logCursor(c);

        if(c.moveToFirst()) {
            do {
                MyObject temp = new MyObject(c.getString(c.getColumnIndex(dbh.TITLE_COLUMN_TNT)), c.getInt(c.getColumnIndex(dbh.INT_ID_COLUMN_TNT)));
                list.add(temp);
            } while(c.moveToNext());
        }

        c.close();
        this.close();
        return list;
    }

    void logCursor(Cursor c) {
        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = " + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG_DB, str);
                } while (c.moveToNext());
            }
        } else
            Log.d(LOG_TAG_DB, "Cursor is null");
    }
}
