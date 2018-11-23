package eu.bufa.prodan.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreference {

    private static final String PREFS_NAME = "preferences";

    private static SharedPreference shared = new SharedPreference();

    private SharedPreference() {
    }

    public static SharedPreference getInstance() {
        return shared;
    }

    public void SaveSharedPreference(Context context, final String key, final String value) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            Editor editor = settings.edit();

            editor.putString(key, value);

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetSharedPreference(Context context, String key, String defaultValue) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void SaveSharedPreferenceBoolean(Context context, final String key, final boolean value) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            Editor editor = settings.edit();

            editor.putBoolean(key, value);

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getSharedPreferenceBoolean(Context context, final String key, final boolean defaultValue) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getBoolean(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void removePreference(Context context, String key) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveSharedPreferencesInt(Context context, final String key, final int value) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            Editor editor = settings.edit();

            editor.putInt(key, value);

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSharedPreferenceInt(Context context, final String key, final int defaultValue) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void saveSharedPreferenceFloat(Context context, final String key, final float value){
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            Editor editor = settings.edit();

            editor.putFloat(key, value);

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getSharedPreferenceFloat(Context context, final String key, final float defaultValue) {
        try {
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            return settings.getFloat(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
