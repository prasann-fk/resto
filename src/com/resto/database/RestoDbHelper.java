package com.resto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestoDbHelper extends DatabaseInitializer {
    public static final String CLASS_NAME = RestoDbHelper.class.toString();
    SQLiteDatabase db = getWritableDatabase();

    public RestoDbHelper(Context context) {
        super(context);
    }

    public String getRestaurantName() {
        Cursor c = db.query(RestoContract.Restaurant.TABLE_NAME, null, null, null, null, null, null);
        int count = c.getCount();
        if (count <= 0) {
            return null;
        } else {
            c.moveToFirst();
            return c.getString(c.getColumnIndexOrThrow(RestoContract.Restaurant.COLUMN_NAME_NAME));
        }
    }

    public void deleteRestaurantName() {
        db.delete(RestoContract.Restaurant.TABLE_NAME, null, null);
        db.delete(RestoContract.Menu.TABLE_NAME, null, null);
    }

    public void initializeResto(String data) throws Exception {
        try {
            JSONObject json = new JSONObject(data);
            db.beginTransaction();
            initializeRestaurant(new JSONObject(json.getString("restaurant")));
            initializeMenu(new JSONArray(json.getString("menu")));
            db.setTransactionSuccessful();
        } catch (JSONException e) {
            Log.i(RestoDbHelper.class.getName(), "PRASANN: " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception("Error occurred while parsing data");
        } finally {
            db.endTransaction();
        }
    }

    public void initializeMenu(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ContentValues values = new ContentValues();
            values.put(RestoContract.Menu.COLUMN_NAME_ID, jsonObject.getString(RestoContract.Menu.COLUMN_NAME_ID));
            values.put(RestoContract.Menu.COLUMN_NAME_NAME, jsonObject.getString(RestoContract.Menu.COLUMN_NAME_NAME));
            values.put(RestoContract.Menu.COLUMN_NAME_DESCRIPTION, jsonObject.getString(RestoContract.Menu.COLUMN_NAME_DESCRIPTION));
            values.put(RestoContract.Menu.COLUMN_NAME_TAGS, jsonObject.getString(RestoContract.Menu.COLUMN_NAME_TAGS));
            db.insert(RestoContract.Menu.TABLE_NAME, "null", values);
        }
    }

    public void initializeRestaurant(JSONObject jsonObject) throws JSONException {
        ContentValues values = new ContentValues();
        values.put(RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_ID, jsonObject.getString(RestoContract.Restaurant.COLUMN_NAME_RESTAURANT_ID));
        values.put(RestoContract.Restaurant.COLUMN_NAME_NAME, jsonObject.getString(RestoContract.Restaurant.COLUMN_NAME_NAME));
        values.put(RestoContract.Restaurant.COLUMN_NAME_DESCRIPTION, jsonObject.getString(RestoContract.Restaurant.COLUMN_NAME_DESCRIPTION));
        values.put(RestoContract.Restaurant.COLUMN_NAME_TAGS, jsonObject.getString(RestoContract.Restaurant.COLUMN_NAME_TAGS));
        db.insert(RestoContract.Restaurant.TABLE_NAME, "null", values);
    }

}
