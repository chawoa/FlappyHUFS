package com.example.android_sw;

import android.content.Context;
import android.content.SharedPreferences;

public class ExpStore {
    public static int LV = 1;
    private static final String PREF_NAME = "expStorePreference";
    private static final String KEY_PREVIOUS_EXP = "previousExp";
    private static SharedPreferences sharedPreferences;

    public static void assign(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setPreviousExp(int exp) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_PREVIOUS_EXP, exp);
        editor.apply();
    }

    public static int getPreviousExp() {
        return sharedPreferences.getInt(KEY_PREVIOUS_EXP, 0);
    }

    public static int addExp(int exp) {
        int previousExp = getPreviousExp();
        previousExp += exp;
        setPreviousExp(previousExp);
        return previousExp;
    }

    public static float calculateExpPercentage(int maxExp) {
        float expPercentage = ((float) getPreviousExp() / maxExp) * 100;
        return expPercentage;
    }
}