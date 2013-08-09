package com.resto.api.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.resto.models.Menu;
import com.resto.models.Restaurant;

public class MenuRequest extends SpringAndroidSpiceRequest<Menu> {

    private Restaurant restaurant;

    public MenuRequest(Restaurant restaurant) {
        super( Menu.class );
        this.restaurant = restaurant;
    }

    @Override
    public Menu loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://192.168.0.102:3000/restaurants/" + restaurant.id + "/menu", Menu.class );
    }
}