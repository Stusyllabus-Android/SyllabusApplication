package com.stu.syllabus.util;

import android.content.ClipData;
import android.content.ClipboardManager;

import com.stu.syllabus.App;

/**
 * yuan
 * 2019/12/22
 **/
public class ClipboardUtil {
    public static void copyToClipboard(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) App.getContext().getSystemService(App.getContext().CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
    }
}
