package com.hanadal.dooson.hanadal.util;

import android.content.Context;
import android.widget.Toast;

public final class UtilClass {
    public static void Toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
