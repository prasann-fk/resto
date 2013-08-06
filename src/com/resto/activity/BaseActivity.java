package com.resto.activity;

import android.app.Activity;
import android.os.Bundle;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.octo.android.robospice.GsonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.resto.database.RestoDbHelper;


public class BaseActivity extends Activity {

    private RestoDbHelper dbHelper = null;
    protected SpiceManager spiceManager = new SpiceManager(GsonSpringAndroidSpiceService.class);


    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
