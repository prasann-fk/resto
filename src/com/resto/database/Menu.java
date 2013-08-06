package com.resto.database;

import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Menu {

	@DatabaseField(generatedId = true)
	public int _id;
	@DatabaseField(canBeNull = false, columnName = "external_id")
	public String id;
    @DatabaseField(canBeNull = false, foreign = true)
    private Restaurant restaurant;
	@DatabaseField(canBeNull = false)
	public String name;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String description;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String tags;
    @DatabaseField(foreign = true)
    private Menus menus;

	Menu() {
	}

	public Menu(Restaurant restaurant, String external_id, String name, String description, String tags) {
        this.restaurant = restaurant;
		this.id = external_id;
        this.name = name;
        this.description = description;
        this.tags = tags;
	}

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public Menus getMenus(){
        return menus;
    }

    public void setMenus(Menus menus){
        this.menus = menus;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(_id);
		sb.append(", ").append("external_id=").append(id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("description=").append(description);
		sb.append(", ").append("tags=").append(tags);
        sb.append(", ").append("restaurant=").append(restaurant.toString());
		return sb.toString();
	}
}

