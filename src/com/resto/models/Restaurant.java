package com.resto.models;

import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Restaurant {

	@DatabaseField(generatedId = true)
	public int _id;
	@DatabaseField(canBeNull = false, columnName = "external_id")
	public String id;
	@DatabaseField(canBeNull = false)
	public String name;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String description;
	@DatabaseField(canBeNull = true, dataType = DataType.LONG_STRING)
    public String tags;

	Restaurant() {
	}

	public Restaurant(String external_id, String name, String description, String tags) {
		this.id = external_id;
        this.name = name;
        this.description = description;
        this.tags = tags;
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
