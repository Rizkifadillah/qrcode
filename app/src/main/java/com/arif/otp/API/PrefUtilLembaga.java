package com.arif.otp.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class PrefUtilLembaga {
    public static final String LEMBAGA_SESSION = "lembaga_session";

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putLembaga(Context context, String key, Lembaga lembaga){
        Gson gson = new Gson();
        String json = gson.toJson(lembaga);
        putString(context, key, json);
    }

    public static Lembaga getLembaga(Context context, String key){
        Gson gson = new Gson();
        String json = getString(context, key );
        Lembaga lembaga = gson.fromJson(json, Lembaga.class);
        return lembaga;
    }

    private static void putString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    private static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    public static void clear (Context context){
        getSharedPreference(context).edit().clear().apply();
    }
}
