package com.study.an.SQLDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2016/7/1.
 */
//public class FeedReaderDbHelper extends SQLiteOpenHelper {
//    // If you change the database schema, you must increment the database version.
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "FeedReader.db";
//
//    public FeedReaderDbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_CREATE_ENTRIES);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);
//        onCreate(db);
//    }
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }
//}
