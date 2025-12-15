package com.amirbahadoramiri.applenotebook.tools.logger;

import android.util.Log;

public class Logger {
    public static String TAG = "DEBUG";

    public static void loge(String msg) {
        Log.e(TAG, msg);
    }

    public static void loge(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void logd(String msg) {
        Log.d(TAG, msg);
    }

    public static void logd(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void logi(String msg) {
        Log.i(TAG, msg);
    }

    public static void logi(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void logw(String msg) {
        Log.w(TAG, msg);
    }

    public static void logw(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void logv(String msg) {
        Log.v(TAG, msg);
    }

    public static void logv(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void logwtf(String msg) {
        Log.wtf(TAG, msg);
    }

    public static void logwtf(String tag, String msg) {
        Log.wtf(tag, msg);
    }

    public static void log_print(int priority, String msg) {
        Log.println(priority, TAG, msg);
    }

    public static void log_print(String tag, int priority, String msg) {
        Log.println(priority, tag, msg);
    }
}
