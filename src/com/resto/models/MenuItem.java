package com.resto.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;
import com.resto.utils.ImageUtils;

@DatabaseTable
public class MenuItem {

	@DatabaseField(generatedId = true)
	public int _id;
	@DatabaseField(canBeNull = false, columnName = "external_id")
	private String id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Restaurant restaurant;
	@DatabaseField(canBeNull = false)
	private String name;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    private String description;
    @DatabaseField(columnDefinition = "LONGBLOB not null", dataType = DataType.BYTE_ARRAY)
    public byte[] image_bytes;

    private String image;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    private String tags;
    @DatabaseField(foreign = true)
    private Menu menu;

	MenuItem() {
	}

	public MenuItem(Restaurant restaurant, String external_id, String name, String description, String tags) {
        this.restaurant = restaurant;
		this.id = external_id;
        this.name = name;
        this.description = description;
        this.tags = tags;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Drawable getImageDrawable(Context context){
        return ImageUtils.byteToDrawable(context, image_bytes);
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public Menu getMenu(){
        return menu;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(_id);
		sb.append(", ").append("external_id=").append(id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("description=").append(description);
		sb.append(", ").append("tags=").append(tags);
		return sb.toString();
	}
}

