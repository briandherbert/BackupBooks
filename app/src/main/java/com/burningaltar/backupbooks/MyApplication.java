package com.burningaltar.backupbooks;

import android.app.Application;

/**
 * Created by bherbert on 5/1/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderLib.init(this);
    }
}
