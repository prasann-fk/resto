package com.resto.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.resto.R;
import com.resto.database.RestoDbHelper;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final RestoDbHelper dbHelper = new RestoDbHelper(this);
        String rest_name = dbHelper.getRestaurantName();
        setContentView(R.layout.main);
        TextView v = (TextView)findViewById(R.id.restaurant_name);
        v.setText(rest_name);
        Button btnClickMenu =(Button)findViewById(R.id.ButtonMenu);
        btnClickMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        //dbHelper.deleteRestaurantName();
    }
}
