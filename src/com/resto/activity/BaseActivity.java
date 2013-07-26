package com.resto.activity;

import android.app.Activity;
import android.os.Bundle;
import com.resto.api.DataService;
import com.resto.database.RestoDbHelper;

public class BaseActivity extends Activity {

    RestoDbHelper dbHelper;
    DataService dataService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new RestoDbHelper(this);
        dataService = new DataService(this);
    }
}
