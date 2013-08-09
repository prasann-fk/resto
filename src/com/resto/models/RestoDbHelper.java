package com.resto.models;

import android.content.Context;
import android.util.Base64;

import java.util.List;
import java.util.Collection;
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

    public List<MenuItem> getAllMenuItems(){
        return getMenuRuntimeDao().queryForAll();
    }

    public void clearData() {
        for(Restaurant restaurant: getRestaurantRuntimeDao().queryForAll())
            getRestaurantRuntimeDao().delete(restaurant);
        for(MenuItem menuItem : getMenuRuntimeDao().queryForAll())
            getMenuRuntimeDao().delete(menuItem);
    }

    public void createMenu(final Restaurant restaurant, final Collection<MenuItem> listMenuItem){
        getMenuRuntimeDao().callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                if(listMenuItem == null)
                    return null;
                for (MenuItem menuItem : listMenuItem) {
                    menuItem.setRestaurant(restaurant);
                    menuItem.image_bytes = Base64.decode(menuItem.getImage(), Base64.URL_SAFE);
                    getMenuRuntimeDao().create(menuItem);
                }
                return null;
            }
        });
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return getRestaurantRuntimeDao().createIfNotExists(restaurant);
    }

}
