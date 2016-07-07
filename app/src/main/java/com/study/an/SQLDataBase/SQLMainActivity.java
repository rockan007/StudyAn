package com.study.an.SQLDataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2016/7/1.
 */
public class SQLMainActivity extends AppCompatActivity {
    FeedReaderDbHelper mDbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper=FeedReaderDbHelper.getNewInstance(this);
        insertData();
        updateData();
    }
    //写 insert
    private void insertData(){
        ContentValues values=new ContentValues();
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_NAME,"AN");
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_AGE,18);
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_ENTRY_ID,007);
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_SEX,"男");
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_EDU,"小学");
        database.insert(FeedReaderDbHelper.FeedEntry.TABLE_NAME,null,values);
        database.close();
    }
    //改 update
    private void updateData(){
        ContentValues values=new ContentValues();
        values.put(FeedReaderDbHelper.FeedEntry.COLUMN_NAME_AGE,16);
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        String selection= FeedReaderDbHelper.FeedEntry.COLUMN_NAME_NAME+" LIKE ?";
        String[] selectionArgs={"AN"};
        database.update(FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        database.close();
    }
    //删除 delete
    private void deleteData(){
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        String selection= FeedReaderDbHelper.FeedEntry.COLUMN_NAME_NAME+" LIKE ?";
        String[] selectionArgs={"AN"};
        database.delete(FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                selection,
                selectionArgs);
        database.close();
    }
    //查询 query
    private void queryData(){
        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        Cursor cursor=database.query(false, FeedReaderDbHelper.FeedEntry.TABLE_NAME,null,null,null,null,null,null,null);

    }
}