package com.resto.api.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.resto.database.Restaurant;
import com.resto.database.Menus;

public class MenuRequest extends SpringAndroidSpiceRequest<Menus> {

    private Restaurant restaurant;

    public MenuRequest(Restaurant restaurant) {
        super( Menus.class );
        this.restaurant = restaurant;
    }

    @Override
    public Menus loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://192.168.0.103:3000/restaurants/" + restaurant.id + "/menu", Menus.class );
    }
}