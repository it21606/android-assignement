package com.example.user.test;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATA_DB";
    public static final int DATABASE_VERSION = 8;
    public static final String TABLE_NAME = "DATA_TABLE";
    public static final String DATA_ID = "_ID";
    public static final String DATA__FNAME = "FNAME";
    public static final String DATA__LNAME = "LNAME";
    public static final String DATA__AGE = "AGE";
    public static final String DATA__STAMP = "TIMESTAMP";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                DATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATA__FNAME + " TEXT, " +
                DATA__LNAME + " TEXT, " +
                DATA__AGE + " TEXT, " +
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
            values.put(DATA__FNAME, dataContract.getFname());
            values.put(DATA__LNAME, dataContract.getLname());
            values.put(DATA__AGE, dataContract.getAge());
            values.put(DATA__STAMP, dataContract.getTimestamp());
            db.insertOrThrow(TABLE_NAME, null, values);
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<Result> getSelected(String fname) {
        ArrayList<Result> dataContactsArray = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE_NAME, null, DATA__FNAME + "=?", new String[]{fname}, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) ;
                {
                    do {
                        Result result = new Result(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                        dataContactsArray.add(result);
                    } while (cursor.moveToNext());
                }
                return dataContactsArray;
            } else {
                return new ArrayList<>();
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}
