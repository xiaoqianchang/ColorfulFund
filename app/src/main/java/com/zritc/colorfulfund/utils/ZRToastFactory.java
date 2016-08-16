package com.zritc.colorfulfund.utils;


import android.content.Context;
import android.widget.Toast;

public class ZRToastFactory {
    private static Context context = null;
    private static Toast toast = null;

    public static Toast getToast(Context context, String text) {
        if (ZRToastFactory.context == context) {
            // toast.cancel();
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);

        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        return toast;
    }

    public static Toast getToast(Context context, int resId) {
        if (ZRToastFactory.context == context) {
            toast.setText(resId);
            toast.setDuration(Toast.LENGTH_SHORT);

        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        }
        return toast;
    }

    public static Toast getLongToast(Context context, String text) {
        if (ZRToastFactory.context == context) {
            // toast.cancel();
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }
        return toast;
    }

    public static Toast getLongToast(Context context, int resId) {
        if (ZRToastFactory.context == context) {
            toast.setText(resId);
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        }
        return toast;
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
