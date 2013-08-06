package com.resto.database;

import java.util.Collection;
import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Menus {

	@DatabaseField(generatedId = true)
	public int _id;
    @ForeignCollectionField(eager = false)
    private Collection<Menu> menus;

    public Collection<Menu> getMenus(){
        return menus;
    }

    public void setMenus(Collection<Menu> menus){
        this.menus = menus;
    }
}