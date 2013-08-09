package com.resto.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.*;
import android.widget.*;
import com.resto.models.MenuItem;
import com.resto.R;

public class MenuListAdapter extends ArrayAdapter<MenuItem> {

    Context context;

    public MenuListAdapter(Context context, int resourceId,
            List<MenuItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        Button addToCartButton;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        MenuItem menuItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_item, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.description);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.name);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.addToCartButton = (Button) convertView.findViewById(R.id.add_to_cart_button);
            holder.addToCartButton.setTag(position);
            holder.addToCartButton.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Left Accessory "+view.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(menuItem.getDescription());
        holder.txtTitle.setText(menuItem.getName());
        holder.imageView.setImageDrawable(menuItem.getImageDrawable(context));

        return convertView;
    }
}