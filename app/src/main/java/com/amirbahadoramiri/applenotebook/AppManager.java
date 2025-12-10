package com.amirbahadoramiri.applenotebook;

import android.app.Application;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class AppManager extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setFont();
    }

    public void setFont() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/san_francisco_medium.ttf")
                                .setFontAttrId(io.github.inflationx.calligraphy3.R.attr.fontPath)
                                .build()))
                .build());
    }

}
