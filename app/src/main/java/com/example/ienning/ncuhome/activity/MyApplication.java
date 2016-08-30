package com.example.ienning.ncuhome.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by ienning on 16-7-28.
 */
public class MyApplication extends Application{
    private static Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
