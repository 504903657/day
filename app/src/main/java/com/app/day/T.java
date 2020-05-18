package com.app.day;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class T {

    public static Toast toast = null;

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;


    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            if (toast == null) {
                Toast toast = new Toast(context);
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View toastRoot = inflater.inflate(R.layout.toast, null);
                TextView tv = (TextView) toastRoot.findViewById(R.id.toast_text);


                tv.setText(message);
                toast.setView(toastRoot);
                // toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.BOTTOM, 0, 0);

                toast.show();
            } else {
                toast.setText(message);
                toast.show();
            }
    }


    public static void showDefultToast(Context context, String title) {
        if (toast == null) {
            toast = Toast.makeText(context, title, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            toast.setText(title);
            toast.show();
        }
    }


    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }


    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}