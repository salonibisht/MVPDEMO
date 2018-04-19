package com.example.appinventiv.finalmvpdemo;

import android.app.Application;

import com.example.appinventiv.finalmvpdemo.data.notificationdatabase.DatabaseHandler;

/**
 * Created by appinventiv on 17/4/18.
 */

public class MvpDemo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHandler.initContext(this);
    }
}
