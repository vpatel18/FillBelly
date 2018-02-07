package com.developer.nikhil.fillbelly;

import android.app.Application;
import android.content.Context;


public class FillBellyApplication extends Application {

    public static FillBellyApplication application;

    public FillBellyApplication() {
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static FillBellyApplication getApp() {
        if (application == null) {
            application = new FillBellyApplication();
        }
        return application;
    }

    public static Context getAppContext() {
        if (application == null) {
            application = new FillBellyApplication();
        }
        return application;
    }
}


