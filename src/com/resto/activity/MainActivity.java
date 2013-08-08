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

public class MainActivity extends BaseActivity {

    private static final String RESTAURANT_JSON_CACHE_KEY = "restaurant_json";
    private static final String MENU_JSON_CACHE_KEY = "menu_json";
    public static final String CLASS_NAME = MainActivity.class.toString();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Restaurant restaurant = getHelper().getRestaurant();
        if (restaurant == null) {
            setContentView(R.layout.main);
            findViewById(R.id.rest_id_submit).setOnClickListener(onClickListener);
        } else {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
    public void onClick(View v) {
        EditText restEditText = (EditText) findViewById(R.id.rest_id_input);
        EditText tableEditText = (EditText) findViewById(R.id.table_id_input);
        if(isInputInValid(restEditText, "Restaurant ID"))
            return;
        if(isInputInValid(tableEditText, "Table ID"))
            return;
        try{
            spiceManager.execute(new RestaurantRequest(restEditText.getText().toString(), tableEditText.getText().toString()),
                    RESTAURANT_JSON_CACHE_KEY, DurationInMillis.ALWAYS_EXPIRED, new RestaurantRequestListener());
        }catch(Exception e2){
            handleDatabaseInitializationException(e2);
        }
      }
    };

    public boolean isInputInValid(EditText e, String inputText){
        if(e.getText().toString().length() == 0){
            e.setError("Please input valid "+ inputText);
            return true;
        }
        return false;
    }

    public void handleDatabaseInitializationException(Exception e){
        Log.e(CLASS_NAME, "Error occurred while initializing database" + e.getLocalizedMessage());
        e.printStackTrace();
        getHelper().clearData();
        Toast.makeText( MainActivity.this, "failure", Toast.LENGTH_SHORT ).show();
    }

    public final class RestaurantRequestListener implements RequestListener< Restaurant > {

        public void onRequestFailure( SpiceException spiceException ) {
           Toast.makeText( MainActivity.this, "failure", Toast.LENGTH_SHORT ).show();
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
           Toast.makeText( MainActivity.this, "failure", Toast.LENGTH_SHORT ).show();
        }

        public void onRequestSuccess( Menus menus) {
            try{
                getHelper().createMenu(getHelper().getRestaurant(), menus.getMenus());
                Toast.makeText( MainActivity.this, "success", Toast.LENGTH_SHORT ).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }catch (Exception e){
                handleDatabaseInitializationException(e);
                return;
            }
        }
    }
}
