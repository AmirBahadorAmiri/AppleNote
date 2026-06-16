package com.amirbahadoramiri.applenotebook;

import android.app.Application;

import com.amirbahadoramiri.applenotebook.tools.sharedhelper.SharedHelper;

public class AppManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedHelper.init();
    }
}
