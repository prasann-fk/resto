package com.resto.database;

import android.provider.BaseColumns;

public class RestoContract {

    public RestoContract() {}

    public static abstract class Restaurant implements BaseColumns {
        public static final String TABLE_NAME = "Restaurant";
        public static final String COLUMN_NAME_RESTAURANT_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TAGS = "tags";
    }

    public static abstract class Menu implements BaseColumns {
        public static final String TABLE_NAME = "Menu";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TAGS = "tags";
    }
}
