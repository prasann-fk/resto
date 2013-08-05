package com.resto.database;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.misc.TransactionManager;

import java.util.List;
import java.util.concurrent.Callable;


public class RestoDbHelper extends DatabaseHelper{
    public static final String CLASS_NAME = RestoDbHelper.class.toString();

    public RestoDbHelper(Context context) {
        super(context);
    }

    public Restaurant getRestaurant() {
        List<Restaurant> restaurants = getRestaurantRuntimeDao().queryForAll();
        return restaurants.isEmpty()? null : restaurants.get(0);
    }

    public void clearData() {
        for(Restaurant restaurant: getRestaurantRuntimeDao().queryForAll())
            getRestaurantRuntimeDao().delete(restaurant);
        for(Menu menu: getMenuRuntimeDao().queryForAll())
            getMenuRuntimeDao().delete(menu);
    }

    public void initializeResto(String data) throws Exception {
        try {
            final JSONObject json = new JSONObject(data);
            TransactionManager.callInTransaction(connectionSource,
                    new Callable<Void>() {
                        public Void call() throws Exception {
                            initializeRestaurant(new JSONObject(json.getString("restaurant")));
                            initializeMenu(new JSONArray(json.getString("menu")));
                            Log.i(CLASS_NAME, "Restaurants: " + getRestaurantRuntimeDao().queryForAll().toString());
                            Log.i(CLASS_NAME, "Menus: " + getMenuRuntimeDao().queryForAll().toString());
                            return null;
                        }
                    });
        } catch (JSONException e) {
            Log.e(CLASS_NAME, "PRASANN: " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception("Error occurred while parsing data");
        }
    }

    public void initializeMenu(final JSONArray jsonArray) throws JSONException {
        getMenuRuntimeDao().callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                getMenuRuntimeDao().create(new Menu(jsonObject.getString("id"), jsonObject.getString("name"),
                        jsonObject.getString("description"), jsonObject.getString("tags")));
                }
                return null;
            }
        });
    }

    public void initializeRestaurant(JSONObject jsonObject) throws JSONException {
        getRestaurantRuntimeDao().create(new Restaurant(jsonObject.getString("id"), jsonObject.getString("name"),
                        jsonObject.getString("description"), jsonObject.getString("tags")));
    }

}
