package com.example.user.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.sql.Timestamp;

public class LocContentProvider extends ContentProvider {

    private DBHelper DbHelper;
    static public UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        DbHelper = new DBHelper(getContext());
        //create URI inserting a location
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.user.test", "save", 1);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == 1) {
            //add timestamp to contentValues and insert it into database
            SQLiteDatabase sqLiteDatabase = DbHelper.getWritableDatabase();
            values.put(DbHelper.DATA__STAMP, (new Timestamp(System.currentTimeMillis())).toString());
            try {
                sqLiteDatabase.insertOrThrow(DbHelper.TABLE_NAME, null, values);
            } catch (SQLException e) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}