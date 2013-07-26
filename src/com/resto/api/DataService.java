package com.resto.api;

import android.content.Context;
import com.resto.database.RestoDbHelper;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class DataService {

    RestoDbHelper dbHelper;

    public DataService(Context context){
        dbHelper = new RestoDbHelper(context);
    }

    public void initializeDatabase(int restaurantId) throws Exception{
        try {
        HashMap<Boolean, String> hm = new LongIO().execute("restaurants/" + restaurantId).get();
        Iterator iterator = hm.entrySet().iterator();
        Map.Entry mEntry = (Map.Entry) iterator.next();
		Boolean success = (Boolean) mEntry.getKey();
        String data = (String) mEntry.getValue();
        if(success)
            dbHelper.setRestaurantName(data);
         else
            throw new Exception(data);
        } catch (InterruptedException e1) {

        } catch (ExecutionException e2) {

        }
    }
}
