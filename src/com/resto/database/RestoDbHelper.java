package com.resto.database;

import android.content.Context;
import android.util.Log;
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

    public void clearData() {
        for(Restaurant restaurant: getRestaurantRuntimeDao().queryForAll())
            getRestaurantRuntimeDao().delete(restaurant);
        for(Menu menu: getMenuRuntimeDao().queryForAll())
            getMenuRuntimeDao().delete(menu);
    }

    public void createMenu(final Restaurant restaurant, final Collection<Menu> listMenu){
        getMenuRuntimeDao().callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                if(listMenu == null)
                    return null;
                for (Menu menu: listMenu) {
                    menu.setRestaurant(restaurant);
                    getMenuRuntimeDao().create(menu);
                }
                return null;
            }
        });
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return getRestaurantRuntimeDao().createIfNotExists(restaurant);
    }

}
