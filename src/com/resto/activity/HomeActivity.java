package com.resto.activity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.resto.R;
import com.resto.models.Restaurant;

public class HomeActivity extends BaseActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Restaurant restaurant = getHelper().getRestaurant();
        setContentView(R.layout.home);
        TextView v = (TextView)findViewById(R.id.restaurant_name);
        TextView v1 = (TextView)findViewById(R.id.restaurant_description);
        v.setText(restaurant.name);
        v1.setText(restaurant.description);
        Button btnClickMenu =(Button)findViewById(R.id.ButtonMenu);
        btnClickMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MenuActivity.class));
            }
        });
    }
}
