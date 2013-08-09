package com.resto.models;

import java.util.Collection;
import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Menu {

	@DatabaseField(generatedId = true)
	public int _id;
    @ForeignCollectionField(eager = false)
    private Collection<MenuItem> menu;

    public Collection<MenuItem> getMenu(){
        return menu;
    }

    public void setMenus(Collection<MenuItem> menu){
        this.menu = menu;
    }
}