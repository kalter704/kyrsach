package com.example.vasiliy.encyclopedia_of_the_sky.Services;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vasiliy.encyclopedia_of_the_sky.Services.DataBaseObjects.SkyObject;

import java.util.ArrayList;
import java.util.List;

public class SkyDataBase {

    private final String LOG_TAG_DB = "myDataBase";

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

    public List<SkyObject> getListSkyObjects() {
        List<SkyObject> list = new ArrayList<>();

        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor c;

        c = db.query(dbh.TABLE_NAME_SKY_OBJECTS, null, null, null, null, null, null);

        logCursor(c);

        int nameColIndex = c.getColumnIndex(dbh.NAME_COLUMN_TNSO);
        int idColIndex = c.getColumnIndex(dbh.ID_COLUMN_TNSO);
        int imgColIndex = c.getColumnIndex(dbh.IMG_COLUMN_TNSO);

        if(c.moveToFirst()) {
            do {
                SkyObject temp = new SkyObject(c.getString(nameColIndex), c.getString(idColIndex), c.getString(imgColIndex));
                list.add(temp);
            } while(c.moveToNext());
        }

        c.close();
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
