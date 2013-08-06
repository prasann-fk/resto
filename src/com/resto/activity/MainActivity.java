package com.resto.activity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.resto.R;
import com.resto.database.Restaurant;

public class MainActivity extends BaseActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Restaurant restaurant = getHelper().getRestaurant();
        setContentView(R.layout.main);
        TextView v = (TextView)findViewById(R.id.restaurant_name);
        v.setText(restaurant.name);
        Button btnClickMenu =(Button)findViewById(R.id.ButtonMenu);
        btnClickMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });

        //TODO: remove this line: it is there because i am testing initial request
        getHelper().clearData();
    }
}
