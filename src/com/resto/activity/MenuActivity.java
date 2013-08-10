package com.resto.activity;

import android.os.Bundle;
import android.util.Log;
import android.app.ActionBar;
import android.widget.*;
import android.view.*;
import android.view.Display;
import android.graphics.Point;
import android.view.Gravity;
import com.resto.R;
import com.resto.adapter.MenuListAdapter;
import com.resto.models.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.resto.models.Restaurant;

import java.util.List;

public class MenuActivity extends BaseActivity implements OnItemClickListener
{
    private final String CLASS_NAME = MenuActivity.class.getName();
    private final int BUTTON_WIDTH = 70;
    PopupMenu popupMenu;
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
        setFooterButtons();
    }

    public void setFooterButtons(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - BUTTON_WIDTH;
        RelativeLayout footer = (RelativeLayout) findViewById(R.id.footer);
        Restaurant restaurant = getHelper().getRestaurant();
        //String tags[] = restaurant.tags.split("\\.");
        boolean extraPopupNeeded = false;
        String[] tags = {"italian","mexican","indian","lebanese","popper"};
        int base_number = 100987;
        int i = 0;
        while(i < tags.length){
            Button footerButton = new Button(this);
            footerButton.setText(tags[i]);
            footerButton.setId(base_number + i);
            footerButton.setTextSize(12);
            footerButton.setLines(1);
            footerButton.setTag(tags[i]);
            footerButton.setBackgroundResource(R.drawable.button_bar_background);
            footerButton.setHorizontallyScrolling(true);
            footerButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                        view.getTag().toString(),
                        Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            });
            LayoutParams lp = new LayoutParams(BUTTON_WIDTH , 40);
            lp.setMargins(-3, -3, -3, -3);
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lp.addRule(RelativeLayout.RIGHT_OF, base_number + i - 1);
            width -= BUTTON_WIDTH;
            if(width < 0){
                extraPopupNeeded = true;
                break;
            }
            footer.addView(footerButton, lp);
            i++;
        }
        if(extraPopupNeeded){
            Button popupButton = (Button) findViewById(R.id.menu_more_id);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT , 40);
            lp.setMargins(-3, -3, -3, -3);
            lp.addRule(RelativeLayout.RIGHT_OF, base_number + i - 1);
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            popupButton.setTextSize(12);
            popupButton.setLayoutParams(lp);
            popupMenu = new PopupMenu(this, popupButton);
            while(i < tags.length){
                popupMenu.getMenu().add(Menu.NONE, base_number + i, Menu.NONE, tags[i]);
                i++;
            }
            popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener(){
                public boolean onMenuItemClick(android.view.MenuItem menuItem) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                        menuItem.getTitle(),
                        Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    return false;  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
            popupButton.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    popupMenu.show();
                }
            });
            popupButton.setVisibility(Button.VISIBLE);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
            "Item " + (position + 1) + ": " + menu_items.get(position),
            Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}