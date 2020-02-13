package com.example.user.test;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LOCATION_DB";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "DATA_TABLE";
    public static final String DATA_ID = "_ID";
    public static final String DATA__LONG = "LONG";
    public static final String DATA__LANG = "LANG";
    public static final String DATA__STAMP = "TIMESTAMP";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATA__LONG + " TEXT, " +
                DATA__LANG + " TEXT, " +
                DATA__STAMP + " TEXT " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(DataContract dataContract) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DATA__LONG, dataContract.getFname());
            values.put(DATA__LANG, dataContract.getLname());
            values.put(DATA__STAMP, dataContract.getTimestamp());
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (SQLException e) {
            throw e;
        }
    }
}
