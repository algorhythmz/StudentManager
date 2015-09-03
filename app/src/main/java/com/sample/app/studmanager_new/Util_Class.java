package com.sample.app.studmanager_new;

import android.content.Context;
import android.widget.Toast;

/**
 * Rahul Yadav Created com.sample.app.studmanager_new on 02-09-2015 at 15:44 for StudManager-New.
 */
public class Util_Class {

    public static void toastL(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void toastS(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
