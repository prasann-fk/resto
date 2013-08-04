package com.resto.database;

import android.database.sqlite.*;
import android.content.Context;

import java.nio.DoubleBuffer;

public class DatabaseInitializer extends SQLiteOpenHelper {

    private static final String SQL_CREATE_RESTAURANT =
        "CREATE TABLE IF NOT EXISTS " + RestoContract.Restaurant.TABLE_NAME + " (" +
        RestoContract.Restaurant._ID + " INTEGER PRIMARY KEY," +
        RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_ID + " INTEGER," +
        RestoContract.Restaurant.COLUMN_NAME_NAME + " VARCHAR(50)," +
        RestoContract.Restaurant.COLUMN_NAME_DESCRIPTION + " TEXT," +
        RestoContract.Restaurant.COLUMN_NAME_TAGS + " TEXT" +
        " )";

    private static final String SQL_CREATE_MENU =
        "CREATE TABLE IF NOT EXISTS " + RestoContract.Menu.TABLE_NAME + " (" +
        RestoContract.Menu._ID + " INTEGER PRIMARY KEY," +
        RestoContract.Menu.COLUMN_NAME_ID + " INTEGER," +
        RestoContract.Menu.COLUMN_NAME_NAME + " VARCHAR(50)," +
        RestoContract.Menu.COLUMN_NAME_DESCRIPTION + " TEXT," +
        RestoContract.Menu.COLUMN_NAME_TAGS + " TEXT" +
        " )";

    private static final String SQL_DELETE_RESTAURANT =
        "DROP TABLE IF EXISTS " + RestoContract.Restaurant.TABLE_NAME;

    private static final String SQL_DELETE_MENU =
        "DROP TABLE IF EXISTS " + RestoContract.Menu.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Resto.db";

    public DatabaseInitializer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RESTAURANT);
        db.execSQL(SQL_CREATE_MENU);
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_RESTAURANT);
        db.execSQL(SQL_DELETE_MENU);
    }

    //implement these correctly when you want to change schema based on versions
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDelete(db);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
