package com.amirbahadoramiri.applenotebook.tools.packager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import com.amirbahadoramiri.applenotebook.tools.logger.Logger;

public class Packager {

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.logd("getVersionName: " + e.getMessage());
        }
        return "";
    }

    public static String negareshApplication(Context context) {
        return "Version " + Packager.getVersionName(context);
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.logd("getVersionName: " + e.getMessage());
        }
        return 0;
    }

    public static void openInPlayStore(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
    }

    public static void openInMarket(Context context) {
        context.startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())), ""));
    }

    public static int getSdkCode() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.logd("getVersionName: " + e.getMessage());
        }
        return false;
    }

}