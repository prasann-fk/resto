package com.resto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.resto.R;
import com.resto.api.request.RestaurantRequest;
import com.resto.api.request.MenuRequest;
import com.resto.database.Menus;
import com.resto.database.Restaurant;
import android.util.Log;

public class InitialActivity extends BaseActivity {

    private static final String RESTAURANT_JSON_CACHE_KEY = "restaurant_json";
    private static final String MENU_JSON_CACHE_KEY = "menu_json";
    public static final String CLASS_NAME = InitialActivity.class.toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Restaurant restaurant = getHelper().getRestaurant();
        if (restaurant == null) {
            setContentView(R.layout.initial);
            findViewById(R.id.rest_id_submit).setOnClickListener(onClickListener);
        } else {
            startActivity(new Intent(InitialActivity.this, MainActivity.class));
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
    public void onClick(View v) {
        EditText e = (EditText) findViewById(R.id.rest_id_input);
        String restaurantId = e.getText().toString();
        if(restaurantId.length() == 0){
            e.setError("Please input Valid text");
            return;
        }
        try{
            spiceManager.execute(new RestaurantRequest(restaurantId, "aa"), RESTAURANT_JSON_CACHE_KEY, DurationInMillis.ALWAYS_EXPIRED, new RestaurantRequestListener());
            //startActivity(new Intent(InitialActivity.this, MainActivity.class));
        }catch(Exception e1){
            handleDatabaseInitializationException(e1);
        }
      }
    };

    public void handleDatabaseInitializationException(Exception e){
        Log.e(CLASS_NAME, "Error occurred while initializing database" + e.getLocalizedMessage());
        e.printStackTrace();
        getHelper().clearData();
        Toast.makeText( InitialActivity.this, "failure", Toast.LENGTH_SHORT ).show();
    }

    public final class RestaurantRequestListener implements RequestListener< Restaurant > {

        public void onRequestFailure( SpiceException spiceException ) {
           Toast.makeText( InitialActivity.this, "failure", Toast.LENGTH_SHORT ).show();
        }

        public void onRequestSuccess( Restaurant restaurant ) {
            try{
                restaurant = getHelper().createRestaurant(restaurant);
            }catch (Exception e){
                handleDatabaseInitializationException(e);
                return;
            }
            spiceManager.execute(new MenuRequest(restaurant), MENU_JSON_CACHE_KEY, DurationInMillis.ALWAYS_EXPIRED, new MenuRequestListener());
        }
    }

    public final class MenuRequestListener implements RequestListener<Menus> {
        public void onRequestFailure( SpiceException spiceException ) {
           Toast.makeText( InitialActivity.this, "failure", Toast.LENGTH_SHORT ).show();
        }

        public void onRequestSuccess( Menus menus) {
            try{
                getHelper().createMenu(getHelper().getRestaurant(), menus.getMenus());
                Toast.makeText( InitialActivity.this, "success", Toast.LENGTH_SHORT ).show();
                startActivity(new Intent(InitialActivity.this, MainActivity.class));
            }catch (Exception e){
                handleDatabaseInitializationException(e);
                return;
            }
        }
    }
}
