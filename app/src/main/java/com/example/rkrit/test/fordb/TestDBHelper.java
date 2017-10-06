package com.example.rkrit.test.fordb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rkrit.test.tables.Table;

/**
 * Created by rkrit on 06.10.17.
 */

public class TestDBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Test.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Table.Tag.TABLE_NAME+ "( " +
                    Table.Tag._ID + " INTEGER PRIMARY KEY , " +
                    Table.Tag.LATITUDE + " FLOAT(7,6), " +
                    Table.Tag.LONGTITUDE + " FLOAT(7,6))";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table.Tag.TABLE_NAME;

    public TestDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public TestDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("wow", SQL_CREATE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}
