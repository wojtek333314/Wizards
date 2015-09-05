package com.brotherhood.wizards.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Wojciech Osak on 2015-09-05.
 */
public class SharedPreferences
{
    private static String KEY = "wizards";

    private static Preferences getPrefs()
    {
        return Gdx.app.getPreferences(KEY);
    }
    public static void putString(String key,String value)
    {
        Preferences tmp = getPrefs();

        tmp.putString(key, value);
        tmp.flush();
    }

    public static void putBoolean(String key,boolean value)
    {
        Preferences tmp = getPrefs();
        tmp.putBoolean(key, value);
        tmp.flush();
    }

    public static void putLong(String key,long value)
    {
        Preferences tmp = getPrefs();
        tmp.putLong(key, value);
        tmp.flush();
    }

    public static void putInt(String key,int value)
    {
        Preferences tmp = getPrefs();
        tmp.putInteger(key, value);
        tmp.flush();
    }

    public static void putFloat(String key,float value)
    {
        Preferences tmp = getPrefs();
        tmp.putFloat(key, value);
        tmp.flush();
    }

    public static String getString(String key){
        return getPrefs().getString(key);
    }

    public static int getInt(String key){
        return getPrefs().getInteger(key);
    }

    public static float getFloat(String key){
        return getPrefs().getFloat(key);
    }

    public static boolean getBoolean(String key){
        return getPrefs().getBoolean(key);
    }

    public static long getLong(String key){
        return getPrefs().getLong(key);
    }

}
