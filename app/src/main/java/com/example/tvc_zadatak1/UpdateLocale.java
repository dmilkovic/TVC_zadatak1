package com.example.tvc_zadatak1;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

public class UpdateLocale {

    public static String getLocale(Context context)
    {
        String langPref = "Language";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String language = prefs.getString(langPref, "");
        return language;
    }

    public static boolean isLocaleSet(Context context)
    {
        if(getLocale(context).equals(""))
        {
            return false;
        }else
        {
            return true;
        }
    }

    public static void loadLocale(Context context) {
        String language = getLocale(context);
        changeLang(language, context);
    }

    public static void changeLang(String lang, Context context) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang, context);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());
    }

    public static void saveLocale(String lang, Context context) {
        String langPref = "Language";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }
}
