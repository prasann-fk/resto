package com.resto.database;

import android.provider.BaseColumns;

public class RestoContract {

    public RestoContract() {}

     public static abstract class Restaurant implements BaseColumns {
        public static final String TABLE_NAME = "Restaurant";
        public static final String COLUMN_NAME_RESTAURANT_ID = "id";
        public static final String COLUMN_NAME_RESTAURANT_NAME = "name";
     }
}
