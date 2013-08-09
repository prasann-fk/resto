package com.resto.activity;

import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.view.Gravity;
import com.resto.R;
import com.resto.adapter.MenuListAdapter;
import com.resto.models.MenuItem;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

public class MenuActivity extends BaseActivity implements OnItemClickListener
{
    List<MenuItem> menu_items;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ListView listView = (ListView) findViewById(R.id.menu_list);
        menu_items = getHelper().getAllMenuItems();
        MenuListAdapter adapter = new MenuListAdapter(this, R.layout.menu_item, menu_items );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
            "Item " + (position + 1) + ": " + menu_items.get(position),
            Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}