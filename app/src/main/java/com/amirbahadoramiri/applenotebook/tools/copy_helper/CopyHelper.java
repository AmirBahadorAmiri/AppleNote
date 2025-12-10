package com.amirbahadoramiri.applenotebook.tools.copy_helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.util.Objects;

public class CopyHelper {

    private static ClipboardManager clipboardManager;
    private static CopyHelper copyHelper;

    public static CopyHelper initialize(Context applicatioContext) {
        if (clipboardManager == null || copyHelper == null) {
            copyHelper = new CopyHelper();
            clipboardManager = (ClipboardManager) applicatioContext.getSystemService(Context.CLIPBOARD_SERVICE);
        }
        return copyHelper;
    }

    public void insert(String str) {
        ClipData clipData = ClipData.newPlainText("", str);
        clipboardManager.setPrimaryClip(clipData);
    }

    public String read() {
        return Objects.requireNonNull(clipboardManager.getPrimaryClip()).getItemAt(0).getText().toString();
    }

}