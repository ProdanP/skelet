package eu.bufa.prodan.myapplication.utils;


import android.util.Log;

import eu.bufa.prodan.myapplication.BuildConfig;

public class LogHelper {
    public static void log(String tag, Object object) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, " : " + object);
        }
    }
}
