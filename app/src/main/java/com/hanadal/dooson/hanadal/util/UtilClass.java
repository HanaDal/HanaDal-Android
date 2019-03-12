package com.hanadal.dooson.hanadal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public final class UtilClass {
    public static void Toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private static SharedPreferences pref = null;

    public static String getToken(Context context){
        if(pref == null) pref = context.getSharedPreferences("hanadal", MODE_PRIVATE);
        return pref.getString("token", "");
    }

    public static void saveToken(Context context, String token){
        if(pref == null) pref = context.getSharedPreferences("hanadal", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
