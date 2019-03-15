package com.hanadal.dooson.hanadal.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public final class UtilClass {
    private static SharedPreferences pref = null;
    private static ProgressDialog progressDialog = null;

    public static void Toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getToken(Context context) {
        if (pref == null) pref = context.getSharedPreferences("hanadal", MODE_PRIVATE);
        return pref.getString("token", "");
    }

    public static String getToken() {
        return pref.getString("token", "");
    }

    public static void saveToken(Context context, String token) {
        if (pref == null) pref = context.getSharedPreferences("hanadal", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static void initProgress(Activity activity){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("로딩중...");
        progressDialog.setCancelable(false);
    }

    public static void loadProgress(Activity activity) {
        initProgress(activity);
        progressDialog.show();
    }

    public static void cancelProgress() {
        if (progressDialog != null) progressDialog.dismiss();
    }
}
