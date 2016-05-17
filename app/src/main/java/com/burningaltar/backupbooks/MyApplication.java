package com.burningaltar.backupbooks;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by bherbert on 5/1/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        ImageLoaderLib.init(this);
    }
}
