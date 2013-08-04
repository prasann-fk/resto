package com.resto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RestoDbHelper extends DatabaseInitializer{
    SQLiteDatabase db = getWritableDatabase();

    public RestoDbHelper(Context context){ super(context); }

    public String getRestaurantName()
    {
        String[] projection = { RestoContract.Restaurant.COLUMN_NAME_NAME};
        Cursor c = db.query( RestoContract.Restaurant.TABLE_NAME, projection, null, null, null, null, null);
        int count = c.getCount();
        if(count <= 0){
            return null;
        }
        else{
            c.moveToFirst();
            return c.getString(c.getColumnIndexOrThrow(RestoContract.Restaurant.COLUMN_NAME_NAME));
        }
    }

    public void setRestaurantName(String name)
    {
        ContentValues values = new ContentValues();
        values.put(RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_ID, 1);
        values.put(RestoContract.Restaurant.COLUMN_NAME_NAME, name);
        db.insert( RestoContract.Restaurant.TABLE_NAME, "null", values);
    }

    public void deleteRestaurantName(){
        db.delete(RestoContract.Restaurant.TABLE_NAME, null, null);
    }

    public void initializeRestaurant(String data){
        try{
        db.beginTransaction();
        setRestaurantName(data);
        db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

}
