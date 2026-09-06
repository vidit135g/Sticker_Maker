package com.absolute.whatsappstickers.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RequestPermissionsHelper {

    private static final int CODE_REQUEST_WRITE_READ_EXTERNAL_STORAGE = 0;

    public static boolean verifyPermissions(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            return true;
        }
        int permissionToWriteCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionToReadCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionToWriteCheck == PackageManager.PERMISSION_DENIED && permissionToReadCheck == PackageManager.PERMISSION_DENIED) {
            return false;
        } else return true;
    }

    public static void requestPermissions(Activity context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            return;
        }
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_REQUEST_WRITE_READ_EXTERNAL_STORAGE);
    }
}
