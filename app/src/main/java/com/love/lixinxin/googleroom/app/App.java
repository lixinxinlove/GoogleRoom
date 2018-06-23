package com.love.lixinxin.googleroom.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.love.lixinxin.googleroom.db.LeeDatabase;

public class App extends Application {

    public static LeeDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = LeeDatabase.getInstance(this);
        Stetho.initializeWithDefaults(this);
    }
}
