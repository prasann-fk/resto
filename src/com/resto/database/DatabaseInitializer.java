package com.resto.database;

import android.database.sqlite.*;
import android.content.Context;

public class DatabaseInitializer extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE IF NOT EXISTS " + RestoContract.Restaurant.TABLE_NAME + " (" +
        RestoContract.Restaurant._ID + " INTEGER PRIMARY KEY," +
        RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_ID + " INTEGER," +
        RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_NAME + " VARCHAR(50)" +
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + RestoContract.Restaurant.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Resto.db";

    public DatabaseInitializer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    //implement these correctly when you want to change schema based on versions
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
