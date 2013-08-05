package com.resto.database;

import com.j256.ormlite.field.*;

public class Menu {

	@DatabaseField(generatedId = true)
	public int id;
	@DatabaseField(canBeNull = false)
	public String external_id;
	@DatabaseField(canBeNull = false)
	public String name;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String description;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String tags;

	Menu() {
	}

	public Menu(String external_id, String name, String description, String tags) {
		this.external_id = external_id;
        this.name = name;
        this.description = description;
        this.tags = tags;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append("external_id=").append(external_id);
		sb.append(", ").append("name=").append(name);
		sb.append(", ").append("description=").append(description);
		sb.append(", ").append("tags=").append(tags);
		return sb.toString();
	}
}

