package me.lshare.recyclerviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lshare on 2016/6/26.
 */
public class MyApplication extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
