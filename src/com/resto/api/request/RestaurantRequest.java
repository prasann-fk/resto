package com.resto.api.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.resto.database.Restaurant;

public class RestaurantRequest extends SpringAndroidSpiceRequest<Restaurant> {

    private String restaurant_id;
    private String table_id;

    public RestaurantRequest(String restaurant_id, String table_id) {
        super( Restaurant.class );
        this.restaurant_id = restaurant_id;
        this.table_id = table_id;
    }

    @Override
    public Restaurant loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://192.168.0.102:3000/restaurants/" + restaurant_id, Restaurant.class );
    }
}