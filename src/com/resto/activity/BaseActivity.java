package com.resto.activity;

import android.app.Activity;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.resto.api.DataService;
import com.resto.database.RestoDbHelper;

public class BaseActivity extends Activity {

    DataService dataService;
    private RestoDbHelper dbHelper = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataService = new DataService(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    protected RestoDbHelper getHelper() {
        if (dbHelper == null) {
            dbHelper =
                OpenHelperManager.getHelper(this, RestoDbHelper.class);
        }
        return dbHelper;
    }
}
