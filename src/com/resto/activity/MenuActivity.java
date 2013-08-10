package com.resto.activity;

import android.os.Bundle;
import android.util.Log;
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
import com.resto.models.FooterButton;

import java.util.List;

public class MenuActivity extends BaseActivity implements OnItemClickListener
{
    private final String CLASS_NAME = MenuActivity.class.getName();
    private final int BUTTON_WIDTH = 70;
    private final int base_number = 100987;
    String[] tags;
    PopupMenu popupMenu = null;
    List<MenuItem> menu_items;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ListView listView = (ListView) findViewById(R.id.menu_list);
        tags = getHelper().getRestaurant().tags.split("\\.");
        menu_items = getHelper().getMenuItem(tags[0]);
        MenuListAdapter adapter = new MenuListAdapter(this, R.layout.menu_item, menu_items );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setFooterButtons();
    }

    public void setFooterButtons(){
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x - BUTTON_WIDTH;
        RelativeLayout footer = (RelativeLayout) findViewById(R.id.footer);
        boolean extraPopUpNeeded = false;
        int i = 0;
        while(i < tags.length){
            FooterButton footerButton = createFooterButton(i);
            width -= BUTTON_WIDTH;
            if(width < 0){
                extraPopUpNeeded = true;
                break;
            }
            footer.addView(footerButton);
            i++;
        }
        if(extraPopUpNeeded)
            createPopUpMenu(i);
        final Button btn = (Button) findViewById(base_number);
            btn.post(new Runnable() {
                public void run() {
                    btn.setBackgroundResource(R.drawable.button_pressed);
                    btn.setClickable(false);
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
            "Item " + (position + 1) + ": " + menu_items.get(position),
            Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public FooterButton createFooterButton(int i){
        FooterButton footerButton = new FooterButton(this);
        footerButton.setText(tags[i]);
        footerButton.setId(base_number + i);
        footerButton.setTag(tags[i]);
        footerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                recreateListFromTag(view.getTag().toString());
                enableButtons();
                final Button btn = (Button) view;
                    btn.post(new Runnable() {
                        public void run() {
                            btn.setBackgroundResource(R.drawable.button_pressed);
                            btn.setClickable(false);
                    }
                });
            }
        });
        LayoutParams lp = (LayoutParams) footerButton.getLayoutParams();
        lp.addRule(RelativeLayout.RIGHT_OF, base_number + i - 1);
        footerButton.setLayoutParams(lp);
        return footerButton;
    }

    public void createPopUpMenu(int i){
        FooterButton popUpButton = (FooterButton) findViewById(R.id.menu_more_id);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(70 , 40);
        lp.setMargins(-3, -3, -3, -3);
        lp.addRule(RelativeLayout.RIGHT_OF, base_number + i - 1);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        popUpButton.setLayoutParams(lp);
        popupMenu = new PopupMenu(findViewById(R.id.footer).getContext(), popUpButton);
        while(i < tags.length){
            popupMenu.getMenu().add(Menu.NONE, base_number + i, Menu.NONE, tags[i]);
            i++;
        }
        popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(android.view.MenuItem menuItem) {
                recreateListFromTag(menuItem.getTitle().toString());
                enableButtons();
                menuItem.setEnabled(false);
                return false;
            }
        });
        popUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                popupMenu.show();
            }
        });
        popUpButton.setVisibility(Button.VISIBLE);
    }

    public void enableButtons(){
        for(int k = 0; k < tags.length; k++){
            final Button btn = (Button) findViewById(base_number + k);
            if(btn != null){
                btn.post(new Runnable() {
                    public void run() {
                        btn.setBackgroundResource(R.drawable.button_bar_default);
                        btn.setClickable(true);
                    }
                });
            }
        }
        if(popupMenu != null){
            for(int j = 0; j < popupMenu.getMenu().size(); j++){
                popupMenu.getMenu().getItem(j).setEnabled(true);
            }
        }
    }

    public void recreateListFromTag(String tag){
        menu_items = getHelper().getMenuItem(tag);
        ListView listView = (ListView) findViewById(R.id.menu_list);
        MenuListAdapter adapter = (MenuListAdapter) listView.getAdapter();
        adapter.clear();
        adapter.addAll(menu_items);
        adapter.notifyDataSetChanged();
    }
}