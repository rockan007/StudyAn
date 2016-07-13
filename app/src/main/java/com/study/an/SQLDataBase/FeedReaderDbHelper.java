package com.study.an.SQLDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by admin on 2016/7/1.
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {
    private static FeedReaderDbHelper newInstance;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE="INTEGER";

    public static FeedReaderDbHelper getNewInstance(Context context) {
        if (newInstance == null) {
            newInstance = new FeedReaderDbHelper(context);
        }
        return newInstance;
    }

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE"+FeedEntry.TABLE_NAME+"("+
                    FeedEntry._ID+"INTEGER PRIMARY KEY"+COMMA_SEP+
                    FeedEntry.COLUMN_NAME_ENTRY_ID+INT_TYPE+COMMA_SEP+
                    FeedEntry.COLUMN_NAME_NAME+TEXT_TYPE+COMMA_SEP+
                    FeedEntry.COLUMN_NAME_AGE+INT_TYPE+COMMA_SEP+
                    FeedEntry.COLUMN_NAME_SEX+TEXT_TYPE+COMMA_SEP+
                    FeedEntry.COLUMN_NAME_EDU+TEXT_TYPE+
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Inner class that defines the table contents
     */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "Id";
        public static final String COLUMN_NAME_NAME = "姓名";
        public static final String COLUMN_NAME_AGE = "年龄";
        public static final String COLUMN_NAME_SEX = "性别";
        public static final String COLUMN_NAME_EDU = "学历";
    }
}
